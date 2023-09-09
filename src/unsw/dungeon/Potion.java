package unsw.dungeon;

public class Potion extends Entity implements Item{
    public Potion(int x, int y){
        super(x,y);
        super.setType("potion");
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
        p.setTextP("enemy will run away for 60s, starting now");
        p.startPotion();
    }
    

}