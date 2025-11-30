package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.io.*;
import java.nio.file.*;

import org.uob.a2.utils.*;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map {
    Room currentRoom;
    ArrayList<Room> rooms = new ArrayList<>();
    Set<String> visitedRooms;

    public Map(){
        this.visitedRooms = new HashSet<>();
    }
    public Room getCurrentRoom(){
        return currentRoom;
    }
    public Room getRoomById(String id){
         for (Room room : rooms) { 
              if (room.getId().equals(id)) {
                  return room;
        }
    }
    return null; 
}
    public void addRoom(Room room){
        rooms.add(room);
    }
    public void setCurrentRoom(String roomId){
        for(Room room: rooms){
            if(room.getId().equals(roomId)){
                currentRoom = room;
                visitedRooms.add(roomId);
                return;
            }
        }
    }
    private String getSymbol(String roomId){
        if(visitedRooms.contains(roomId)){
            return roomId;
        }
        return ".";
    }
    public String displayMap() {
        if (currentRoom == null) {
            return "No current room set.";
        }

        StringBuilder display = new StringBuilder();
        display.append(getSymbol("c")).append("");
        display.append(getSymbol("b")).append("");
        display.append(getSymbol("a")).append("");
        display.append(getSymbol("s")).append("\n");

        display.append(getSymbol("i")).append("");
        display.append(getSymbol("k")).append("");
        display.append(getSymbol("d")).append("");
        display.append(getSymbol("g")).append("\n");

        display.append(getSymbol(" ")).append("");
        display.append(getSymbol("y")).append("");
        display.append(getSymbol("h")).append("");
        display.append(getSymbol("o")).append("\n");

        display.append(getSymbol(" "));
        display.append(getSymbol(" "));
        display.append(getSymbol("m")).append("");
        display.append(getSymbol(" "));

        return display.toString();
    }

  
    /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }
}

