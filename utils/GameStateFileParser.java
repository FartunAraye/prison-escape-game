package org.uob.a2.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.uob.a2.gameobjects.*;

/**
 * Utility class for parsing a game state from a file.
 * 
 * <p>
 * This class reads a structured text file to create a {@code GameState} object,
 * including the player, map, rooms, items, equipment, features, and exits.
 * </p>
 */
public class GameStateFileParser {
    public GameStateFileParser(){
        
    }
    public static GameState parse(String filename){
        Player player = null;
        Map map = new Map();
        Room currentRoom = null;
        String startRoom = null;

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = reader.readLine())!= null){
                line = line.trim();
                
                if(line.startsWith("player:")){
                    player = new Player(line.substring(7).trim());
                }
                else if(line.startsWith("map:")){
                    startRoom = line.substring(4).trim();
                }
                else if(line.startsWith("room:")){
                    String[] roomContent = line.substring(5).split(",",4);
                    if(roomContent.length == 4){
                    String roomId = roomContent[0].trim();
                    String roomName = roomContent[1].trim();
                    String roomDescription = roomContent[2].trim();
                    boolean roomHidden = Boolean.parseBoolean(roomContent[3].trim());

                    currentRoom = new Room(roomId, roomName, roomDescription, roomHidden);
                    map.addRoom(currentRoom);
                    }
                    else{
                        System.err.println("Skipping invalid room line: " + line);
                    }
                }
                else if(line.startsWith("equipment:")){
                    String[] equip = line.substring(10).split(",",9);
                    if (equip.length >= 5) {
                    String equipId = equip[0].trim();
                    String equipName = equip[1].trim();
                    String equipDescription = equip[2].trim();
                    boolean equipHidden = Boolean.parseBoolean(equip[3].trim());
                    UseInformation useInfo = null;
                        if(equip.length >= 8){
                    String action = equip[4].trim();  
                    String target = equip[5].trim();  
                    String result = equip[6].trim();  
                    String message = equip[7].trim();
                    useInfo = new UseInformation(false, action, target, result, message);
                        }

                    Equipment equipment = new Equipment(equipId, equipName, equipDescription, equipHidden, useInfo);
                    currentRoom.addEquipment(equipment);
                    }else{
                        System.err.println("Skipping invalid equipment line: " + line);
                    }
                }
                else if(line.startsWith("item:")){
                    String[] items = line.substring(5).split(",",4);
                    if(items.length == 4){
                    String itemId = items[0].trim();
                    String itemName = items[1].trim();
                    String itemDescription = items[2].trim();
                    boolean itemHidden = Boolean.parseBoolean(items[3].trim());

                    Item item = new Item(itemId, itemName, itemDescription, itemHidden);
                    currentRoom.addItem(item);
                    }else{
                        System.err.println("Skipping invalid item line: " + line);
                    }
                }
                else if(line.startsWith("feature:")){
                    String[] features = line.substring(8).split(",",4);
                    if(features.length == 4){
                    String featureId = features[0].trim();
                    String featureName = features[1].trim();
                    String featureDescription = features[2].trim();
                    boolean featureHidden = Boolean.parseBoolean(features[3].trim());

                    Feature feature = new Feature(featureId, featureName, featureDescription, featureHidden);
                    currentRoom.addFeature(feature);
                    }else{
                        System.err.println("Skipping invalid feature line: " + line);
                    }
                }
                else if(line.startsWith("container:")){
                    String[] containers = line.substring(10).split(",",4);
                    if(containers.length == 4){
                    String containerId = containers[0].trim();
                    String containerName = containers[1].trim();
                    String containerDescription = containers[2].trim();
                    boolean containerHidden = Boolean.parseBoolean(containers[3].trim());

                    Container container = new Container(containerId, containerName, containerDescription, containerHidden);
                    currentRoom.addFeature(container);
                    }else{
                        System.err.println("Skipping invalid container line: " + line);
                    }
                }
                else if(line.startsWith("exit:")){     
                    String[] exits = line.substring(5).split(",",5);
                    if(exits.length == 5){
                    String exitId = exits[0].trim();
                    String exitName = exits[1].trim();
                    String exitDescription = exits[2].trim();
                    String exitNextRoom = exits[3].trim();
                    boolean exitHidden = Boolean.parseBoolean(exits[4].trim());

                    Exit exit = new Exit(exitId, exitName, exitDescription, exitNextRoom, exitHidden);
                    currentRoom.addExit(exit);
                    }else{
                        System.err.println("Skipping invalid exit line: " + line);
                    }
                }
        }
            
            if(startRoom != null){
                 map.setCurrentRoom(startRoom);
            }
    }
        catch(IOException e){
            e.printStackTrace();
        }
        
        return new GameState(map, player);

   
}
}
