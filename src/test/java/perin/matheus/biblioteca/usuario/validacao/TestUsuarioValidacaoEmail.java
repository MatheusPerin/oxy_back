package perin.matheus.biblioteca.usuario.validacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.usuario.UsuarioEntity;
import perin.matheus.biblioteca.usuario.validacoes.UsuarioValidacaoEmail;

import java.util.List;

public class TestUsuarioValidacaoEmail {

    private final UsuarioValidacaoEmail validacao = new UsuarioValidacaoEmail();

    @Test
    public void testEmailsInvalidos() {
        final List<String> emails = List.of(
            "teste",
            "teste@",
            "teste@aa.c",
            "teste@com",
            "teste@123.123",
            "teste$&@ggg.com"
        );

        for (String email: emails) {
            UsuarioEntity entity = UsuarioEntity.builder().email(email).build();

            ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity), email.concat(" - deve ser inválido"));

            Assertions.assertEquals("Email inválido", validacaoException.getMessage());
        }
    }

}
