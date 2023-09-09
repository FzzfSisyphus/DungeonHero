package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image treasureImage;
    private Image potionImage;
    private Image keyImage;
    private Image boulderImage;
    private Image switchImage;
    private Image exitImage;
    private Image dooropenImage;
    private Image doorclosedImage;
    private Image portalImage;
    private Image enemyImage;
    private Image swordImage;
    private Image dragonImage;
    private Image attackImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        potionImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        switchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString());
        doorclosedImage = new Image((new File("images/closed_door.png")).toURI().toString());
        dooropenImage = new Image((new File("images/open_door.png")).toURI().toString());
        portalImage = new Image((new File("images/portal.png")).toURI().toString());
        enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
        swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        dragonImage = new Image((new File("images/dragon.gif")).toURI().toString());   
        attackImage = new Image((new File("images/act.gif")).toURI().toString());

    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
//// add new onLoad 
    @Override
    public void onLoad(Treasure treasure){
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    @Override
    public void onLoad(Potion potion){
        ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
    }
    
    @Override
    public void onLoad(Key key){
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }

    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }

    @Override
    public void onLoad(SwitchFloor switchFloor) {
        ImageView view = new ImageView(switchImage);
        addEntity(switchFloor, view);
    }

    @Override
    public void onLoad(Exit Exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(Exit, view);
    }

    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(doorclosedImage);
        addEntity(door, view);
    }

    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }

    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }

    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }

    @Override
    public void onLoad(Dragon dragon) {
        ImageView view = new ImageView(dragonImage);
        addEntity(dragon, view);
    }


    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        entity.RemoveFromView().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                node.setVisible(false);
            }
        });

        if(entity instanceof Door){
            Door d = (Door) entity;
            d.getIsopen().addListener((observable, oldValue, newValue) -> {
                if (newValue){
                    ImageView view = (ImageView) node;
                    view.setImage(new Image((new File("images/open_door.png")).toURI().toString()));
                }
            });
        }
        
        if(entity instanceof Player){
            Player p = (Player) entity;
            p.getSwordP().addListener((observable, oldValue, newValue) -> {
                if (!newValue.equals("5")){
                    ImageView view = (ImageView) node;
                    
                    Timeline timeline = new Timeline();     
                    timeline.setCycleCount(2);      
                    
                     EventHandler<ActionEvent> ev = new EventHandler<ActionEvent>() {            
                        @Override
                        public void handle(ActionEvent t) {
                            view.setImage(attackImage);
                        }
                    }; 
                                

                    KeyFrame k = new KeyFrame(Duration.millis(500),ev);
                    timeline.getKeyFrames().add(k);
                    timeline.play();
                    timeline.setOnFinished(event -> view.setImage(playerImage));
                }
            });
        }
        
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
