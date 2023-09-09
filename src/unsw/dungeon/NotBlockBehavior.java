package unsw.dungeon;

public class NotBlockBehavior implements BlockBehavior{

    @Override
    public boolean ifBlockPlayer(int x, int y, int player_x, int player_y, String direction){
        return false;
    }
    
}