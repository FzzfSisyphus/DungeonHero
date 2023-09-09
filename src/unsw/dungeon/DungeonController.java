package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.HPos;






import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML 
    private GridPane StructG;
    
    @FXML
    private GridPane textG;
   
    @FXML
    private GridPane squares;

    @FXML
    private HBox MessageBox;
    
    @FXML
    private VBox vbox;
    
    @FXML
    private Text NewUP;

    @FXML
    private Text GOAL;


    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private Alert a;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        player.isIfover().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                GameOver();
            }
        });
        this.initialEntities = new ArrayList<>(initialEntities);

        // create a alert
        a = new Alert(AlertType.NONE);
    }

    @FXML
    public void initialize() {
        vbox.setSpacing(5);
        vbox.setFillWidth(false);
        
        StructG.setHalignment(MessageBox, HPos.CENTER);
        StructG.setHgap(10);
        MessageBox.setSpacing(10);         


        Image MessageTreasure = new Image((new File("images/gold_pile.png")).toURI().toString());
        Text TreasureNumber = new Text();
        TreasureNumber.setText("money");
        MessageBox.getChildren().add(0,new ImageView(MessageTreasure));
        MessageBox.getChildren().add(1,TreasureNumber);
        TreasureNumber.textProperty().bindBidirectional( player.getTreasureP());
        TreasureNumber.setFont(Font.font(null, FontWeight.BOLD, 15));

        Image MessageSword = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        Text SwordTime = new Text();
        SwordTime.setText("sword");
        MessageBox.getChildren().add (2,new ImageView(MessageSword));
        MessageBox.getChildren().add(3,SwordTime);
        SwordTime.textProperty().bindBidirectional( player.getSwordP());
        SwordTime.setFont(Font.font(null, FontWeight.BOLD, 15));

        Image MessagePotion = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
        Text PotionTime = new Text();
        PotionTime.setText("potion");
        MessageBox.getChildren().add (4,new ImageView(MessagePotion));
        MessageBox.getChildren().add(5,PotionTime);
        PotionTime.textProperty().bindBidirectional( player.getPotionP());
        PotionTime.setFont(Font.font(null, FontWeight.BOLD, 15));
        
        Image MessageKey = new Image((new File("images/key.png")).toURI().toString());
        Text KeyID = new Text();
        KeyID.setText("None");
        MessageBox.getChildren().add (6,new ImageView(MessageKey));
        MessageBox.getChildren().add(7,KeyID);
        KeyID.textProperty().bindBidirectional( player.getKeyP());
        KeyID.setFont(Font.font(null, FontWeight.BOLD, 15));



        textG.setHalignment(NewUP, HPos.LEFT);
        NewUP.setWrappingWidth(200);
        NewUP.setText("game start");

        NewUP.setCache(true);
        NewUP.setFill(Color.rgb(51, 153, 255));
        NewUP.setFont(Font.font(null, FontWeight.BOLD, 18));

        textG.setHalignment(GOAL, HPos.RIGHT);
        GOAL.setText(dungeon.GoalDetail());
        GOAL.setCache(true);
        GOAL.setFill(Color.ORANGERED);
        GOAL.setFont(Font.font(null, FontWeight.BOLD, 20));

        GOAL.textProperty().bindBidirectional(dungeon.getGoals());
    
        NewUP.textProperty().bindBidirectional( player.getTextP());


        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                player.moveUp();
                break;
            case DOWN:
                player.moveDown();
                break;
            case LEFT:
                player.moveLeft();
                break;
            case RIGHT:
                player.moveRight();
                break;
            case F :   // F  player wield 
                player.wield();
                break;
            case Q:
                player.setKey(null);
                player.setTextP("Key discarded! You can pick up another key.");
                break;
            default:
                break;
        }
    }

    private void GameOver() {
        a.setAlertType(AlertType.CONFIRMATION);

        // set content text
        a.setContentText("Game is Over");
            
        Stage stage = (Stage) squares.getScene().getWindow();
        Optional<ButtonType> result = a.showAndWait();
        if(!result.isPresent()){
            stage.close();
        }else if(result.get() == ButtonType.OK){
            stage.close();
        }else if(result.get() == ButtonType.CANCEL){
                
        }
                
    }

}

