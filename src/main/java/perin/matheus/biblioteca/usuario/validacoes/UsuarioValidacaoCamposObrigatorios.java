package perin.matheus.biblioteca.usuario.validacoes;

import org.springframework.stereotype.Component;
import perin.matheus.biblioteca.base.listener.validacao.Validacao;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.usuario.UsuarioEntity;
import perin.matheus.biblioteca.utils.CampoObrigatorioValidacao;

@Component
public class UsuarioValidacaoCamposObrigatorios implements Validacao<UsuarioEntity> {

    @Override
    public void validar(UsuarioEntity entity) throws ValidacaoException {
        CampoObrigatorioValidacao.validarString(entity.getNome(), "Nome");
        CampoObrigatorioValidacao.validarString(entity.getEmail(), "E-mail");
        CampoObrigatorioValidacao.validar(entity.getDataCadastro(), "Data de cadastro");
        CampoObrigatorioValidacao.validarString(entity.getTelefone(), "Telefone");
    }

}
