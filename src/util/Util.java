package util;

import javafx.scene.control.Alert;

public class Util {
    public void alertAddError(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Adicionar Error Gmail");
        alert.setContentText("Coloque um Gmail Valido");
        alert.show();
    }
    //alerta de deletar usuario
    public void alertDelete(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletar");
        alert.setContentText("Usuario deletado co sucesso!");
        alert.show();
    }
    //alerta de erro ao deletar
    public void alertDeleteError(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletar");
        alert.setContentText("Usuario nao encontrado!");
        alert.show();
    }
    //alerta de confirmacao de adicionar usuario
    public void alertAdd(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Adicionar");
        alert.setContentText("Usuario adicionado com sucesso!!");
        alert.show();
    }
}
