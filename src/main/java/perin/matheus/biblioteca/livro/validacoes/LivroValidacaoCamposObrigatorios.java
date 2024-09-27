package perin.matheus.biblioteca.livro.validacoes;

import org.springframework.stereotype.Component;
import perin.matheus.biblioteca.base.listener.validacao.Validacao;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.livro.LivroEntity;
import perin.matheus.biblioteca.utils.CampoObrigatorioValidacao;

import java.util.Objects;

@Component
public class LivroValidacaoCamposObrigatorios implements Validacao<LivroEntity> {

    @Override
    public void validar(LivroEntity entity) throws ValidacaoException {
        CampoObrigatorioValidacao.validarString(entity.getAutor(), "Autor");
        CampoObrigatorioValidacao.validarString(entity.getIsbn(), "ISBN");
        CampoObrigatorioValidacao.validar(entity.getCategoria(), "Categoria");
        CampoObrigatorioValidacao.validarString(entity.getTitulo(), "Título");
        CampoObrigatorioValidacao.validar(entity.getDataPublicacao(), "Data de publicação");
    }

}
