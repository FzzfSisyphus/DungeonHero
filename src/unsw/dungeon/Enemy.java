package unsw.dungeon;

import java.lang.annotation.Target;
import java.time.LocalTime;

public class Enemy extends Entity implements Item{
    
    private Dungeon dungeon; 
    private LocalTime limit; 
    private int times; 
    public Enemy(int x, int y, Dungeon dungeon){
        super(x,y, new NotBlockBehavior());
        super.setType("enemy");
        super.setToremove(false);
        this.dungeon = dungeon;
        this.limit = null ;
        this.times = 0 ;
    }
    
    public boolean swordCheck(int player_x, int player_y){   

        if(player_x >=(this.getX() - 1) && player_x <= (this.getX() +1) ){           
            if(player_y >=(this.getY()-1) && player_y <= (this.getY()+1) ){
                return true; // in the range 
            }
        }
        return false; //out of range                               
    }


/////////////// enemy track player 
    public void tracPlayer(int x ,int y,Dungeon dungeon){     
        if(limit == null){
            if(x != getX()){
                xmove(x, y, dungeon);
            }
            if(y != getY()){
                ymove(x, y, dungeon);
            }
        } else if (limit.isBefore(LocalTime.now()) ) {
            if(x != getX()){
                xmove(x, y, dungeon);
            }
            if(y != getY()){
                ymove(x, y, dungeon);
            }
        }else{
            potionMove(x,y,dungeon);
        }
        
    }    
    
    public void xmove(int playerX,int playerY,Dungeon dungeon){       
        if(this.getX() > playerX ){
            if(!dungeon.ifBlockPlayer(getX() - 1 , getY(), "left") ){
                x().set(getX() - 1);
                //System.out.print("-->left");
            }
        }else{
            if(!dungeon.ifBlockPlayer(getX() + 1 , getY(), "right") ){
                //System.out.print("-->right");                
                x().set(getX() + 1);
            }
        }  
    }

    public void ymove(int playerX,int playerY,Dungeon dungeon){
        if(this.getY() > playerY ){
            if(!dungeon.ifBlockPlayer(getX(), getY()-1, "up") ){
                //System.out.print("-->up");                
                y().set(getY() - 1); 
            }
        }else{
            if(!dungeon.ifBlockPlayer(getX(), getY()+1, "down") ){
                //System.out.print("-->down");             
                y().set(getY() + 1);                 
            }
        } 
   
    }

    public void potionMove(int x, int y , Dungeon d){
        int dx = dungeon.getWidth() - x ; 
        int dy = dungeon.getHeight() - y;
        xmove(dx,dy,d);
        ymove(dx,dy,d);            
    }

//////// potion function  
    public void stop(LocalTime t,int x ,int y){
        limit = t; 
        tracPlayer(this.getX(),this.getY(),this.dungeon);   // stop at same positon  
    }

    
    @Override
    public  boolean ifmeet (int player_x,int player_y) { 
        // every step of player will be record for enemys
        if (times == 1) {
            tracPlayer(player_x, player_y,this.dungeon); 
            times = 0;
        }else{
            times += 1 ;
        }

        if ((player_x == this.getX()) && (player_y == this.getY())){
            return true;
        }else {
            return false;
        }       
    }
    
    @Override
    public void function(Player p){
        if (p.getPotion() != null){
            super.setToremove(true);
            p.setTextP("Awesome! You kill the enemy because of invincibility!");
            p.addHaveKilled(1);
        }else{
            System.out.println("die   enemey: " + getX() + getY());
            p.setTextP("You are killed by the enemy!!!!!!!");
            dungeon.setIfover(true);
            p = null;  // kill the player

        }
    
    }

}