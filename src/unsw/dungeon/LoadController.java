package unsw.dungeon;

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
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class LoadController {

    private Stage stage;
    @FXML
    private GridPane ViewList;

    @FXML
    private Button BtA;

    @FXML
    private Button BtB;

    @FXML
    private Button BtC;

    private Image BackImage;

    public LoadController(Stage stage) {
        this.stage = stage;
        //this.BackImage = new Image((new File("images/background.png")).toURI().toString());
    }

    @FXML
    public void initialize() {
        //BackImage = new Image((new File("images/key.png")).toURI().toString());
        BackImage = new Image((new File("images/new.png")).toURI().toString());
        ViewList.add(new ImageView(BackImage),0,0);


        BtA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DungeonControllerLoader dungeonLoader;
                try {
                    dungeonLoader = new DungeonControllerLoader("maze.json");
                    DungeonController controller = dungeonLoader.loadController();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
                    loader.setController(controller);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);

                    root.requestFocus();
                    stage.setScene(scene);
                    stage.show();

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });   
    
        BtB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DungeonControllerLoader dungeonLoader;
                try {
                    dungeonLoader = new DungeonControllerLoader("boulders.json");
                    DungeonController controller = dungeonLoader.loadController();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
                    loader.setController(controller);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);

                    root.requestFocus();
                    stage.setScene(scene);
                    stage.show();

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        
        BtC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DungeonControllerLoader dungeonLoader;
                try {
                    dungeonLoader = new DungeonControllerLoader("advanced.json");
                    DungeonController controller = dungeonLoader.loadController();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
                    loader.setController(controller);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);

                    root.requestFocus();
                    stage.setScene(scene);
                    stage.show();

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    
    
    
    }

    
}