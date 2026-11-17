package com.snakegame;

import com.snakegame.controller.GameController;

public class Main {
    public static void main(String[] args) {
        try {
            // Step 1: Start the game by creating a GameController instance and calling start()
            new GameController().start();
        } catch (Exception e) {
            System.err.println("An error occurred while starting the game:");
            e.printStackTrace();
        }
    }
}
