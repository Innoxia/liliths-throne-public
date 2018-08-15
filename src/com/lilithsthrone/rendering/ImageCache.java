package com.lilithsthrone.rendering;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @since 0.2.5.5
 * @version 0.2.10
 * @author Addi
 */
public enum ImageCache {
	INSTANCE;

	protected Map<File, CachedImage> cache = Collections.synchronizedMap(new LinkedHashMap<File, CachedImage>(16, 0.75f, true) {
		private static final long serialVersionUID = 1L;

		@Override
		protected boolean removeEldestEntry(Map.Entry<File, CachedImage> eldest) {
			// Always allow as many as there can be in one room (player, two companions, five slaves)
			if (size() <= 8) return false;

			// Allow cache with up to 1/10 of the maximum memory
			long byteSize = size() * 500 * 1024;
			if (byteSize > Runtime.getRuntime().maxMemory() / 10) return true;

			// Drop entries until the cache is smaller than the remaining memory
			long adjustSize = (byteSize - Runtime.getRuntime().freeMemory()) / (500 * 1024);
			if (adjustSize > 0)
				for (int i = 0; i < adjustSize; ++i)
					remove(keySet().iterator().next());
			return false;
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

			getImage(f);
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
	 * @return A CachedImage object containing the image string or null if the image failed to load
	 */
	public CachedImage getImage(File f) {
		CachedImage image = cache.get(f);
		if (image == null) {
			image = new CachedImage();
			if (image.load(f)) cache.put(f, image);
			else return null;
		}
		return image;
	}
}
