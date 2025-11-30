package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents a room in the game, which is a type of {@code GameObject}.
 * 
 * <p>
 * Rooms can have items, equipment, features, and exits. They also manage navigation
 * and interactions within the game world.
 * </p>
 */
public class Room extends GameObject {
    private ArrayList<Exit> exits = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Feature> features = new ArrayList<>();
    private ArrayList<Equipment> equipment = new ArrayList<>();
    private boolean isLocked;
    

    public Room(String id, String name, String description, boolean hidden){
        super(id, name, description, hidden);
        this.isLocked = id.equals("s") || id.equals("o") || id.equals("m");
    }
    public Room(){
        super();
    }
    public boolean isLocked(){
        return isLocked;
    }
    public void unlock(){
        isLocked = false;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public ArrayList<Exit> getExits(){
        return exits;
    }
    public void addExit(Exit exit){
        exits.add(exit);
    }
    public ArrayList<Item> getItems(){
        return items;
    }
    public Item getItem(String id){
        for(Item item: items){
            if(item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }
    public Item getItemByName(String name){
        for(Item item:items){
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }
    public Feature getFeatureByName(String name){
        for(Feature feature:features){
            if(feature.getName().equals(name)){
                return feature;
            }
        }
        return null;
    }
    public Equipment getEquipmentByName(String name){
        for(Equipment equip:equipment){
            if(equip.getName().equals(name)){
                return equip;
            }
        }
        return null;
    }
    public ArrayList<Equipment> getEquipments(){
        return equipment;
    }
    public Equipment getEquipment(String id){
        for(Equipment equip: equipment){
            if(equip.getId().equals(id)){
                return equip;
            }
        }
        return null;
    }
    public Exit getExit(String id){
        for(Exit exit: exits){
            if(exit.getId().equals(id)){
                return exit;
            }
        }
        return null;
    }
    public void addEquipment(Equipment equip){
        equipment.add(equip);
    }
    public Feature getFeature(String id){
        for(Feature feature: features){
            if(feature.getId().equals(id)){
                return feature;
            }
        }
        return null;
    }
    public void addItem(Item item){
        items.add(item);
    }
    public void removeItem(Item item){
        items.remove(item);
    }
    public ArrayList<Feature> getFeatures(){
        return features;
    }
    public ArrayList<GameObject> getAll(){
        ArrayList<GameObject> objectsInRoom = new ArrayList<>();
        objectsInRoom.addAll(items);
        objectsInRoom.addAll(features);
        objectsInRoom.addAll(equipment);
        objectsInRoom.addAll(exits);
        return objectsInRoom;
    }
    public void addFeature(Feature feature){
        features.add(feature);
    }
    public boolean hasItem(String itemName){
        for(Item item: items){
            if(item.getName().equals(itemName)){
                return true;
            }
        }
        return false;
    }
    public boolean hasEquipment(String name){
        for(Equipment equip: equipment){
            if(equip.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a string representation of the room, including its contents and description.
     *
     * @return a string describing the room
     */
    @Override
    public String toString() {
        String out = "[" + id + "] Room: " + name + "\nDescription: " + description + "\nIn the room there is: ";
        for (Item i : this.items) {
            out += i + "\n";
        }
        for (Equipment e : this.equipment) {
            out += e + "\n";
        }
        for (Feature f : this.features) {
            out += f + "\n";
        }
        for (Exit e : this.exits) {
            out += e + "\n";
        }
        return out + "\n";
    }
}
