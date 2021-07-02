package org.example.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.App;
import org.example.animations.Shaker;
import org.example.database.DatabaseHandler;
import org.example.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {
    private int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton loginSignupButton;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        databaseHandler = DatabaseHandler.getInstance();
        loginButton.setOnAction(event -> {
            Shaker userNameShaker = new Shaker(loginUsername);
            Shaker passwordShaker = new Shaker(loginPassword);
            String loginText = loginUsername.getText().trim();
            String loginPwd = loginPassword.getText().trim();
            if (loginText.isEmpty() || loginPwd.isEmpty()) {
                passwordShaker.shake();
                userNameShaker.shake();
                return;
            }
            User user = new User();
            user.setUserName(loginText);
            user.setPassword(loginPwd);
            ResultSet userRow = databaseHandler.getUser(user);
            int counter = 0;
            try {
                while (userRow.next()) {
                    counter++;
                    String name = userRow.getString("FirstName");
                    userId = userRow.getInt("UserId");
                    System.out.println("Welcome! " + name);
                }
                if (counter == 1) {
                    showAddItemScreen();
                } else {
                    userNameShaker = new Shaker(loginUsername);
                    passwordShaker = new Shaker(loginPassword);
                    passwordShaker.shake();
                    userNameShaker.shake();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        loginSignupButton.setOnAction(event -> {

            loginSignupButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(App.class.getResource("view/signup.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void showAddItemScreen() {
        //Take users to AddItem screen
        loginSignupButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(App.class.getResource("view/addItem.fxml"));
        try {
            loader.setRoot(loader.getRoot());
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        AddItemController addItemController = loader.getController();
        addItemController.setUserId(userId);
        stage.showAndWait();
    }

}
