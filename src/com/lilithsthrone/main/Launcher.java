package com.lilithsthrone.main;

import javafx.application.Application;

public class Launcher {
    // The jar's main class cannot inherit from javafx.Application
    // if we're to bundle JavaFX within it.

    public static void main(String[] args) {
        Main.init_env();
        Application.launch(Main.class, args);
    }
}
