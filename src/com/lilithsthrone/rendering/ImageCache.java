package com.lilithsthrone.rendering;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @since 0.2.5.5
 * @version 0.2.5.5
 * @author Addi
 */
public enum ImageCache {
	INSTANCE;

	protected Map<File, CachedImage> cache = Collections.synchronizedMap(new LinkedHashMap<File, CachedImage>(16, 0.75f, true) {
		@Override
		protected boolean removeEldestEntry(Map.Entry<File, CachedImage> eldest) {
			// Always allow as many as there can be in one room (player, two companions, five slaves)
			if (size() <= 8) return false;

			// Allow cache with 1/10 of the maximum memory and less than the amount of available memory
			int kbSize = size() * 500 * 1024;
			return kbSize > Runtime.getRuntime().maxMemory() / 10 || kbSize > Runtime.getRuntime().freeMemory();
		}
	});

	protected LinkedBlockingQueue<File> queue = new LinkedBlockingQueue<>();
	protected Thread loaderThread = new Thread(() -> {
		while (!Thread.currentThread().isInterrupted()) {
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
			} catch (NullPointerException e) {
				// Early shutdown, just ignore it
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
		if (cache.get(f) == null && !queue.contains(f)) {
			queue.offer(f);
		}
	}

	/**
	 * Attempts to retrieve a cached image. If it isn't in the cache, queue it and return immediately.
	 * @param f A File containing the path to the image
	 * @return A CachedImage object containing the image string if found in the cache, null otherwise
	 */
	public CachedImage requestImage(File f) {
		CachedImage image = cache.get(f);
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
		CachedImage image = cache.get(f);
		if (image == null) {
			image = new CachedImage();
			image.load(f);
			cache.put(f, image);
		}
		return image;
	}
}
