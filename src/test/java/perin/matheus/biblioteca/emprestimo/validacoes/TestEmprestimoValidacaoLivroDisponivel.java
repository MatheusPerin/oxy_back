package perin.matheus.biblioteca.emprestimo.validacoes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.emprestimo.EmprestimoEntity;
import perin.matheus.biblioteca.livro.LivroEntity;
import perin.matheus.biblioteca.livro.jdbc.LivroJdbcPesquisas;
import perin.matheus.biblioteca.livro.jdbc.models.LivroDisponivelDTO;

import java.util.List;
import java.util.Set;

public class TestEmprestimoValidacaoLivroDisponivel {

    @InjectMocks
    private EmprestimoValidacaoLivroDisponivel validacao;

    @Mock
    private LivroJdbcPesquisas livroJdbcPesquisas;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);

        Mockito
            .when(livroJdbcPesquisas.pesquisarLivrosDisponiveisDTOPorIds(Mockito.anyList()))
            .thenReturn(
                List.of(
                    LivroDisponivelDTO.builder()
                        .id(1L)
                        .titulo("Livro 1")
                        .disponivel(false)
                    .build(),
                    LivroDisponivelDTO.builder()
                        .id(2L)
                        .titulo("Livro 2")
                        .disponivel(true)
                    .build(),
                    LivroDisponivelDTO.builder()
                        .id(3L)
                        .titulo("Livro 3")
                        .disponivel(false)
                    .build()
                )
            );
    }

    @Test
    public void testLivroDisponivel() {
        EmprestimoEntity entity = EmprestimoEntity.builder()
            .livros(
                Set.of(
                    LivroEntity.builder().id(1L).build(),
                    LivroEntity.builder().id(2L).build(),
                    LivroEntity.builder().id(3L).build()
                )
            )
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        final String MENSAGEM_ESPERADA =
            "Os seguintes livros não estão disponiveis: \n" +
            " * 1 - Livro 1\n" +
            " * 3 - Livro 3";

        Assertions.assertEquals(MENSAGEM_ESPERADA, validacaoException.getMessage());
    }

}
