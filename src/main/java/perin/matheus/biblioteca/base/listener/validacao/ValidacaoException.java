package perin.matheus.biblioteca.base.listener.validacao;

import perin.matheus.biblioteca.config.exception.SistemaException;

public class ValidacaoException extends SistemaException {

    public ValidacaoException(String message) {
        super(message);
    }

}
