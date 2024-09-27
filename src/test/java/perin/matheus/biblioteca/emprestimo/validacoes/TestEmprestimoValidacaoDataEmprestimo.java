package perin.matheus.biblioteca.emprestimo.validacoes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.emprestimo.EmprestimoEntity;

import java.time.LocalDate;

public class TestEmprestimoValidacaoDataEmprestimo {

    private final EmprestimoValidacaoDataEmprestimo validacao = new EmprestimoValidacaoDataEmprestimo();

    @Test
    public void testDataEmprestimoAnteriorDataAtual() {
        EmprestimoEntity entity = EmprestimoEntity.builder()
            .dataEmprestimo(LocalDate.now().minusDays(1))
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals("Data de emprestimo não pode ser anterior a data atual", validacaoException.getMessage());
    }

}
