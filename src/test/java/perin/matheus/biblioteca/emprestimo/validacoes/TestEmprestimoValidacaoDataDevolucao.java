package perin.matheus.biblioteca.emprestimo.validacoes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.emprestimo.EmprestimoEntity;

import java.time.LocalDate;

public class TestEmprestimoValidacaoDataDevolucao {

    private final EmprestimoValidacaoDataDevolucao validacao = new EmprestimoValidacaoDataDevolucao();

    @Test
    public void testDataDevelucaoPosteriorDataEmprestimo() {
        EmprestimoEntity entity = EmprestimoEntity.builder()
            .dataEmprestimo(LocalDate.now())
            .dataDevolucao(LocalDate.now().minusDays(1))
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals("Data de devolução deve ser posterior a data de emprestimo", validacaoException.getMessage());
    }

}
