package unsw.dungeon;

interface Item {
    public  boolean ifmeet(int player_x,int player_y);
    public void function(Player p);  
}
