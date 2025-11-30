package org.uob.a2.utils;

import org.uob.a2.gameobjects.*;
import java.util.Set;
import java.util.HashSet;

public class Score{
    private int totalScore;
    private Set<String> visitedRooms;
    private final static int ROOM_VALUE = 10; //points awarded per room visit

    public Score(){
        this.totalScore = 0; //score is initialized to zero
        this.visitedRooms = new HashSet<>(); //empty set for tracking visited rooms
    }
    //add points for visiting a new room, points added if room hasn't been visited before 
    public void addVisitRoom(String roomId){
        if(!visitedRooms.contains(roomId)){
            visitedRooms.add(roomId);
            totalScore += ROOM_VALUE;
        }
    }
    public void addUseOnTarget(){
        totalScore += ROOM_VALUE;
    }
    public void subtractUseOnTarget(){
        totalScore -= ROOM_VALUE;
    }
    public int getScore(){
        return totalScore;
    }
}