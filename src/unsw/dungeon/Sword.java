package unsw.dungeon;

public class Sword extends Entity implements Item{

    private int AvailableWield;
    
    public Sword(int x, int y){
        super(x,y);
        this.AvailableWield = 5;
        super.setType("sword");
        super.setToremove(false);
    }


    //return the remaining number that the sword can be wielded
    public void wieldOnce(){
        this.AvailableWield -= 1;
    }

    public int available(){
        return this.AvailableWield;
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
        if(p.PickUpSword(this)){
            super.setToremove(true);
        }
    }


    
}