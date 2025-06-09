package validator;

import java.util.ArrayList;
import java.util.List;
import util.DialogUtil;

public class UsuarioValidador implements IUsuarioValidador{
    DialogUtil util = new DialogUtil();
    @Override
    public boolean validarUsuario(String nome, String email, String senha, String login) {
        List<Validador<String>> validadores = new ArrayList<>();
        
        validadores.add(new CampoObrigatorioValidador("Nome", nome));
        validadores.add(new CampoObrigatorioValidador("E-mail", email));
        validadores.add(new CampoObrigatorioValidador("Senha", senha));
        validadores.add(new CampoObrigatorioValidador("Login", login));
        
        validadores.add(new EmailValidador(email));
        
        for (Validador<String> validador : validadores)  {
            if (!validador.validar(validador.getValor())) {
                util.showWarning(validador.getMensagemErro());
                return false;
            }
        }
        return true;
    }
}


