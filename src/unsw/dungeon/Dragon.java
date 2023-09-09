package unsw.dungeon;

import java.util.Random;

public class Dragon extends Enemy {
    private int times;
    private Dungeon dungeon;
    private int amount; 

    public Dragon(int x, int y, Dungeon dungeon){    
        super(x,y,dungeon);
        this.dungeon = dungeon;
        this.amount = 0;
    }

    public void collect(){
        for (Entity e:dungeon.getEntity(getX(),getY()) ){
            if (e instanceof Treasure){
                Treasure t = (Treasure) e;
                System.out.println(amount);
                amount += 500;
                e.remove();
                e.setToremove(true);
                t.other_pick = true;
                
            }
        }
    }
    
    public void die(){
        Player p = dungeon.getPlayer();
        p.addTreasure(amount);  // set the default value of treasure is 500 
    }

    @Override
    public void tracPlayer(int x ,int y,Dungeon dungeon){          
        if(x != getX()){
            xmove(x, y, dungeon);
        }
        if(y != getY()){
            ymove(x, y, dungeon);
        }
    }    

    @Override
    public  boolean ifmeet (int player_x,int player_y) { 
        Random t = new Random(); 
        int tx = t.nextInt(dungeon.getWidth());
        int ty = t.nextInt(dungeon.getHeight());
        collect();

        if (times == 1) {
            tracPlayer(tx,ty,this.dungeon); 
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
            p.setTextP("Awesome! You kill the dragon because of invincibility!");
            this.die();
            p.addHaveKilled(1);
        }else{
            System.out.println("die   enemey: " + getX() + getY());
            p.setTextP("You are killed by the dragon!!!!!!!");
            dungeon.setIfover(true);
            p = null;  // kill the player

        }
    
    }

}