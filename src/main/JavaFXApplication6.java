package main;

import controller.FXMLDocumentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.dto.UsuarioDTO;
import validator.IUsuarioValidador;
import validator.UsuarioValidador;

public class JavaFXApplication6 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FXMLDocument.fxml"));

        // Usar o setControllerFactory para passar o controlador com o parâmetro
        loader.setControllerFactory(c -> {
            // Crie o seu validador aqui
            IUsuarioValidador validador = new UsuarioValidador();

            // Retorne o controlador passando o validador
            return new FXMLDocumentController(validador);
        });

        // Carregar a interface
        Parent root = loader.load();

        // Configura a cena e exibe a interface
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    
    public static void main(String[] args) {  
        launch(args); 
    }
    
}
