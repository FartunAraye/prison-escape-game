package org.uob.a2.commands;
import org.uob.a2.utils.Score;
import org.uob.a2.gameobjects.*;

/**
 * Represents the quit command, allowing the player to exit the game.
 * 
 * <p>
 * The quit command signals the end of the game session and provides a summary of the player's
 * current status before termination.
 * </p>
 */
public class Quit extends Command {

    public Quit(){
        this.commandType = commandType.QUIT;
    }
    public String execute(GameState gameState){
        StringBuilder result = new StringBuilder();
        result.append("Game over:\n");

        Score score = gameState.getScore();
        int gameScore = score.getScore();
        result.append("End score: " + gameScore + "\n");
        
        Player player = gameState.getPlayer();
        if (!player.getInventory().isEmpty()) {
            result.append("You had: ");
            for (GameObject item : player.getInventory()) {
                result.append(item.getName().toLowerCase());
                result.append(" ");
            }
        }
        
        return result.toString().trim();
    }
}
