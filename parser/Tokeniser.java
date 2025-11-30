package org.uob.a2.parser;

import java.util.ArrayList;

/**
 * The {@code Tokeniser} class is responsible for converting a string input into a list of tokens
 * that can be parsed into commands by the game.
 * 
 * <p>
 * The tokeniser identifies keywords, variables, and special symbols, assigning each a {@code TokenType}.
 * </p>
 */
public class Tokeniser {
    private ArrayList<Token> tokens;

    public Tokeniser(){
        this.tokens = new ArrayList<Token>();
    }
    public ArrayList<Token> getTokens(){
        return tokens;
    }
    public String sanitise(String s){
        return s.trim().toLowerCase();
    }
    public void tokenise(String s){
        tokens.clear(); //clears any previous tokens
        String input = sanitise(s);
        String[] parts = input.split("\\s+"); //splits on whitespace

          //handling for use and combine commands
         if (parts.length > 2 && (parts[0].equals("use") || parts[0].equals("combine"))) {
             tokeniseCommand(input);
}
        else{ //tokenization for other commands
        for(String value : parts){
            TokenType tokenType = identifyToken(value);
            tokens.add(new Token(tokenType, value));
        }
            tokens.add(new Token(TokenType.EOL, ""));
        }
    }
    private void tokeniseCommand(String input) {
        String[] parts = input.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            TokenType currentType = identifyToken(part);
            
            if (i == 0) {
             if (part.equals("use")) {
                 tokens.add(new Token(TokenType.USE, part));  
          } else if (part.equals("combine")) {
                 tokens.add(new Token(TokenType.COMBINE, part));        
                } 
                }
            else if (i == 2 && currentType == TokenType.PREPOSITION) {
                tokens.add(new Token(TokenType.PREPOSITION, part));
            } 
            else {
                tokens.add(new Token(currentType, part));
            }
        }
        tokens.add(new Token(TokenType.EOL, ""));
    }
    private TokenType identifyToken(String value){
        switch(value){
            case "use":
                return TokenType.USE;
            case "combine":
                return TokenType.COMBINE;
            case "get":
                return TokenType.GET;
            case "drop":
                return TokenType.DROP;
            case "look":
                return TokenType.LOOK;
            case "status":
                return TokenType.STATUS;
            case "help":
                return TokenType.HELP;
            case "quit":
                return TokenType.QUIT;
            case "move":
                return TokenType.MOVE;
            case "on":
            case "with":
            case "using":
            case "and":
                return TokenType.PREPOSITION;
            default:
                return TokenType.VAR;
        }
    }
}
