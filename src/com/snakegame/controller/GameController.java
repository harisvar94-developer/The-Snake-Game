package com.snakegame.controller;

import com.snakegame.dto.Direction;
import com.snakegame.dto.Player;
import com.snakegame.service.GameService;
import com.snakegame.service.GameService.MoveResult;
import com.snakegame.view.GameView;
import java.util.Scanner;

public class GameController {

    // VIEW: The GameView class is responsible for displaying the game state and reading user input. It provides methods to print the welcome message, read the player's name, read the player's move, and print the game board.

    // SERVICE: The GameService class is responsible for managing the game state. It provides methods to move the snake, check for collisions, and manage the score. It also provides access to the current state of the board, snake, and food.

    // CONTROLLER: The GameController class is responsible for coordinating the interaction between the view and the service. It handles user input, updates the game state, and updates the view accordingly. It also manages the game loop and handles the end of the game.
    
    private final GameView view = new GameView();
    private final ScoreController scoreController = new ScoreController();

//---------------------------------------------------------------------------------------------------------------------
//step 2: Implement the start method to handle the game loop and user input
    public void start() {
        Scanner scanner = new Scanner(System.in);
        // step 3:  Go to (view) and access printWelcome()  Print welcome message and read player name
        view.printWelcome();
        //  step 4 go to 53 line of this class and call readPlayer() method to read player name and create a Player object and assign it to a variable name to  player bro 
        Player player = readPlayer(scanner);
        // step 5: Create a GameService instance to manage the game state and assign it to a variable named game

        GameService game = new GameService();
        //------------------------------------------------------
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);


        // the running variable is used to control the game loop. It is set to true initially, and will be set to false when the game ends (either by hitting a wall or running into itself).
        boolean running = true;
        //------------------------------------------------------------------------
        //

        view.printBoard(game.getSnake(), game.getFood(), game.getScore());
        //-----------------------------------------------------------------------
        while (running) {
            String input = view.readMove(scanner);
            System.out.println("entered : "+input);
            if (input == null || input.isEmpty()) {
                view.printMessage("Invalid input. Use L, R, T, B or Q.");
                continue;
            }
            // bug 
            if (input.equals("Q") ) {
                view.printMessage("You quit the game.");
                System.exit(0);
            }
            Direction direction = Direction.fromChar(input.charAt(0));
            System.out.println(direction);
            // if (input == null) {
            //     view.printMessage("Invalid input. Use L, R, T, B or Q.");
            //     continue;
            // }
            
            
            MoveResult result = game.move(direction);
            if (result == MoveResult.WALL) {
                view.printMessage("You hit the wall!");
                running = false;
            } else if (result == MoveResult.SELF) {
                view.printMessage("You ran into yourself!");
                running = false;
            } else if (result == MoveResult.ATE) {
                view.printMessage("Yum! That food was worth it.");
            }
            view.printBoard(game.getSnake(), game.getFood(), game.getScore());
        }
        long durationSeconds = System.currentTimeMillis() - startTime;
        scoreController.finish(player, game, durationSeconds);
    }


    //------------------------------------------------------------------------------------------------------------------



    private Player readPlayer(Scanner scanner) {
        String name = view.readPlayerName(scanner);
        return new Player(name);
    }
}
