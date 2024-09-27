package perin.matheus.biblioteca.base.listener.evento;

/**
 * Interface para definir um evento que pode ser executado em uma entidade.
 *
 * @param <ENTITY> O tipo da entidade sobre a qual o evento será executado.
 */
public interface Evento<ENTITY> {

    /**
     * Método responsável por executar a lógica do evento na entidade fornecida.
     *
     * @param entity A entidade sobre a qual o evento será executado.
     */
    void executar(ENTITY entity);

}
