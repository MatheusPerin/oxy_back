package perin.matheus.biblioteca.emprestimo.validacoes;

import org.springframework.stereotype.Component;
import perin.matheus.biblioteca.base.listener.validacao.Validacao;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.emprestimo.EmprestimoEntity;
import perin.matheus.biblioteca.utils.CampoObrigatorioValidacao;

import java.time.LocalDate;

@Component
public class EmprestimoValidacaoDataEmprestimo implements Validacao<EmprestimoEntity> {

    @Override
    public void validar(EmprestimoEntity entity) throws ValidacaoException {
        if (entity.getDataEmprestimo().isBefore(LocalDate.now()))
            throw new ValidacaoException("Data de emprestimo não pode ser anterior a data atual");
    }

}
