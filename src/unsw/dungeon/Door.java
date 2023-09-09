package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.BooleanProperty;

public class Door extends Entity implements Item {

    private int id;

    DoorState openstate;
    DoorState closestate;

    DoorState state;

    protected BooleanProperty Isopen;

    public Door(int x, int y, int id) {
        super(x, y, new WallBlockBehavior());
        this.id = id;
        this.openstate = new DoorOpenState(this);
        this.closestate = new DoorCloseState(this);
        this.state = this.closestate;
        super.setToremove(false);

        this.Isopen= new SimpleBooleanProperty();
        this.setIsopen(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public DoorState getOpenstate() {
        return openstate;
    }

    public DoorState getClosestate() {
        return closestate;
    }

    public DoorState getState() {
        return state;
    }

    public void setState(DoorState state) {
        this.state = state;
    }

    public boolean open(Key key) {
        return this.state.open(key);
    }
    
    public boolean open(){
        return this.state.open();
    }
    
    public void UnBlock() {
        this.blockBehavior = new NotBlockBehavior();
        this.setIsopen(true);
    }

    public boolean close() {
        return this.state.close();
    }

    public void Block(){
        this.blockBehavior = new WallBlockBehavior();
        this.setIsopen(false);

    }
      

    @Override
    public void function(Player p) {
        // if(p.getKey().getDoor() == id){
        //     open();
        // }
        
        if (p.getKey() != null){
            if (getState()  == this.closestate){
                if (open(p.getKey())){
            
                    p.setKey(null);
                    p.setTextP("Nice!! You open the door!!! \n NOTICE You don't have key any more");
                } else{
                    p.setTextP("Your key does not match the door. Press 'Q' to discard the key and pick another one");
    
                }

            }else{
                p.setTextP("The door is already open.");
            }
            
        }else{
            if (getState()  == this.closestate){
                p.setTextP("Please pick up the key or push a boulder to any switchfloor to open the door.");
            } else{
                if (!p.getTextP().get().equals("Nice!! You open the door!!! \n NOTICE You don't have key any more.")){
                    p.setTextP("The door is open.");

                }
            }


        }
    }

    @Override
    public boolean ifmeet(int player_x, int player_y) {
        if(player_x >=(this.getX() - 1) && player_x <= (this.getX() +1) ){           
            if(player_y >=(this.getY()-1) && player_y <= (this.getY()+1) ){
                return true; // in the range 
            }
        }       
        return false;
        
    }

    public BooleanProperty getIsopen() {
        return this.Isopen;
    }

    public void setIsopen(boolean isopen) {
        this.Isopen.set(isopen);
    }

    
}