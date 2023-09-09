package unsw.dungeon;

public interface DoorState {
    public boolean open(Key key);
    public boolean open();
    public boolean close();
    
}