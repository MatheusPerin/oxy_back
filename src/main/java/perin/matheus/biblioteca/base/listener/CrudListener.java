package perin.matheus.biblioteca.base.listener;

import perin.matheus.biblioteca.base.listener.evento.Evento;
import perin.matheus.biblioteca.base.listener.validacao.Validacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para marcar classes de entidade que requerem
 * o processamento de eventos e validações durante operações CRUD.
 *
 * Essa anotação permite associar eventos e validações a uma entidade,
 * permitindo que lógica adicional seja executada automaticamente durante
 * o ciclo de vida da entidade.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CrudListener {

    /**
     * Um array de classes que implementam a interface Evento.
     * Esses eventos serão executados quando a entidade for processada.
     *
     * @return Um array de classes de eventos que devem ser executados.
     */
    Class<? extends Evento<?>>[] eventosBeans() default {};

    /**
     * Um array de classes que implementam a interface Validacao.
     * Essas validações serão aplicadas à entidade antes de suas operações.
     *
     * @return Um array de classes de validação que devem ser aplicadas.
     */
    Class<? extends Validacao<?>>[] validacoesBeans() default {};

}
