package org.uob.a2.gameobjects;

public class Equipment extends GameObject implements Usable {
    protected UseInformation useInformation;

    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation){
        super(id, name, description, hidden);
        this.useInformation = useInformation;
    }
    public void setUseInformation(UseInformation useInformation){
        this.useInformation = useInformation;
    }
    public UseInformation getUseInformation(){
        return useInformation;
    }
    public String use(GameObject target, GameState gameState){
    if (target == null) {
        return "Invalid target for equipment usage.";
    }
    
    if (!target.getId().equals(useInformation.getTarget())) {
        return "You cannot use this equipment on " + target.getName();
    }
    
    Room currentRoom = gameState.getMap().getCurrentRoom();
    GameObject itemToReveal = null;
    
    if (!currentRoom.getItems().isEmpty()) {
        for (GameObject item : currentRoom.getItems()) {
            if (item.getId().equals(useInformation.getResult())) {
                itemToReveal = item;
                break;
            }
        }
        
        if (itemToReveal != null) {
            itemToReveal.setHidden(false);
        }
    }
    
    useInformation.setUsed(true);
    return useInformation.getMessage();
}


   
    /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */
    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }
}
