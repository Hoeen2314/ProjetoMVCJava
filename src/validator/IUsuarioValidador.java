package validator;
public interface IUsuarioValidador {
    boolean validarUsuario(String nome, String email, String senha, String login);
}
