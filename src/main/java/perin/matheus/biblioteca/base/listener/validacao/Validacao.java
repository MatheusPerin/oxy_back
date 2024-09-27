package perin.matheus.biblioteca.base.listener.validacao;

/**
 * Interface para definir a lógica de validação de entidades.
 *
 * @param <ENTITY> O tipo da entidade que será validada.
 */
public interface Validacao<ENTITY> {

    /**
     * Método responsável por validar a entidade fornecida.
     *
     * @param entity A entidade a ser validada.
     * @throws ValidacaoException Se a validação falhar, uma exceção de validação será lançada.
     */
    void validar(ENTITY entity) throws ValidacaoException;

}
