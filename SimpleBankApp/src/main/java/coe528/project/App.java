package coe528.project;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    
    private Scene loginScene;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bank Account Login");
        
        // Create the login scene
        LoginScreen login = new LoginScreen(primaryStage);
        loginScene = login.getScene();
        
        // Set the login screen as the initial scene
        primaryStage.setScene(loginScene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("The Application has now been closed");
        });
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}


