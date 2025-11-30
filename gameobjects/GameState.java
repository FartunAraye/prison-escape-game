package org.uob.a2.gameobjects;

import org.uob.a2.utils.Score;

/**
 * Represents the current state of the game, including the map and the player.
 * 
 * <p>
 * The game state contains all necessary information about the game's progress, such as
 * the player's status and the state of the game map.
 * </p>
 */
public class GameState {
    private Map map;
    private Player player;
    private Score score;

    public GameState(Map map, Player player){
        this.map = map;
        this.player = player;
        this.score = new Score();
    }
    public GameState(){
        this.score = new Score();
    }
    public Map getMap(){
        return map;  
    }
    public Player getPlayer(){
        return player;
    }
    public Score getScore(){
        return score; 
    }
    /**
     * Returns a string representation of the game state, including the map and player details.
     *
     * @return a string describing the game state
     */
    @Override
    public String toString() {
        return "GameState {" +
               "map=" + (map != null ? map.toString() : "null") + ", " +
               "player=" + (player != null ? player.toString() : "null") +
               '}';
    }
}
