package org.uob.a2;

import java.util.Scanner;
import java.util.ArrayList;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 * 
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game {
    private GameState gameState; //holds game information
    private Tokeniser tokeniser; //breaks input into tokens
    private Parser parser; //converts tokens into commands

    public Game(){
        this.gameState = GameStateFileParser.parse("data/game.txt"); //loads game data from file
        this.tokeniser = new Tokeniser();
        this.parser = new Parser();
    }

    public static void main(String[] args){
        Game game = new Game(); //creates a new game instance
        System.out.println("In this game, you are a prisoner and your goal is to try and escape. The only exit is the main gate. Good luck!");
        game.run(); //starts the game by calling the run method
        
    }
    public void run(){
        start();
    }
    public void start(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.print(">");
            String input = scanner.nextLine();
            ArrayList<Token> tokens = tokenise(input);

            try{
                //parses the tokens into a command
               Command command = this.parser.parse(tokens);
                if(command instanceof Quit){
                    String quit = command.execute(this.gameState); //executes the quit command
                    System.out.println(quit);
                    break; //exits the loop and ends the game
                }else{
                turn(command);
                }
            } catch(CommandErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    //tokenise the input string into a list of tokens
    private ArrayList<Token> tokenise(String input){
        this.tokeniser.tokenise(input);
        return this.tokeniser.getTokens();
    }
    //handles the execution if a valid command is parsed
    public void turn(Command command){
        String result = command.execute(this.gameState); //executes the command and gets the result
        System.out.println(result); //displayes the result of the command
        Scanner reader = new Scanner(System.in);
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom();
        
        //logic for handling quitting the game if the player wins 
        if(result.contains("You unlock the main gate with the key.\nYou have successfully exited the prison. Well Done!")){
            StringBuilder endMessage = new StringBuilder();
            endMessage.append("Game over:\n");

            Score score = gameState.getScore();
            int gameScore = score.getScore();
            endMessage.append("End score: " + gameScore + "\n");
        
        if (!player.getInventory().isEmpty()) {
            endMessage .append("You had: ");
            for (GameObject item : player.getInventory()) {
                endMessage .append(item.getName().toLowerCase());
                endMessage .append(" ");
            }
        }
        System.out.println(endMessage.toString().trim());
            System.exit(0);  //exits the game
    }
        if(result.contains("Type 'solve puzzle' and choose the right key")){
            String inputToSolvePuzzle = reader.nextLine();
            if(inputToSolvePuzzle.equals("solve puzzle")){
                System.out.println("Letters on key 1 : hvbse sppn, Letters on key 2: nbjo hbuf, Letters on key 3: tupsbhf, Letters on key 4: jogjsnbsz.Which key will be used to open the main gate?(Hint - shift each letter one letter back in the alphabet to get the real word, each key has the name of the room it opens):");
                String inputToSolveRiddle = reader.nextLine();
                if(inputToSolveRiddle.equals("key 2")){
                System.out.println("Correct! That is the right key.Get the key and use it to unlock the main gate.");
                    gameState.getScore().addUseOnTarget();
                    Equipment equipment = currentRoom.getEquipmentByName("key");
                    equipment.setHidden(false);
                    
            }else{
                  System.out.println("Incorrect.You don't get another chance to solve the puzzle.");  
                }
            }
        }
        //checks if player just got the mask
        if(result.contains("You pick up: mask")){
            String inputWear = reader.nextLine();
            if(inputWear.equals("wear mask")){
                if(player.hasItem("mask")){
                    //gets the mask 
                    Item mask = player.getItem("mask");
                    //removes it from inventory
                    player.getInventory().remove(mask);
                System.out.println("You have worn the mask. This will help with your disguise");
            }
            }
            else{
                System.out.println("Wrong input");
            }
        }
        //checks if player just got uniform
        if(result.contains("You pick up: uniform")){
            String inputWear = reader.nextLine();
            if(inputWear.equals("wear uniform")){
                if(player.hasItem("uniform")){
                    //gets the uniform
                    Item uniform = player.getItem("uniform");
                    //removes it from inventory
                    player.getInventory().remove(uniform);
                System.out.println("You have worn the uniform. You can now easily blend in with the guards");
            }
            }
            else{
                System.out.println("Wrong input");
            }
}
}
}
