package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
      Parent root = FXMLLoader.load(App.class.getResource("view/login.fxml"));
      primaryStage.setTitle("To DO App");
      primaryStage.setScene(new Scene(root,700,400));
      primaryStage.show();
    }



    public static void main(String[] args) {
        launch();
    }

}