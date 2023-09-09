package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
     
        primaryStage.setTitle("Dungeon");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Loading.fxml"));
        LoadController loadC = new LoadController(primaryStage);
        loader.setController(loadC);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();

/*
      // Debug using 
        //DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("boulders.json");
        //DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");
       
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");
        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show(); 
    
*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
