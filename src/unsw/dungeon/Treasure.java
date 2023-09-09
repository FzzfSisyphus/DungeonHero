package unsw.dungeon;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Treasure extends Entity implements Item {
    private static int total_amount;
    public boolean player_pick = false;
    public boolean other_pick = false;
    public Treasure (int x, int y){
        super(x,y);
        super.setType("treasure");
        this.player_pick = false;
        this.other_pick = false;
        total_amount +=500;
    }
    
    @Override
    public  boolean ifmeet(int player_x,int player_y){
        if(this.getX() == player_x && this.getY() == player_y){
            player_pick = true;
            return true;
        }else{
            if (other_pick == true){
                return true;
            }else{
                return false;

            }
        }
    }
    
    @Override
    public void function(Player p){
        if (this.player_pick == true){
            p.addTreasure(500);  // set the default value of treasure is 500 
            p.setTextP("Pick up treasure ! +500");
            if (p.getTreasure() >= total_amount){
                p.Complete("treasure");
            }

        }
    }



}