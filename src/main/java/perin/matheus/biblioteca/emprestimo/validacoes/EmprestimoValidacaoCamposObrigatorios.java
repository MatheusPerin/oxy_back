package perin.matheus.biblioteca.emprestimo.validacoes;

import org.springframework.stereotype.Component;
import perin.matheus.biblioteca.base.listener.validacao.Validacao;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.emprestimo.EmprestimoEntity;
import perin.matheus.biblioteca.utils.CampoObrigatorioValidacao;

@Component
public class EmprestimoValidacaoCamposObrigatorios implements Validacao<EmprestimoEntity> {

    @Override
    public void validar(EmprestimoEntity entity) throws ValidacaoException {
        CampoObrigatorioValidacao.validar(entity.getDataEmprestimo(), "Data de emprestimo");
        CampoObrigatorioValidacao.validar(entity.getDataDevolucao(), "Data de devolução");
        CampoObrigatorioValidacao.validar(entity.getUsuario(), "Usuário");
        CampoObrigatorioValidacao.validar(entity.getSituacao(), "Situação");
        CampoObrigatorioValidacao.validarCollection(entity.getLivros(), "Livros");
    }

}
