package unsw.dungeon;

import java.util.ArrayList;

public class SwitchFloor extends Entity implements Item {
    private Dungeon dungeon;
    private ArrayList<Door> door;
    private boolean iftriggered;
    public SwitchFloor(int x, int y,Dungeon dungeon) {
        super(x, y);
        this.dungeon = dungeon;
        this.door = new ArrayList<Door> ();
    
        super.setToremove(false);
        this.iftriggered = ifmeet(0, 0);

        for (Entity e: dungeon.getAllEntities()){
            if(e instanceof Door){
                addDoor((Door) e);
            }
        }
    }
    public void addDoor(Door door){
        if (this.door != null){
            this.door.add(door);
        }
        
    }

    public void openDoor(){
        if (this.door != null){
            for (Door d: this.door){
                d.open();
            }
        }
    }

    public void closeDoor(){
        if(this.door != null){
            for (Door d: this.door){
                d.close();
            }
        }
    }
    
    @Override
    public  boolean ifmeet(int player_x,int player_y){ 
        for (Entity entity: dungeon.getAllEntities()){
            if(entity != null && entity instanceof Boulder){
                
                
                if(entity.getX() == this.getX() && 
                    entity.getY() == this.getY()){
                        return true;
                }
                
            }
        }
        return false;
    }
    
    @Override
    public void function(Player p){
        openDoor();
        
        boolean ifAlltriggered = true;
        for (Entity entity: dungeon.getAllEntities()){
            if(entity != null && entity instanceof SwitchFloor){
                SwitchFloor switchfloor = (SwitchFloor) entity;
                
                if (!switchfloor.ifmeet(p.getX(), p.getY())){
                    ifAlltriggered = false;
                }
                
            }
        }
        if (ifAlltriggered){
            p.Complete("boulders");
        }

    }

}