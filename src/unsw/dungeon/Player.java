package unsw.dungeon;
import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;

    private Sword sword;
    private int treasure;
    private int killedDragon;
    private LocalTime potion;
    private Key key;
    private String Text;
    private StringProperty TreasuerProp;
    private StringProperty SwordProp;
    private StringProperty PotionProp;
    private StringProperty TextProp;
    private StringProperty KeyProp;

    private int HaveKilled;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.treasure = 0 ;
        this.key = null;
        this.potion = null ;
        this.TreasuerProp = TreasureP();
        this.PotionProp = PotionP();
        this.SwordProp = SwordP();
        this.TextProp = TextP("");
        this.KeyProp = KeyP();
        this.HaveKilled = 0;
        this.Text = "";
        this.killedDragon = 0;
    }
    
   
    //Potion Property Bind

    public StringProperty PotionP(){
        StringProperty p = new SimpleStringProperty();
        String res =  "0";
        if(this.potion != null){           
            if(this.potion.isAfter(LocalTime.now())){  // calculate all left time and translate to String
                Duration d =  java.time.Duration.between(this.potion,LocalTime.now() );
                res = String.valueOf(d.toSeconds());
            }
        }
        res = res + " s" ;
        p.set(res);
        return p; 
    }
    public StringProperty getPotionP(){
        return this.PotionProp;
    }  
    public void setPotionProp() {
        String res =  "0";
        if(this.potion != null){           
            if(this.potion.isAfter(LocalTime.now())){  // calculate all left time and translate to String
                Duration d =  java.time.Duration.between(this.potion,LocalTime.now() );
                res = String.valueOf(d.toSeconds());
            }
        }
        res = res + " s left";
        this.PotionProp.set(res);
    } 
///////////////////////////////
// Sword Property bind

    public  StringProperty SwordP(){
        StringProperty p = new SimpleStringProperty();
        String res =  "0";
        if(sword != null){
            res = String.valueOf(sword.available());
        }
        p.set(res);
        return p;
    }
    public StringProperty getSwordP(){
        return this.SwordProp;
    }
    public void setSwordProp(int sword) {
        this.SwordProp.set(String.valueOf(sword));
    }
//////////////////////////////////
// Treasure Property bind

    public  StringProperty TreasureP(){
        StringProperty p = new SimpleStringProperty();
        String res =  "0";
        if(this.treasure != 0){
            res = String.valueOf(treasure);
        }
        p.set(res);
        return p;
    }
    public StringProperty getTreasureP(){
        return this.TreasuerProp;
    }
    public void setTreasuerProp(int treasuer) {
        this.TreasuerProp.set(String.valueOf(treasure));
    }

//////////////////////////////////
// Text Property bind
    public StringProperty TextP(String text){
        StringProperty p = new SimpleStringProperty();
        String res = "Enjoy new Game!";
        /*
        if(this.Text.equals("") == false){
            res = text; 
        }*/ 
        p.set(res);
        return p;
    }

    public StringProperty getTextP(){
        return this.TextProp; 
    }

    
    public void setTextP(String text){
        this.Text = text;
        this.TextProp.set(text);
    }

//////////////////////////////////
// Key Property bind

    public StringProperty KeyP(){
        StringProperty p = new SimpleStringProperty();
        String res = "None";       
        if(this.key != null){
            res = "id: ";
            res = res + this.key.getDoor(); 
        }
        p.set(res);
        return p;
    }
    
    public StringProperty getKeyP(){
        return this.KeyProp;   
    }

    public void setKeyProp(Key key) {
        String res;
        if (key != null){
            res = "id: ";
            res = res + key.getDoor();
            this.KeyProp.set(res);

        } else{
            res = "None";
        }
       
        this.KeyProp.set(res);
    }

//////////////////////////////////
// End game Property(ifover) bind
    public BooleanProperty isIfover() {
        return dungeon.isIfover();
    }

    public void setIfover(boolean ifover) {
        dungeon.setIfover(ifover);
    }


 
    public void Complete(String name){
        dungeon.Complete(name);
    }


//////////////
// check if player has sword
    public void swordCheck(){
        if(this.sword == null){
            System.out.println("don't have sword");
        }else{
            System.out.println("have sword");
            
        }
    }

/// basic movement
    public void moveUp() {
        setPotionProp();

        if ((getY() > 0) && !dungeon.ifBlockPlayer(getX(), getY()-1, "up")){
            y().set(getY() - 1);
            dungeon.updateAll();
        }
        dungeon.updateAll();
    }

    public void moveDown() {
        setPotionProp();
        if ((getY() < dungeon.getHeight() - 1) && !dungeon.ifBlockPlayer(getX(), getY() + 1, "down")){
            y().set(getY() + 1);
            dungeon.updateAll();
        }
        dungeon.updateAll();

    }

    public void moveLeft() {
        setPotionProp();
        if ((getX() > 0) && !dungeon.ifBlockPlayer(getX() - 1 , getY(), "left")){
            x().set(getX() - 1);
            dungeon.updateAll();
        }
        dungeon.updateAll();

    }

    public void moveRight() {
        setPotionProp();
        if ((getX() < dungeon.getWidth() - 1) && !dungeon.ifBlockPlayer(getX() + 1, getY(), "right")){
            x().set(getX() + 1);
            dungeon.updateAll();
        }
        dungeon.updateAll();
    }

///treasure function 
    public void addTreasure(int x){
        this.treasure += x ;
        setTreasuerProp(this.treasure);
    }
    

    public int getTreasure(){
        return this.treasure;
    }

///potion function 
    public void startPotion(){
        LocalTime t = LocalTime.now().plusMinutes(1); // invisible time 1 min
        this.potion = t;        
        dungeon.stopEnemy(t,getX(),getY());     
    }

    public LocalTime getPotion(){
        return this.potion;
    }

/// key function
    public void setKey(Key key){
        this.key = key;  
        setKeyProp(key);
    }

    public Key getKey() {
        return this.key;
    } 

// collect function (different from use sword)
    public boolean PickUpSword(Sword sword){    
        
        if(this.sword == null){
            this.sword = sword;
            setSwordProp(this.sword.available());
            setTextP("Press 'F' to wield Sword  \nEnemy will be killed if they are 1 unit away from you");
            return true;
        } else{
            if (this.sword.equals(sword)){
                return true;
            } else{
                setTextP("You already have a sword available!");
                return false;
            }
        }
    }

    public Sword getSword(){
        return this.sword;
    }

    //return the number of enemies that are killed
    public int wield(){
        
        if (this.sword != null){
            if(this.sword.available() > 0){
                int killed = dungeon.killEnemy(getX(),getY());
                this.sword.wieldOnce();
                setTextP("You wield once.");
                setSwordProp(this.sword.available());
                if (this.sword.available() == 0){
                    setSwordProp(this.sword.available());
                    dungeon.getAllEntities().remove(this.sword);
                    this.sword = null;
                    setTextP("Now you can pick up another sword");
                }
                this.HaveKilled += killed;
                if (HaveKilled == dungeon.Num_enemies){
                    Complete("enemies");
                }
                
                if(killDragon(0) != 0){
                    System.out.println("killed: "+HaveKilled + "goal：" + dungeon.Num_enemies);
                    setTextP("You killed a dragon! Check if you get bonus tresure" + "\n Awesome!! You killed "+ killed + " enemies!!!");
                }else {
                    if (killed != 0){
                        setTextP("Awesome!! You killed "+ killed + " enemies!!!");

                    }
                }
                return killed;
            }
            return 0;
        } else{
            return 0;
        }
    }

    public int killDragon(int i){
        this.killedDragon += i;
        return this.killedDragon;
    }

    public boolean openDoor(){
        return dungeon.openDoor();
    }

    //portal 
    public void transport(int px, int py){
        x().set(px);
        y().set(py);
    }

    
//other helper function

    public int getHaveKilled() {
        return HaveKilled;
    }
    
    public Dungeon getDungeon() {
        return dungeon;
    }

    public void addHaveKilled(int killed) {
        this.HaveKilled += killed;
            if (getHaveKilled() == dungeon.Num_enemies){
                Complete("enemies");
            }
    }

}
