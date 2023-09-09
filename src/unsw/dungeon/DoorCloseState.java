package unsw.dungeon;

public class DoorCloseState implements DoorState {
    Door door;

    public DoorCloseState(Door door) {
        this.door = door;
    }

    @Override
    public boolean open(Key key) {
        if (key == null) {
            return false;

        } else if (key.getDoor() == this.door.getId()) {
            this.door.setState(this.door.getOpenstate());
            this.door.UnBlock();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean open() {
        this.door.setState(this.door.getOpenstate());
        this.door.UnBlock();
        return true;

    }

    @Override
    public boolean close() {
        return true;
    }

    
    
}