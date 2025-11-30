package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 * 
 * <p>
 * The move command checks if the specified direction corresponds to an available exit in the current room.
 * If a matching exit is found, the player's location is updated to the connected room.
 * </p>
 */
public class Move extends Command {
    private String direction;

    public Move(String direction){
        this.direction = direction;
        this.commandType = commandType.MOVE;
    }
    public String execute(GameState gameState){
        Map map = gameState.getMap();
        Room currentRoom = map.getCurrentRoom();
       
        for(Exit ex: currentRoom.getExits()){
            if(ex.getName().toLowerCase().equals(direction.toLowerCase()) && !ex.getHidden()){
                 String nextRoomById = ex.getNextRoom();
                Room nextRoom = map.getRoomById(nextRoomById);
                if(nextRoomById.equals("s")){
                    if(nextRoom.isLocked()){
                        return "The storage room is locked.Use the lockpick to open the door";
                    }
                
            }
                if(nextRoomById.equals("o")){
                    if(nextRoom.isLocked()){
                        return "The office is locked.Use the keycard to open it.";
                    }
            }
                map.setCurrentRoom(nextRoomById);
                gameState.getScore().addVisitRoom(nextRoomById);
            return "Moving towards " + direction + "\n";
            }
            
    }
        return "No exit found in that direction.";
    }

    public String toString(){
        return "Move " + direction;
    }
}
