package perin.matheus.biblioteca.emprestimo.validacoes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.emprestimo.EmprestimoEntity;
import perin.matheus.biblioteca.emprestimo.enums.EmprestimoSituacao;
import perin.matheus.biblioteca.livro.LivroEntity;
import perin.matheus.biblioteca.usuario.UsuarioEntity;

import java.time.LocalDate;
import java.util.Set;

import static perin.matheus.biblioteca.utils.CampoObrigatorioValidacao.MENSAGEM;

public class TestEmprestimoValidacaoCamposObrigatorios {

    private final EmprestimoValidacaoCamposObrigatorios validacao = new EmprestimoValidacaoCamposObrigatorios();

    @Test
    public void testDataEmprestimoObrigatoria() {
        EmprestimoEntity entity = EmprestimoEntity.builder()
            .dataDevolucao(LocalDate.now())
            .livros(Set.of(LivroEntity.builder().build()))
            .situacao(EmprestimoSituacao.EMPRESTADO)
            .usuario(UsuarioEntity.builder().build())
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Data de emprestimo"), validacaoException.getMessage());
    }

    @Test
    public void testDataDevolucaoObrigatoria() {
        EmprestimoEntity entity = EmprestimoEntity.builder()
            .dataEmprestimo(LocalDate.now())
            .livros(Set.of(LivroEntity.builder().build()))
            .situacao(EmprestimoSituacao.EMPRESTADO)
            .usuario(UsuarioEntity.builder().build())
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Data de devolução"), validacaoException.getMessage());
    }

    @Test
    public void testUsuarioObrigatorio() {
        EmprestimoEntity entity = EmprestimoEntity.builder()
            .dataEmprestimo(LocalDate.now())
            .dataDevolucao(LocalDate.now())
            .livros(Set.of(LivroEntity.builder().build()))
            .situacao(EmprestimoSituacao.EMPRESTADO)
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Usuário"), validacaoException.getMessage());
    }

    @Test
    public void testSituacaoObrigatoria() {
        EmprestimoEntity entity = EmprestimoEntity.builder()
            .dataEmprestimo(LocalDate.now())
            .dataDevolucao(LocalDate.now())
            .livros(Set.of(LivroEntity.builder().build()))
            .usuario(UsuarioEntity.builder().build())
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Situação"), validacaoException.getMessage());
    }

    @Test
    public void testLivrosObrigatorio() {
        EmprestimoEntity entity = EmprestimoEntity.builder()
            .dataEmprestimo(LocalDate.now())
            .dataDevolucao(LocalDate.now())
            .livros(Set.of())
            .usuario(UsuarioEntity.builder().build())
            .situacao(EmprestimoSituacao.EMPRESTADO)
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals(String.format(MENSAGEM, "Livros"), validacaoException.getMessage());
    }

}
