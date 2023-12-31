package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");
        JSONObject Goals = json.getJSONObject("goal-condition");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }

    
        dungeon.setGoal(createGoal(Goals));
        return dungeon;
    }
    
    private Goal createGoal(JSONObject Goals){
        Goal goal;
        if (!Goals.has("subgoals")) {
            goal = new SingleGoal(Goals.getString("goal"));

        } else{
            
            if (Goals.getString("goal").equals("AND")){
                goal = new AndGoal();
            } else{
                goal = new OrGoal();
                

            }
            JSONArray subgoals = Goals.getJSONArray("subgoals");
                for (int i = 0; i < subgoals.length(); i++){
                    goal.addGoal(createGoal(subgoals.getJSONObject(i)));
                }
            
        }
        return goal;

    }
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        // TODO Handle other possible entities
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
            break;
        case "key":
            int kid = json.getInt("id");
            Key key = new Key(x, y,kid);
            onLoad(key);
            entity = key;
            break;      
        case "potion":
            Potion potion = new Potion(x, y);
            onLoad(potion);
            entity = potion;
            break;
        case "switch":
            SwitchFloor switchFloor = new SwitchFloor(x, y,dungeon);
            onLoad(switchFloor);
            entity = switchFloor;
            break;  
        case "boulder":
            Boulder boulder = new Boulder(x, y, dungeon);
            onLoad(boulder);
            entity = boulder;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "door":
            int did = json.getInt("id");
            Door door = new Door(x, y,did);
            onLoad(door);
            entity = door;
            break;
        case "portal":
            int portalID = json.getInt("id");
            int no = json.getInt("no");
            Portal p = new Portal(x, y,portalID,no);
            p.setDungeon(dungeon);
            onLoad(p);
            entity = p;
            break;
        case "enemy":
            Enemy enemy = new Enemy(x, y, dungeon);
            onLoad(enemy);
            entity = enemy;
            break;
        case "sword":
            Sword sword = new Sword(x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "dragon":
            Dragon dragon = new Dragon(x, y,dungeon);
            onLoad(dragon);
            entity = dragon;
            break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    // TODO Create additional abstract methods for the other entities
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Potion potion);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(SwitchFloor switchFloor);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Portal portal);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(Dragon dragon);


}
