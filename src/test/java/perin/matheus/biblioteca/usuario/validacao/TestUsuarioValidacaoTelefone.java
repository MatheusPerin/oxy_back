package perin.matheus.biblioteca.usuario.validacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.usuario.UsuarioEntity;
import perin.matheus.biblioteca.usuario.validacoes.UsuarioValidacaoTelefone;

import static perin.matheus.biblioteca.usuario.validacoes.UsuarioValidacaoTelefone.MENSAGEM_BASE;

public class TestUsuarioValidacaoTelefone {

    private final UsuarioValidacaoTelefone validacao = new UsuarioValidacaoTelefone();

    @Test
    public void testTelefoneNumerico() {
        UsuarioEntity entity = UsuarioEntity.builder()
            .telefone("asdasd")
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM_BASE, "Deve ser composto somente por caracteres numérico"), validacaoException.getMessage());
    }

    @Test
    public void testTelefoneQuantidadeCaracteresAMenos() {
        UsuarioEntity entity = UsuarioEntity.builder()
            .telefone("123456789")
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM_BASE, "Deve conter 10 ou 11 caracteres"), validacaoException.getMessage());
    }

    @Test
    public void testTelefoneQuantidadeCaracteresAMais() {
        UsuarioEntity entity = UsuarioEntity.builder()
            .telefone("123456789012")
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM_BASE, "Deve conter 10 ou 11 caracteres"), validacaoException.getMessage());
    }

    @Test
    public void testTelefoneComposicao() {
        UsuarioEntity entity = UsuarioEntity.builder()
            .telefone("9999999999")
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM_BASE, "O tenelefone não pode ser composto somente pelo digito: 9"), validacaoException.getMessage());
    }

    @Test
    public void testTelefoneDdd() {
        UsuarioEntity entity = UsuarioEntity.builder()
            .telefone("1012345678")
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM_BASE, "O DDD (10) está inválido para o Basil"), validacaoException.getMessage());
    }

    @Test
    public void testTelefoneValido() {
        UsuarioEntity entity = UsuarioEntity.builder()
            .telefone("4699429523")
        .build();

        Assertions.assertDoesNotThrow(() -> validacao.validar(entity));
    }

}
