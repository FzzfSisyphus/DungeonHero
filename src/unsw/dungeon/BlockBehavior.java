package unsw.dungeon;

public interface BlockBehavior {
    public boolean ifBlockPlayer(int x, int y, int player_x, int player_y, String direction);
}