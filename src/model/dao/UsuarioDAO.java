package model.dao;

import conexao.ConexaoDAO;
import model.dto.UsuarioDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DialogUtil;

public class UsuarioDAO {
    DialogUtil util = new DialogUtil();
    public void cadastrarUsuario(UsuarioDTO usuario) {
        
        String sql = "INSERT INTO Usuario (nome, email, senha, login) Values (?, ?, ?, ?)";
        
        try (Connection c = new ConexaoDAO().getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getLogin());
            
            ps.executeUpdate();
            util.alertAdd();
    
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void selecionarUsuario(String nome){
        String sql = "SELECT * FROM Usuario ";
        try (Connection c = new ConexaoDAO().getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery();){
            while(rs.next()){
                System.out.println("#" + rs.getInt("id") + "#" + rs.getString("nome"));
            }
            
        }catch(SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    public void deleteUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try (Connection c = new ConexaoDAO().getConnection(); 
                PreparedStatement ps = c.prepareStatement(sql)) {
        
        ps.setInt(1, id);  
        
        int rowsAffected = ps.executeUpdate(); 
        
        if (rowsAffected > 0) {
            util.alertDelete();
            
        } else {
            util.alertDeleteError();
        }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateUsuario(int id, String nome, String email, String senha, String login) {
    String sql = "UPDATE Usuario SET nome = ?, email = ?, senha = ?, login = ? WHERE id = ?";
    
    try (Connection c = new ConexaoDAO().getConnection(); 
         PreparedStatement ps = c.prepareStatement(sql)) {
        
        ps.setString(1, nome);
        ps.setString(2, email);
        ps.setString(3, senha);
        ps.setString(4, login);
        ps.setInt(5, id);  
        
        int rowsAffected = ps.executeUpdate(); 
        
        if (rowsAffected > 0) {
            System.out.println("Usuário atualizado com sucesso.");
        } else {
            System.out.println("Nenhum usuário encontrado com o ID especificado.");
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public List<UsuarioDTO> listarUsuarios() {
    List<UsuarioDTO> lista = new ArrayList<>();
    try {
        Connection conn = new ConexaoDAO().getConnection();
        String sql = "SELECT * FROM Usuario";
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            UsuarioDTO u = new UsuarioDTO();
            u.setId(rs.getInt("id"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setLogin(rs.getString("login"));
            lista.add(u);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}
    
    
}
