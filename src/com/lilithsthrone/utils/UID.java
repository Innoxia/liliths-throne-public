package com.lilithsthrone.utils;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Unique identifier class.
 * Uses a wall timestamp to stay unique across application and system restarts. A random number is appended to protect
 * against system time changes and restarts within one millisecond.
 * Note that this implementation is not cryptographically secure. Collisions under special circumstances may happen with
 * a chance of 0.0015%, which is good enough for character ids, but should not be used for passwords or encryption.
 *
 * @since 0.2.11
 * @version 0.2.11
 * @author Addi
 */
public class UID {
    private static final char[] ENCODE = {
            // Alphanumeric, case-insensitive, no I, L, O and U, https://www.crockford.com/wrmg/base32.html
            '0','1','2','3','4','5','6','7','8','9',
            'A','B','C','D','E','F','G','H','J','K', 'M','N','P','Q','R','S','T','V','W','X', 'Y','Z'
    };

    private static AtomicLong previousTimestamp = new AtomicLong();
    private long timestamp;
    private long random;

    public static String get() {
        return new UID().toString();
    }

    public UID() {
        // Timestamp component
        long currentTime = System.currentTimeMillis();
        timestamp = previousTimestamp.updateAndGet(previousValue -> Math.max(previousValue + 1, currentTime));

        // Random component
        random = new Random().nextInt(65536);
    }

    /**
     * Converts the least significant 48 bit of the timestamp (8925 years), followed by a 16 bit random, into a base 32
     * string. It only contains alphanumeric uppercase symbols and is safe to use in file names and URLs.
     * @return A URL and file name safe unique identification string with up to 13 characters
     */
    public String toString() {
        StringBuilder output = new StringBuilder(13);
        // If you are reading this in the year 10895 you might have to fix the left shift
        long id = (timestamp << 16) + (random & 0x0000_0000_0000_FFFFL);

        for (int i = 12; i >= 0; --i) {
            // Take the 5 next bits (0b11111 mask) and convert them into a char from the encode array
            int index = (int) ((id >> (i * 5)) & 0x1F);
            output.append(ENCODE[index]);
        }

        // Strip leading zeros
        while (output.charAt(0) == '0')
            output.deleteCharAt(0);

        return output.toString();
    }
}
