package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConexaoDAO{
    
    static String stringconecxao = "jdbc:postgresql://localhost:5432/prova1";
    static String usuario = "postgres";
    static String senha = "postgres";
    
    public Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(stringconecxao, usuario, senha);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
}
