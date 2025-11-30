package org.uob.a2.commands;
import org.uob.a2.utils.Score;
import org.uob.a2.gameobjects.*;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 * 
 * <p>
 * The status command can display a list of items in the player's inventory, 
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command {
    private String topic;

    public Status(String topic){
        this.topic = topic;
        this.commandType = commandType.STATUS;
    }
    public String execute(GameState gameState){
        Player player = gameState.getPlayer();
        switch(topic){
            case "inventory":
                 StringBuilder inventory = new StringBuilder();
            for (Item item : player.getInventory()) {
                inventory.append(item.getId() + ": " + item.getName()).append("\n");
            }
            for (Equipment equipment : player.getEquipment()) {
                inventory.append(equipment.getId() + ": " + equipment.getName()).append("\n");
            }  
                 return "You have the following:\n" + inventory.toString();    
            case "player":
                return player.toString();
            case "score":
                Score obj = gameState.getScore();
                return "Score: " + obj.getScore();
            case "map":
                StringBuilder output = new StringBuilder();
                output.append(gameState.getMap().displayMap()).append("\n");
                Score score = gameState.getScore();
                score.subtractUseOnTarget();
                return output.toString();
            default :
                Item item = player.getItem(topic);
                if(item != null){
                    return item.getDescription();
                }
                    Equipment equipment = player.getEquipment(topic);
                if(equipment != null){
                    return equipment.getDescription();
                }
                return "";
        }
    }
    public String toString(){
        return "Status command for topic: " + topic;
    }
}
