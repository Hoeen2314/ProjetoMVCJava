package main;

import controller.FXMLDocumentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import validator.IUsuarioValidador;
import validator.UsuarioValidador;

public class JavaFXApplication6 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));

        loader.setControllerFactory(c -> {
            IUsuarioValidador validador = new UsuarioValidador();

            return new FXMLDocumentController(validador);
        });

        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    public static void main(String[] args) {  
        launch(args); 
    }
}
