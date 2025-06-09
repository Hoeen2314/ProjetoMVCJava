package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.UsuarioDAO;
import model.dto.UsuarioDTO;
import util.DialogUtil;
import validator.IUsuarioValidador;

public class FXMLDocumentController implements Initializable {

    private final IUsuarioValidador usuarioValidador;
    
    public FXMLDocumentController(IUsuarioValidador usuarioValidador) {
    this.usuarioValidador = usuarioValidador; 
    }  
    DialogUtil util = new DialogUtil();
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtSenha;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField numId;
    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnAtualizar;
    @FXML
    private TableView<UsuarioDTO> tblUsuario;
    @FXML
    private TableColumn<UsuarioDTO, Integer> colId;
    @FXML
    private TableColumn<UsuarioDTO, String> colNome;
    @FXML
    private TableColumn<UsuarioDTO, String> colEmail;
    @FXML
    private TableColumn<UsuarioDTO, String> colLogin;
    @FXML
    private TableColumn<UsuarioDTO, String> colSenha;
    
    @FXML   
    private void selecionarUsuario() {
    UsuarioDTO usuarioSelecionado = tblUsuario.getSelectionModel().getSelectedItem();
    if (usuarioSelecionado != null) {
        numId.setText(String.valueOf(usuarioSelecionado.getId()));
        txtNome.setText(usuarioSelecionado.getNome());
        txtEmail.setText(usuarioSelecionado.getEmail());
        txtLogin.setText(usuarioSelecionado.getLogin());
        txtSenha.setText(usuarioSelecionado.getSenha());
        }
    }
    
    @FXML
    private void adicionar(ActionEvent event) {
        if (!usuarioValidador.validarUsuario(txtNome.getText(), txtEmail.getText(), txtSenha.getText(), txtLogin.getText())) {
            return;
        }
        UsuarioDTO objUsuario = new UsuarioDTO();
        objUsuario.setNome(txtNome.getText());
        objUsuario.setEmail(txtEmail.getText());
        objUsuario.setSenha(txtSenha.getText());
        objUsuario.setLogin(txtLogin.getText());
        
        new UsuarioDAO().cadastrarUsuario(objUsuario);
        limparCampos();
        listarUsuarios();
    }
    
    @FXML
    private void remover(ActionEvent event) {
        new UsuarioDAO().deleteUsuario(Integer.parseInt(numId.getText()));
        limparCampos();
        listarUsuarios();
    }
    
//funcao para deixar usuarios do banco de dados serem visiveis na tabela
    @FXML
    private void listarUsuarios() {
    UsuarioDAO dao = new UsuarioDAO();
    List<UsuarioDTO> lista = dao.listarUsuarios(); 
    
    ObservableList<UsuarioDTO> dados = FXCollections.observableArrayList(lista);
    tblUsuario.setItems(dados);  
}
    
//inicializando a tabela de usuarios
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));

        listarUsuarios();
    }    
    @FXML
    private void atualizarUsuario(){ 
        if(numId.getText() != null){
            UsuarioDAO dao = new UsuarioDAO();
            dao.updateUsuario(Integer.parseInt(numId.getText()), txtNome.getText(), txtEmail.getText(), txtSenha.getText(), txtLogin.getText());
            limparCampos();
            listarUsuarios();
        }
        else
            System.out.println("Sem id selecionado!");
    }
    
    @FXML
    private void limparCampos(){
        numId.setText("");
        txtNome.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtLogin.setText("");
        listarUsuarios();
    }   

    public void setUsuarioValidador(IUsuarioValidador validador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}