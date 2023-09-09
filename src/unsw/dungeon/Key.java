package unsw.dungeon;

public class Key extends Entity implements Item{
    private int door;
    
    public Key(int x, int y,int id){
        super(x,y);
        super.setType("key");
        this.door = id; 
    }

    public void setDoor(int d){
        door = d;
    }
    
    public int getDoor() {
        return door;
    }

    @Override
    public  boolean ifmeet(int player_x,int player_y){
        if(this.getX() == player_x && this.getY() == player_y){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public void function(Player p){
        if (p.getKey() == null){
            p.setKey(this);
            super.setToremove(true);
            p.setTextP("key pick up! try to open the door");
            p.setKeyProp(this);
        }else{
            super.setToremove(false);
            p.setTextP("You already have a key, id: "+ p.getKey().getDoor());
        }
    }

    
}