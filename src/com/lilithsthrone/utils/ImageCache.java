package com.lilithsthrone.utils;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @since 0.2.5.5
 * @version 0.2.5.5
 * @author Addi
 */
public enum ImageCache {
	INSTANCE;

	protected ConcurrentHashMap<File, SoftReference<CachedImage>> cache = new ConcurrentHashMap<>();
	protected LinkedBlockingQueue<File> queue = new LinkedBlockingQueue<>();
	protected Thread loaderThread = new Thread(() -> {
		while (true) {
			File f;
			try {
				// Fetch a new file to load or wait until one is available
				f = queue.take();
			} catch (InterruptedException e) {
				// Queue broke, end the thread
				break;
			}

			try {
				getImage(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	});

	ImageCache() {
		// Start loader thread as daemon so it will be ended by application shutdown
		loaderThread.setDaemon(true);
		loaderThread.start();
	}

	/**
	 * Attempt to queue an image for caching if it is not queued already. This operation may fail if the queue is full.
	 * @param f A File containing the path to the image
	 */
	public void requestCache(File f) {
		if (retrieveImage(f) == null && !queue.contains(f)) {
			queue.offer(f);
		}
	}

	/**
	 * Attempts to retrieve a cached image. If it isn't in the cache, queue it and return immediately.
	 * @param f A File containing the path to the image
	 * @return A CachedImage object containing the image string if found in the cache, null otherwise
	 */
	public CachedImage requestImage(File f) {
		CachedImage image = retrieveImage(f);
		if (image == null) {
			requestCache(f);
		}
		return image;
	}

	/**
	 * Retrieves an image. If the image isn't in the cache, load it immediately and block the caller until it is ready.
	 * @param f A File containing the path to the image
	 * @return A CachedImage object containing the image string
	 * @throws IOException Forwarded exception if the image can not be loaded
	 */
	public CachedImage getImage(File f) throws IOException {
		CachedImage image = retrieveImage(f);
		if (image == null) {
			image = new CachedImage();
			image.load(f);
			cache.put(f, new SoftReference<>(image));
		}
		return image;
	}

	protected CachedImage retrieveImage(File f) {
		// Attempt to fetch the image
		SoftReference<CachedImage> ref = cache.get(f);
		if (ref != null) {
			if (ref.get() != null) {
				// Cache hit, return the image
				return ref.get();
			} else {
				// Dead reference, clean it up
				cache.remove(f);
			}
		}
		// Cache miss
		return null;
	}
}
