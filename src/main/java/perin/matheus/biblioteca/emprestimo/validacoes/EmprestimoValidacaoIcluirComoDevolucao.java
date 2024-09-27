package perin.matheus.biblioteca.emprestimo.validacoes;

import org.springframework.stereotype.Component;
import perin.matheus.biblioteca.base.listener.validacao.Validacao;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.emprestimo.EmprestimoEntity;
import perin.matheus.biblioteca.emprestimo.enums.EmprestimoSituacao;

import java.util.Objects;

@Component
public class EmprestimoValidacaoIcluirComoDevolucao implements Validacao<EmprestimoEntity> {

    @Override
    public void validar(EmprestimoEntity entity) throws ValidacaoException {
        if (Objects.isNull(entity.getId()) && EmprestimoSituacao.DEVOLVIDO.equals(entity.getSituacao()))
            throw new ValidacaoException("Não é possivel incluir um empréstimo com situação de devolução");
    }

}
