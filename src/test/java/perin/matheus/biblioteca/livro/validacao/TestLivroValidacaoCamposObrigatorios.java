package perin.matheus.biblioteca.livro.validacao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.livro.LivroEntity;
import perin.matheus.biblioteca.livro.enums.LivroCategoria;
import perin.matheus.biblioteca.livro.validacoes.LivroValidacaoCamposObrigatorios;

import java.time.LocalDate;

import static perin.matheus.biblioteca.utils.CampoObrigatorioValidacao.MENSAGEM;

public class TestLivroValidacaoCamposObrigatorios {

    private final LivroValidacaoCamposObrigatorios validacao = new LivroValidacaoCamposObrigatorios();

    @Test
    public void testeAutorObrigatorio() {
        LivroEntity entity = LivroEntity.builder()
            .isbn("123")
            .titulo("Livro")
            .categoria(LivroCategoria.ACAO)
            .dataPublicacao(LocalDate.now())
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Autor"), validacaoException.getMessage());
    }

    @Test
    public void testeIsbnObrigatorio() {
        LivroEntity entity = LivroEntity.builder()
            .autor("Autor")
            .titulo("Livro")
            .categoria(LivroCategoria.ACAO)
            .dataPublicacao(LocalDate.now())
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "ISBN"), validacaoException.getMessage());
    }

    @Test
    public void testeCategoriaObrigatorio() {
        LivroEntity entity = LivroEntity.builder()
            .autor("Autor")
            .isbn("123")
            .titulo("Livro")
            .dataPublicacao(LocalDate.now())
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Categoria"), validacaoException.getMessage());
    }

    @Test
    public void testeTituloObrigatorio() {
        LivroEntity entity = LivroEntity.builder()
            .autor("Autor")
            .isbn("123")
            .categoria(LivroCategoria.ACAO)
            .dataPublicacao(LocalDate.now())
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Título"), validacaoException.getMessage());
    }

    @Test
    public void testeDataPublicacaoObrigatorio() {
        LivroEntity entity = LivroEntity.builder()
            .autor("Autor")
            .isbn("123")
            .categoria(LivroCategoria.ACAO)
            .titulo("Livro")
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Data de publicação"), validacaoException.getMessage());
    }

}
