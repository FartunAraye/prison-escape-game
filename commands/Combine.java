package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

public class Combine extends Command {
    private String item1;
    private String item2;

    public Combine(String item1, String item2){
        this.item1 = item1;
        this.item2 = item2;
        this.commandType = commandType.COMBINE;
    }
    public String execute(GameState gameState){
        Player player = gameState.getPlayer();

        //check if player has first item
        if(!player.hasItem(item1)){
            return "You don't have " + item1;
        }

        //check if player has second item
        if(!player.hasItem(item2)){
            return "You don't have " + item2;
        }

        //gets the items from inventory
        Item firstItem = player.getItem(item1);
        Item secondItem = player.getItem(item2);

        //try to combine the items
        Equipment result = combinedItems(firstItem, secondItem);
        if(result == null){
            return "You can't combine this items.";
        }

        //remove used items from inventory
        player.getInventory().remove(firstItem);
        player.getInventory().remove(secondItem);
        player.addEquipment(result);

        return "You combined " + item1 + " and " + item2 + " to create " + result.getName();
    }
    //checks valid combinations and creates resulting equipment
    public Equipment combinedItems(Item item1, Item item2){
        String combination = item1.getName().toLowerCase() + ":" + item2.getName().toLowerCase();
        String reverseCombination = item2.getName().toLowerCase() + ":" + item1.getName().toLowerCase();

        //check if items can be combined to make lockpick
        if(combination.equals("bobbypin:hairpin") || combination.equals("hairpin:bobbypin") || reverseCombination.equals("hairpin:bobbypin") || reverseCombination.equals("bobbypin:hairpin")){
            Equipment lockpick = new Equipment("lockpick", "lockpick", "A lockpick", false, new UseInformation(false, "picks a lock", "storage room", "lock picked", "You have opened the storage room using the lockpick."));

            return lockpick;
        }
        return null;
    }
   
    
    
    @Override
public String toString(){
    return "Combine " + item1 + " and " + item2;
}
}








