package com.lilithsthrone;

import com.lilithsthrone.main.Main;

/**
 * If JavaFX isn't part of the java package but rather is added separately, you need to wrap the main method in a launcher method, otherwise it will fail.
 * 
 * @since 0.4.6.6
 * @version 0.4.6.6
 * @author Tazze
 */
public class Launcher {
    public static void main(String[] args) {
        Main.main(args);
    }
}
