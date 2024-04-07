/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package coe528.project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author ashsu
 */
public class ManagerScreen{
    
    private Scene scene;
    private AccountFile accountFile;
    private String enteredUsername;
            
    public ManagerScreen(Stage primaryStage, AccountFile accountFile){
        
        Manager m = new Manager("admin", "admin", "manager");
        
        this.accountFile = accountFile;
        
        primaryStage.setTitle("Manager Control Panel");
        
        GridPane managerLayout = new GridPane();
        managerLayout.setAlignment(Pos.CENTER);
        managerLayout.setHgap(10);
        managerLayout.setVgap(5);
        managerLayout.setPadding(new Insets(10));
        
        // Create manager landing page
        VBox logout = new VBox(10);
        logout.setAlignment(Pos.CENTER);
        logout.setPadding(new Insets(20));

        Button addCustomerButton = new Button("Add Customer");
        Button delCustomerButton = new Button("Delete Customer");
        Button logoutButton = new Button("Logout");
        
        addCustomerButton.setMinWidth(100);
        delCustomerButton.setMinWidth(100);

        logoutButton.setOnAction(e -> {
            primaryStage.setScene(new LoginScreen(primaryStage).getScene());
            primaryStage.setTitle("Bank Application");
        });
        
        addCustomerButton.setOnAction(e -> {
            Stage addStage = new Stage();
            addStage.setTitle("Add Customer");
            
            GridPane addScreen = new GridPane();
            addScreen.setAlignment(Pos.CENTER);
            addScreen.setHgap(10);
            addScreen.setVgap(5);
            addScreen.setPadding(new Insets(10));
            
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter Customer Username");

            TextField passwordField = new TextField();
            passwordField.setPromptText("Enter Customer Password");

            TextField balanceField = new TextField();
            balanceField.setPromptText("Enter Customer Balance");

            enteredUsername = usernameField.getText();
            
            Label usernameLabel = new Label("Username:");
            Label passwordLabel = new Label("Password:");
            Label balanceLabel = new Label("Balance:");
            
            Label result = new Label();

            Button addButton = new Button("Confirm");
            
            addButton.setOnAction(event -> {
                
                if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || balanceField.getText().isEmpty()){
                    result.setText("Necessary information is missing.\nPlease try again.");
                }
                
                else if(usernameField.getText().equals("admin")){
                    result.setText("Customer username cannot be admin.\nPlease try again.");
                }
                
                else if(Double.parseDouble(balanceField.getText()) < 100){
                    result.setText("Bank account balance must be a minimum of $100.");
                }
                else{
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    double balance = Double.parseDouble(balanceField.getText());

                    // Call method from AccountFile to add customer file
                    String message = m.add(username, password, balance);
                    result.setText(message);
                    
                    usernameField.clear();
                    passwordField.clear();
                    balanceField.clear();
                }
            });
            
            addScreen.add(usernameLabel, 0, 0);
            addScreen.add(usernameField, 1, 0);
            addScreen.add(passwordLabel, 0, 1);
            addScreen.add(passwordField, 1, 1);
            addScreen.add(balanceLabel, 0, 2);
            addScreen.add(balanceField, 1, 2);
            addScreen.add(addButton, 1, 4);
            addScreen.add(result, 1, 5);

            Scene addCustomerScene = new Scene(addScreen, 400, 300);
            addStage.setScene(addCustomerScene);
            addStage.show();
        });
        
        delCustomerButton.setOnAction(e -> {
            Stage addStage = new Stage();
            addStage.setTitle("Delete Customer");
            
            GridPane delScreen = new GridPane();
            delScreen.setAlignment(Pos.CENTER);
            delScreen.setHgap(10);
            delScreen.setVgap(5);
            delScreen.setPadding(new Insets(10));
            
            TextField usernameField = new TextField();
            usernameField.setPromptText("Enter Customer Username");
            
            enteredUsername = usernameField.getText();
            
            Label usernameLabel = new Label("Username:");
            
            Label result = new Label();

            Button delButton = new Button("Confirm");
            
            delButton.setOnAction(event -> {
                String username = usernameField.getText();

                // Call method from AccountFile to remove customer file
                String message = m.remove(username);
                result.setText(message);
                
                usernameField.clear();
            });
            
            delScreen.add(usernameLabel, 0, 0);
            delScreen.add(usernameField, 1, 0);
            delScreen.add(delButton, 1, 4);
            delScreen.add(result, 1, 6);

            Scene addCustomerScene = new Scene(delScreen, 400, 300);
            addStage.setScene(addCustomerScene);
            addStage.show();
        });
        
        managerLayout.add(addCustomerButton, 0, 2);
        managerLayout.add(delCustomerButton, 1, 2);
        
        logout.getChildren().addAll(logoutButton);
        
        managerLayout.add(logout, 0, 3, 2, 1);

        this.scene = new Scene(managerLayout, 500, 200);
    }
    
    
    public Scene getScene(){
        return scene;
    }
    
    public String getEnteredUsername() {
        return enteredUsername;
    }
    
}
