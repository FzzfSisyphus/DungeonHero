package unsw.dungeon;

public class Boulder extends Entity implements Item {

    private int Ready_to_x = this.getX();
    private int Ready_to_y = this.getY();

    public Boulder(int x, int y, Dungeon dungeon) {
        super(x, y, new BoulderBlockBehavior(dungeon));
        super.setType("boulder");
        super.setToremove(false);
    }

    @Override
    public boolean ifBlockPlayer(int player_x, int player_y, String direction) {
        int new_x = this.getX();
        int new_y = this.getY();
        int old_player_x = player_x;
        int old_player_y = player_y;
        switch (direction) {
            case "left":
                new_x = new_x - 1;
                old_player_x = player_x+1;
                break;
            case "right":
                new_x = new_x + 1;
                old_player_x = player_x - 1;
                break;
            case "up":
                new_y = new_y - 1;
                old_player_y = player_y + 1;

                break;
            case "down":
                new_y = new_y + 1;
                old_player_y = player_y - 1;
                break;
        }
        if ((player_x != this.getX() || player_y!= this.getY()) || (old_player_x !=  this.getX() && old_player_y != this.getY())){
            return false;
        }else if (player_x - this.getX() >=2 || this.getX()-player_x >=2 || player_y - this.getY() >=2 || this.getY()-player_y >=2){
            return false;

        }else{
            if (!super.ifBlockPlayer(player_x, player_y, direction)) {
                this.setReady_to_x(new_x);
                this.setReady_to_y(new_y);
                return false;
            }
            return true;

        }
    }

    private int getReady_to_x() {
        return Ready_to_x;
    }

    private void setReady_to_x(int new_x){
        this.Ready_to_x = new_x;
    }

    private int getReady_to_y() {
        return Ready_to_y;
    }

    private void setReady_to_y(int new_y){
        this.Ready_to_y = new_y;
    }

    private boolean Tomove() {
        return this.getX() != this.getReady_to_x()  || this.getY() != this.Ready_to_y;
    }

    @Override
    public void function(Player p) {
        this.x().set(this.getReady_to_x());
        this.y().set(this.getReady_to_y());
        if (p.getDungeon().getEntity(x().get(), y().get()).get(0) instanceof SwitchFloor){
            p.setTextP("You just push a boulder to a switchfloor!!!");
        }else{
            if (p.getTextP().get().equals("You just push a boulder to a switchfloor!!!")){
                p.setTextP("");
            }
        }
    }

    @Override
    public boolean ifmeet(int player_x, int player_y) {
        return Tomove();
    }


    
    
    


    
}