package perin.matheus.biblioteca.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.config.exception.SistemaException;

import java.util.Objects;

@ControllerAdvice
public class ConfigExcptionHandler {

    public record ExceptionModel(int httpStatus, String mensagem) {}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionModel> handle(Exception ex, WebRequest request) {
        if (ex instanceof SistemaException)
            return ResponseEntity
                .badRequest()
                .body(new ExceptionModel(HttpStatus.BAD_REQUEST.value(), Objects.nonNull(ex.getMessage()) ? ex.getMessage() : "Erro inesperado"));

        return ResponseEntity
            .internalServerError()
            .body(
                new ExceptionModel(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    Objects.nonNull(ex.getMessage()) ? ex.getMessage() : "Erro inesperado"
                )
            );
    }

}
