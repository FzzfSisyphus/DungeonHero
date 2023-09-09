package unsw.dungeon;

public class DoorOpenState implements DoorState {

    Door door;

    public DoorOpenState(Door door) {
        this.door = door;
    }

    @Override
    public boolean open(Key key) {
        this.door.setState(this.door.getOpenstate());
        this.door.UnBlock();
        return true;
    }

    @Override
    public boolean open() {
        this.door.setState(this.door.getOpenstate());
        this.door.UnBlock();
        return true;

    }

    @Override
    public boolean close() {
        this.door.setState(this.door.getClosestate());
        this.door.Block();
        return true;
    }

    
}