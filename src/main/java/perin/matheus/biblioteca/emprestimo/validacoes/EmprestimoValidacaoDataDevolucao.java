package perin.matheus.biblioteca.emprestimo.validacoes;

import org.springframework.stereotype.Component;
import perin.matheus.biblioteca.base.listener.validacao.Validacao;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.emprestimo.EmprestimoEntity;

import java.time.LocalDate;

@Component
public class EmprestimoValidacaoDataDevolucao implements Validacao<EmprestimoEntity> {

    @Override
    public void validar(EmprestimoEntity entity) throws ValidacaoException {
        if (!entity.getDataDevolucao().isAfter(entity.getDataEmprestimo()))
            throw new ValidacaoException("Data de devolução deve ser posterior a data de emprestimo");
    }

}
