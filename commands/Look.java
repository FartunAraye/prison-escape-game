package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command {
    private String target;
    
    public Look(String target){
        this.target = target;
        this.commandType = commandType.LOOK;
    }
    public String execute(GameState gameState){
        Player player = gameState.getPlayer();
        Map map = gameState.getMap();
        Room currentRoom = map.getCurrentRoom();

        if (currentRoom == null || target == null){
            return "";
        }
        
        switch(target){
            case "room" :
                StringBuilder roomDescription = new StringBuilder(currentRoom.getDescription()).append("\n");
                for(Feature feature : currentRoom.getFeatures()){
                    if(!feature.getHidden()){
                        roomDescription.append(feature.getDescription()).append("\n");
                    }
                }
                for(Item item : currentRoom.getItems()){
                    if(!item.getHidden()){
                        roomDescription.append(item.getDescription()).append("\n");
                    }
                }
                for(Equipment equipment : currentRoom.getEquipments()){
                    if(!equipment.getHidden()){
                        roomDescription.append(equipment.getDescription()).append("\n");
                    }
                }
                return roomDescription.toString().trim();
            case "exits" :
                return getExits(currentRoom);
            case "features" :
                return getFeatures(currentRoom);
            default:
                Exit exit = currentRoom.getExit(target);
                if (exit != null && !exit.getHidden()) {  
                    return exit.getDescription();
                }

                Feature feature = currentRoom.getFeatureByName(target);
                if (feature != null && !feature.getHidden()) {  
                    return feature.getDescription();
                }

                Item item = currentRoom.getItemByName(target);
                if (item != null && !item.getHidden()) {  
                    return item.getDescription();
                }

                Equipment equipment = player.getEquipment(target);
                if (equipment != null && !equipment.getHidden()) {  
                    return equipment.getDescription();
                }

                return "";
        }
    }
    
    private String getExits(Room room) {
        StringBuilder exits = new StringBuilder("The available exits are:\n");
        boolean hasVisibleExits = false;
        for (Exit exit : room.getExits()) {
            if (!exit.getHidden()) {  
                exits.append(exit.getDescription()).append("\n");
                hasVisibleExits = true;
            }
        }
        return hasVisibleExits ? exits.toString().trim() : "";
    }

    private String getFeatures(Room room) {
        StringBuilder features = new StringBuilder("You also see:\n");
        boolean hasVisibleFeatures = false;
        for (Feature feature : room.getFeatures()) {
            if (!feature.getHidden()) {  
                features.append(feature.getId() + ":" + feature.getName() + "\n");
                hasVisibleFeatures = true;
            }
        }
        return hasVisibleFeatures ? features.toString().trim() : "";
    }
    public String toString(){
        return "Look " + target;
    }
}
