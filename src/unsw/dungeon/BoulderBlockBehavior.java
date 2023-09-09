package unsw.dungeon;

public class BoulderBlockBehavior implements BlockBehavior{

    private Dungeon dungeon;
    private boolean CanbeMovedOn;

    public BoulderBlockBehavior(Dungeon dungeon){
        this.dungeon = dungeon;
        this.CanbeMovedOn = false;
    }
    @Override
    public boolean ifBlockPlayer(int x, int y, int player_x, int player_y, String direction) {
        int new_x = x;
        int new_y = y;

        switch (direction) {
            case "left":
                new_x = x - 1;
                break;
            case "right":
                new_x = x + 1;
                break;
            case "up":
                new_y = y - 1;
                break;
            case "down":
                new_y = y + 1;
                break;
        }
        boolean ifblock = false;
        for(Entity entity: dungeon.getAllEntities()){
            if (entity.getX() == new_x && entity.getY() == new_y){
                if (!(entity instanceof SwitchFloor)){
                    ifblock = true;
                }
            }
            
        }
        return ifblock;
    }
}