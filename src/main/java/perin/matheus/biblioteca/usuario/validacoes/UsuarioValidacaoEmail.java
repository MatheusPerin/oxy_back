package perin.matheus.biblioteca.usuario.validacoes;

import org.springframework.stereotype.Component;
import perin.matheus.biblioteca.base.listener.validacao.Validacao;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.usuario.UsuarioEntity;

import java.util.regex.Pattern;

@Component
public class UsuarioValidacaoEmail implements Validacao<UsuarioEntity> {

    private final String REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    @Override
    public void validar(UsuarioEntity usuarioEntity) throws ValidacaoException {
        if (!Pattern.matches(REGEX, usuarioEntity.getEmail()))
            throw new ValidacaoException("Email inválido");
    }

}
