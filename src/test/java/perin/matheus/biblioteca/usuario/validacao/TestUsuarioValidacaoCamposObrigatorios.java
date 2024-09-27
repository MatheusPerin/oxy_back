package perin.matheus.biblioteca.usuario.validacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.usuario.UsuarioEntity;
import perin.matheus.biblioteca.usuario.validacoes.UsuarioValidacaoCamposObrigatorios;

import java.time.LocalDate;

import static perin.matheus.biblioteca.utils.CampoObrigatorioValidacao.MENSAGEM;

public class TestUsuarioValidacaoCamposObrigatorios {

    private final UsuarioValidacaoCamposObrigatorios validacao = new UsuarioValidacaoCamposObrigatorios();

    @Test
    public void testNomeObrigatorio() {
        UsuarioEntity entity = UsuarioEntity.builder()
            .nome("")
            .dataCadastro(LocalDate.now())
            .email("teste@teste.com")
            .telefone("99999999999")
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Nome"), validacaoException.getMessage());
    }

    @Test
    public void testEmailObrigatorio() {
        UsuarioEntity entity = UsuarioEntity.builder()
            .nome("Usuario")
            .dataCadastro(LocalDate.now())
            .telefone("99999999999")
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "E-mail"), validacaoException.getMessage());
    }

    @Test
    public void testDataCadastroObrigatorio() {
        UsuarioEntity entity = UsuarioEntity.builder()
            .nome("Usuario")
            .email("teste@teste.com")
            .telefone("99999999999")
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Data de cadastro"), validacaoException.getMessage());
    }

    @Test
    public void testTelefoneObrigatorio() {
        UsuarioEntity entity = UsuarioEntity.builder()
            .nome("Usuario")
            .dataCadastro(LocalDate.now())
            .email("teste@teste.com")
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Telefone"), validacaoException.getMessage());
    }

}
