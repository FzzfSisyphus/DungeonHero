package unsw.dungeon;

public class WallBlockBehavior implements BlockBehavior{
    
    @Override
    public boolean ifBlockPlayer(int x, int y,int player_x, int player_y, String direction){
        if ((player_x == x) && (player_y == y)){
            return true;
        }else {
            return false;
        }

    }
    
}