package perin.matheus.biblioteca.emprestimo.validacoes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.emprestimo.EmprestimoEntity;
import perin.matheus.biblioteca.emprestimo.enums.EmprestimoSituacao;

public class TestEmprestimoValidacaoIncluirComoDevolucao {

    private final EmprestimoValidacaoIcluirComoDevolucao validacao = new EmprestimoValidacaoIcluirComoDevolucao();

    @Test
    public void testDataDevelucaoPosteriorDataEmprestimo() {
        EmprestimoEntity entity = EmprestimoEntity.builder()
            .situacao(EmprestimoSituacao.DEVOLVIDO)
        .build();

        ValidacaoException validacaoException = Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(entity));

        Assertions.assertEquals("Não é possivel incluir um empréstimo com situação de devolução", validacaoException.getMessage());
    }

}
