package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the get command, allowing the player to pick up an item from the current room and add it to their inventory.
 * 
 * <p>
 * This command checks if the specified item is present in the current room. If the item exists and the player
 * does not already have it, the item is added to the player's inventory and removed from the room. Otherwise,
 * an appropriate message is returned.
 * </p>
 */
public class Get extends Command {
    private String item;

    public Get(String item){
        this.item = item;
        this.commandType = CommandType.GET;
    }
    public String execute(GameState gameState){
        Player player = gameState.getPlayer();
        Map map = gameState.getMap();
        Room currentRoom = map.getCurrentRoom();

        if(player.hasItem(item) || player.hasEquipment(item)){
            return "You already have " + item;
        }

        Item itemToGet = currentRoom.getItemByName(item);
        if(itemToGet != null){
            currentRoom.removeItem(itemToGet);
            player.addItem(itemToGet);
            return "You pick up: " + item;
        }

        Equipment equipmentToGet = currentRoom.getEquipmentByName(item);
        if(item.equals("key")){
            if(equipmentToGet.getHidden()){
                return "You should solve the puzzle to get the key";
            }
        }
        if(equipmentToGet != null){
            currentRoom.getEquipments().remove(equipmentToGet);
            player.addEquipment(equipmentToGet);
            return "You pick up: " + item;
        }
        return "No " + item + " to get.";
    }
    public String toString(){
        return "Get " + item;
    }
}
