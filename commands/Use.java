package org.uob.a2.commands;
import org.uob.a2.gameobjects.*;

/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 * 
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
 */
public class Use extends Command {
    private String equipmentName;
    private String target;

    public Use(String equipmentName, String target){
        this.equipmentName = equipmentName;
        this.target = target;
        this.commandType = CommandType.USE;
    }
    public String execute(GameState gameState){
        Player player = gameState.getPlayer();
        Room currentRoom = gameState.getMap().getCurrentRoom(); 

            if(!player.hasEquipment(equipmentName)){
                return "You do not have " + equipmentName;
            }
        Equipment equipment = player.getEquipment(equipmentName);

        if (equipment == null) {
            return "You do not have " + equipmentName;
        }
        
        if(equipment.getUseInformation().isUsed()){
            return "You have already used " + equipmentName;
            }
        if(equipmentName.toLowerCase().equals("lockpick") && target.equals("door")){
            UseInformation uI = equipment.getUseInformation();
            if(!uI.isUsed()){
                uI.setUsed(true);
                Room storageRoom = gameState.getMap().getRoomById("s");
                storageRoom.unlock();
                gameState.getScore().addUseOnTarget();
                player.getEquipment().remove(equipment);
                return "You unlock the storage room door with the lockpick";
            }
            return "You've already unlocked the storage room";
        }
        if(equipmentName.toLowerCase().equals("key") && target.equals("gate")){
            UseInformation useI = equipment.getUseInformation();
            if(!useI.isUsed()){
                useI.setUsed(true);
                Room maingate = gameState.getMap().getRoomById("m");
                maingate.unlock();
                return "You unlock the main gate with the key.\nYou have successfully exited the prison. Well Done!";
            }
        }
        if(equipmentName.toLowerCase().equals("keycard") && target.equals("office")){
            UseInformation usI = equipment.getUseInformation();
            if(!usI.isUsed()){
                usI.setUsed(true);
                Room office = gameState.getMap().getRoomById("o");
                office.unlock();
                player.getEquipment().remove(equipment);
                return "You unlock the office with the keycard";
            }
            return "You've already unlocked the office";
        }
        
        GameObject targetFeature = currentRoom.getFeatureByName(target);

        if(targetFeature == null){
            return "Invalid use target";
        }
        
        UseInformation useInfo = equipment.getUseInformation();
        String expectedTarget = useInfo.getTarget();

        if(!targetFeature.getId().equals(expectedTarget) && !targetFeature.getName().equals(useInfo.getTarget())){
        return "Invalid use target";
    }    
        String result = equipment.use(targetFeature, gameState);
        
        return result;
    }
    public String toString(){
        return equipmentName + " on " + target;
    }
}
