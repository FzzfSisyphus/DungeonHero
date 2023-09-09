package unsw.dungeon;

public class Exit extends Entity implements Item {
    public Exit(int x, int y){
        super(x,y);
        super.setType("exit");
        super.setToremove(false);
    }

    @Override
    public boolean ifmeet(int player_x, int player_y) {
        if(this.getX() == player_x && this.getY() == player_y){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void function(Player p) {
        System.out.println("Player successfully gets to the exit");
        p.Complete("exit");

    }

    
    
}