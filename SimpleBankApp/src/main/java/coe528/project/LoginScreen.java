
package coe528.project;

import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author ashsu
 */
public class LoginScreen{
    
    // Creates a scene that is never modified
    private final Scene scene;
    
    // Emthod for the login screen
    public LoginScreen(Stage primaryStage) {
        
        primaryStage.setTitle("Bank Application");
        
        GridPane loginLayout = new GridPane();
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setHgap(10);
        loginLayout.setVgap(10);
        loginLayout.setPadding(new Insets(20));
        
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        
        Label usernameLbl = new Label("Username:");
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        
        Label passwordLbl = new Label("Password:");
        
        Button loginButton = new Button("Login");
        
        Label result = new Label();
        
        // When login button is pressed follow this
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            Manager m = new Manager(username, password, "manager");
            Customer c = new Customer(username, password, "customer");
                        
            if(username.isEmpty() || password.isEmpty()){
                result.setText("Invalid Credentials. Try Again.");
            }
            else{
                if (m.authenticate() == true) {
                    primaryStage.setScene(new ManagerScreen(primaryStage, new AccountFile()).getScene());
                }
                else if(c.authenticate() == true){
                    primaryStage.setScene(new CustomerScreen(primaryStage, new AccountFile(), c.getUsername()).getScene());
                }
                else{
                    result.setText("Invalid Credentials. Try Again.");
                }
            }
        });
        
        loginLayout.add(usernameLbl, 0, 0);
        loginLayout.add(usernameField, 1, 0);
        
        loginLayout.add(passwordLbl, 0, 1);
        loginLayout.add(passwordField, 1, 1);
        
        loginLayout.add(loginButton, 1, 3);
        
        loginLayout.add(result, 1, 4);
        
        this.scene = new Scene(loginLayout, 500, 200);
        
    }
    
    // Method to get the current scene
    public Scene getScene() {
        return scene;
    }
    
}
