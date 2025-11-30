package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 * 
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command {
    private String topic;

    public Help(String topic){
        this.topic = topic;
        this.commandType = CommandType.HELP;
    }
    public String execute(GameState gameState){
        if(topic == null){
            return "Welcome to the game!\n" + "Available inputs:\n" + "- MOVE <direction>\n" + "- HELP\n" + "- LOOK <room,item,feature>\n" + "- GET <item>\n" + "- DROP <item>\n" + "- COMBINE <item1 and item2>\n" + "- STATUS <map,score,inventory,player>\n" + "- USE <equipment on target>\n" + "- QUIT\n" + "map symbols- c - cell, b - bathroom, a - study area, s - storage, i - infirmary, k - kitchen, d - dining area, g - guard room, y - yard, h - hallway, o - office, m - main gate , ..- rooms that are dotted on the map are not accessible, they are maintenance rooms, staff quarters and control rooms.";
        }
        switch(topic){
            case "move":
                return 
                    "MOVE Command:\n" + "Use the 'move' command\n" + "direction";
            case "look":
                return "Use the 'look' command to look around the current room, at a feature, or at a specific item or equipment";
            case "get":
                return "Use the 'get' command to pick up an item or equipment from the current room ";
            case "drop":
                return "Use the 'drop' command to remove an item or equipment from your inventory ";
            case "use":
                return "Use the 'use' command to use an item in your inventory on a target";
            case "status":
                return "Use the 'status' command to check your current status, or inventory. Also able to display the map and your score";
            case "help":
                return "Use the 'help' command to display the help information ";
            case "combine":
                return "Use the 'combine' command to combine two items into a new item or equipment.";
            case "quit":
                return "Use the 'quit' command to exit the game";
            default:
                return "No help available for the topic: " + topic;
        }
    }
    public String toString(){
        return "HELP " + topic;
    }
}
