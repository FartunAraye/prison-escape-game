package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents the player in the game, including their name, inventory, and equipment.
 * 
 * <p>
 * The player can carry items and equipment, interact with the game world, and perform
 * actions using their inventory or equipment.
 * </p>
 */
public class Player {
    private ArrayList<Equipment> equipment = new ArrayList<>();
    private ArrayList<Item> inventory = new ArrayList<>();
    private String name;

    public Player(String name){
        this.name = name;
    }
    public Player(){
        this.name = "Unrecognized";
    }
    public String getName(){
        return name;
    }
    public ArrayList<Item> getInventory(){
        return inventory;
    }
    public boolean hasItem(String itemName){
        for(Item item: inventory){
            if(item.getName().equals(itemName)){
                return true;
            }
        }
        return false;
    }
    public Item getItem(String itemName){
        for(Item item: inventory){
            if(item.getName().equals(itemName)){
                return item;
            }
        }
        return null;
    }
    public void addItem(Item item){
        inventory.add(item);
    }
    public ArrayList<Equipment> getEquipment(){
        return equipment;
    }
    public boolean hasEquipment(String equipmentName){
        for(Equipment equip: equipment){
            if(equip.getName().toLowerCase().equals(equipmentName.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public Equipment getEquipment(String equipmentName){
        for(Equipment equip: equipment){
            if(equip.getName().equals(equipmentName)){
                return equip;
            }
        }
        return null;
    }
    public void addEquipment(Equipment equip){
        equipment.add(equip);
    }
    
    /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, and equipment descriptions.
     *
     * @return a string describing the player, their inventory, and equipment
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nInventory:\n");
        for (Item i : this.inventory) {
            out.append("- ").append(i.getDescription()).append("\n");
        }
        out.append("Equipment:\n");
        for (Equipment e : this.equipment) {
            out.append("- ").append(e.getDescription()).append("\n");
        }
        return out.toString();
    }
}
