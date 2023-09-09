/**
 *
 */
package unsw.dungeon;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.swing.event.SwingPropertyChangeSupport;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private LocalTime enemyTime;
    private BooleanProperty Ifover;
    private Goal goal;
    private StringProperty Goals;
    public int Num_enemies;

    

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.Ifover = new SimpleBooleanProperty();
        this.Ifover.set(false);
        this.Goals = new SimpleStringProperty();
        this.Goals.set("");
        
    }

    public Dungeon(int width, int height, Goal goal) {
        this(width, height);
        setGoal(goal);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

///enemy realated function
    public void stopEnemy(LocalTime t,int x, int y){
        enemyTime = t;
        for (Entity entity: this.entities){
            if(entity != null && entity instanceof Enemy ){
                Enemy enemy = (Enemy)entity;
                enemy.stop(t,x,y);
            }
        }
    }
    
    public LocalTime getEnemyTime(){
        return this.enemyTime;
    }
    
    public int killEnemy(int x, int y){
        List<Entity> killed = new ArrayList<Entity>();
        for (Entity entity: this.entities){
            if(entity != null && entity instanceof Dragon){
                Dragon d = (Dragon) entity;
                if(d.swordCheck(x,y)) {
                    System.out.println("dragon is killed");
                    d.die();
                    d.remove();
                    player.killDragon(1);
                    killed.add(d);
                }
            }else if(entity != null && entity instanceof Enemy){
                Enemy e = (Enemy) entity;
                if(e.swordCheck(x,y)) {
                    System.out.println("enemy is killed");
                    e.remove();
                    killed.add(e);
                }
            } 
        }
        this.entities.removeAll(killed);
        return killed.size();
    }
//////////////////

    public void setPlayer(Player player) {
        this.player = player;

    }   

    public void setSwitchtoDoor(Door door){
        for (Entity e: this.entities){
            if (e instanceof SwitchFloor){
                SwitchFloor switchfloor = (SwitchFloor) e;
                switchfloor.addDoor(door);
            }

        }

    }

    public boolean openDoor(){
        Key tmp_key;
        for (Entity entity: this.entities){
            if(entity != null && entity instanceof Door ){
                Door door = (Door)entity;
                if (door.getX() == player.getX() && (door.getY() == (player.getY() + 1) || door.getY() == (player.getY()-1))) {

                    if (door.open(player.getKey())){
                        tmp_key = player.getKey();
                        player.setKey(null);
                        return true;
                    }
                }else{
                    if (door.getY() == player.getY() && (door.getX() == (player.getX() + 1) || door.getX() == (player.getX()-1))){
                        if (door.open(player.getKey())){
                            tmp_key = player.getKey();
                            player.setKey(null);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Door){
            Door door = (Door) entity;
            setSwitchtoDoor(door);
        }

        if (entity instanceof Enemy || entity instanceof Dragon){
            this.Num_enemies++;
        }
    }

    public List<Entity> getEntity(int x, int y){
        List<Entity> found = new ArrayList<Entity>();
        for (Entity e: this.entities){
            if (e.getX() == x && e.getY() == y){
                found.add(e);
            }
        }
        return found;
    }

    public boolean ifBlockPlayer(int player_x, int player_y, String direction){
        for (Entity entity : this.entities){
            if (entity != null){
                if (entity.ifBlockPlayer(player_x, player_y, direction)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public List<Entity> getAllEntities(){
        return this.entities;
    }
    
    public void updateAll(){
        List<Entity> EntitiesToRemove = new ArrayList<Entity>();
        for (Entity entity : this.entities){
            if (entity != null && entity instanceof Item){              
                Item item = (Item) entity;
                    if (item.ifmeet(player.getX(), player.getY())){
                        item.function(player);
                        if (entity.getToremove()){                           
                            EntitiesToRemove.add(entity);
                            entity.remove();
                        }
                    }
                

            }
        }
        this.entities.removeAll(EntitiesToRemove);
    }


    public BooleanProperty isIfover() {
        return this.Ifover;
    }

    public void setIfover(boolean ifover) {
        this.Ifover.set(ifover);
    }


// goal realted function 
    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
        this.Goals.set(GoalDetail());
    }

    public void Complete(String name){
        this.goal.Complete(name);
        setIfover(this.goal.ifTrue());
        this.Goals.set(GoalDetail());
    }

    public String GoalDetail(){
        return this.goal.nameString();
    }

    public StringProperty getGoals() {
        return Goals;
    }
    
}
