package org.uob.a2.parser;

import java.util.ArrayList;

import org.uob.a2.commands.*;

/**
 * The {@code Parser} class processes a list of tokens and converts them into {@code Command} objects
 * that can be executed by the game.
 * 
 * <p>
 * The parser identifies the type of command from the tokens and creates the appropriate command object.
 * If the command is invalid or incomplete, a {@code CommandErrorException} is thrown.
 * </p>
 */
public class Parser {
    private ArrayList<Token> tokens = new ArrayList<>();

    public Parser(){
        
    }
    public Command parse(ArrayList<Token> tokens) throws CommandErrorException{
              this.tokens = tokens;

        if(tokens == null || tokens.isEmpty()){
            throw new CommandErrorException("No command entered");
        }
        Token commandToken = tokens.get(0);
        if(commandToken == null){
            throw new CommandErrorException("Command type cannot be null or empty");
        }
        

        switch(commandToken.getTokenType()){
            case USE:
                if(tokens.size() < 4){
                    throw new CommandErrorException("Enter the 'use' command in the order: use <equipmentName> on <target>");
                }
                if (tokens.get(1).getTokenType() != TokenType.VAR || tokens.get(2).getTokenType() != TokenType.PREPOSITION ||
                    tokens.get(3).getTokenType() != TokenType.VAR) {
                    throw new CommandErrorException("Invalid USE command format");
                }
                return new Use(tokens.get(1).getValue(), tokens.get(3).getValue());
            case COMBINE:
                if(tokens.size() < 5){
                    throw new CommandErrorException("Enter the 'combine' command in the order: combine <item1> and <item2>");
                }
                if(tokens.get(1).getTokenType() != TokenType.VAR || tokens.get(2).getTokenType() != TokenType.PREPOSITION ||                               tokens.get(3).getTokenType() != TokenType.VAR){
                    throw new CommandErrorException("Invalid COMBINE command format");
                }
                return new Combine(tokens.get(1).getValue(), tokens.get(3).getValue());
            case MOVE:
                if(tokens.size() < 2 || tokens.get(1).getTokenType() != TokenType.VAR){
                    throw new CommandErrorException("Enter the 'move' command in the order: move <direction>");
                }
                return new Move(tokens.get(1).getValue());

            case GET:
                if(tokens.size() < 2 || tokens.get(1).getTokenType() != TokenType.VAR){
                    throw new CommandErrorException("Enter the 'get' command in the order: get <item>");
                }
                return new Get(tokens.get(1).getValue());

            case LOOK:
                if(tokens.size() < 2 || tokens.get(1).getTokenType() != TokenType.VAR){
                    throw new CommandErrorException("Enter the 'look' coomand in the order: look <target>");
                }
                return new Look(tokens.get(1).getValue());
            case DROP:
                if(tokens.size() < 2 || tokens.get(1).getTokenType() != TokenType.VAR){
                    throw new CommandErrorException("Enter the 'drop' command in the order: drop <item>");
                }
                return new Drop(tokens.get(1).getValue());
            case STATUS:
                if(tokens.size() ==1){
                    return new Status(null);
                }
                if(tokens.get(1).getTokenType() == TokenType.EOL){
                    return new Status(null);
                }
                if(tokens.get(1).getTokenType() == TokenType.VAR){
                    return new Status(tokens.get(1).getValue());
                }
    
            case QUIT:
                if(tokens.size() == 1){
                    return new Quit();
                }
                if(tokens.get(1).getTokenType() == TokenType.EOL){
                    return new Quit();
                }

            case HELP:
               if (tokens.size() == 1) {
                    return new Help(null); //returns general help
                }
                if (tokens.get(1).getTokenType() == TokenType.EOL) {
                    return new Help(null); //returns general help
                }
                else{
                    String topic = tokens.get(1).getValue().toLowerCase();
                    return new Help(topic); //returns topic to the help class
                }
            default:
                throw new CommandErrorException("Unknown command entered");
        }
    }

 
}
