package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the drop command, allowing the player to drop an item from their inventory into the current room.
 * 
 * <p>
 * This command checks if the player possesses the specified item and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item, an error message is returned.
 * </p>
 */
public class Drop extends Command {
    private String item;

   public Drop(String item){
       this.item = item;
       this.commandType = commandType.DROP;
   }
    public String execute(GameState gameState){
        Player player = gameState.getPlayer();
        Map map = gameState.getMap();
        Room currentRoom = map.getCurrentRoom();

        Item itemToDrop = player.getItem(item);
        Equipment equipmentToDrop = player.getEquipment(item);

        if(itemToDrop != null){
            player.getInventory().remove(itemToDrop);
            currentRoom.addItem(itemToDrop);
            return "You drop: " + item;
        }
            else if(equipmentToDrop != null){
                player.getEquipment().remove(equipmentToDrop);
                currentRoom.addEquipment(equipmentToDrop);
                return "You drop: " + item;
            }
        else{
            return "You cannot drop " + item;
        }
        
    }
    public String toString(){
        return "Drop " + item;
    }
}
