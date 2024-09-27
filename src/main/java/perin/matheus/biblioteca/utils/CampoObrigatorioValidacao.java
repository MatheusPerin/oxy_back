package perin.matheus.biblioteca.utils;

import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;

import java.util.Collection;
import java.util.Objects;

public class CampoObrigatorioValidacao {

    public static final String MENSAGEM = "Necessário informar valor para o campo: %s";

    public static void validar(Object valor, String campo) throws ValidacaoException {
        if (Objects.isNull(valor))
            throw new ValidacaoException(String.format(MENSAGEM, campo));
    }

    public static void validarCollection(Collection<?> valor, String campo) throws ValidacaoException {
        validar(valor, campo);

        if (valor.isEmpty())
            throw new ValidacaoException(String.format(MENSAGEM, campo));
    }

    public static void validarString(String valor, String campo) throws ValidacaoException {
        validar(valor, campo);

        if (valor.trim().isEmpty())
            throw new ValidacaoException(String.format(MENSAGEM, campo));
    }

}
