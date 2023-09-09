package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    protected BlockBehavior blockBehavior;
    protected String type;
    protected boolean toremove;
    protected BooleanProperty remove;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.blockBehavior = new NotBlockBehavior();
        this.toremove = true;
        this.remove = new SimpleBooleanProperty();
        this.remove.set(false);
    }

    public Entity(int x, int y, BlockBehavior blockBehavior){
        this(x,y);
        this.blockBehavior = blockBehavior;
        this.toremove = false;
        this.remove = new SimpleBooleanProperty();
        this.remove.set(false);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public void remove(){
        this.remove.set(true);
    }

    public BooleanProperty RemoveFromView(){
        return this.remove;
    }


    public boolean ifBlockPlayer(int player_x, int player_y, String direction){
        return this.blockBehavior.ifBlockPlayer(getX(), getY(), player_x, player_y, direction);
    }

    public void setType(String s){
        this.type = s;
    }

    public String getType(){
        if (this.type == null){
            return null;
        }else{
            return this.type;
        }
    }

    public boolean getToremove() {
        return this.toremove;
    }

    public void setToremove(boolean toremove) {
        this.toremove = toremove;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null){return false;}
        if (this.getClass() != obj.getClass()){return false;}
        Entity e = (Entity) obj;

        return e.getX() == this.getX() && e.getY() == this.getY();
    }
}
