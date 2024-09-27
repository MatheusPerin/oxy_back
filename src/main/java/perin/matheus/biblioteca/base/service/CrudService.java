package perin.matheus.biblioteca.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import perin.matheus.biblioteca.base.listener.CrudListener;
import perin.matheus.biblioteca.base.listener.evento.Evento;
import perin.matheus.biblioteca.base.listener.validacao.Validacao;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Classe de serviço genérica que fornece operações CRUD (Create, Read, Update, Delete)
 * para entidades utilizando um repositório JPA.
 *
 * @param <ENTITY> Tipo da entidade que será manipulada por este serviço.
 * @param <ID> Tipo do identificador da entidade.
 */
public abstract class CrudService<ENTITY, ID> {

    /**
     * Repositório JPA que fornece as operações de persistência para a entidade.
     */
    private JpaRepository<ENTITY, ID> repository;

    /**
     * Contexto da aplicação para acesso a beans do Spring.
     */
    private ApplicationContext context;

    /**
     * Método de injeção de dependência para o repositório JPA.
     *
     * @param repository Instância do repositório JPA a ser injetada.
     */
    @Autowired
    public void setRepository(JpaRepository<ENTITY, ID> repository) {
        this.repository = repository;
    }

    /**
     * Método de injeção de dependência para o contexto da aplicação.
     *
     * @param context Instância do ApplicationContext a ser injetada.
     */
    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Método para listar todas as entidades.
     *
     * @return Lista de todas as entidades.
     */
    public List<ENTITY> listar() {
        return repository.findAll();
    }

    /**
     * Método para salvar uma entidade.
     *
     * @param entity Entidade a ser salva.
     * @return Entidade salva.
     * @throws ValidacaoException Se a entidade não for válida.
     */
    public ENTITY salvar(ENTITY entity) throws ValidacaoException {
        processarListener(entity);

        return repository.save(entity);
    }

    /**
     * Método para buscar uma entidade pelo seu identificador.
     *
     * @param id Identificador da entidade a ser recuperada.
     * @return Um Optional contendo a entidade encontrada ou vazio caso não exista.
     */
    public Optional<ENTITY> pesquisarPorId(ID id) {
        return repository.findById(id);
    }

    /**
     * Método para excluir uma entidade pelo seu identificador.
     *
     * @param id Identificador da entidade a ser excluída.
     */
    public void excluirPorId(ID id) {
        repository.deleteById(id);
    }

    /**
     * Método privado que processa eventos e validações para a entidade,
     * se a classe da entidade estiver anotada com @CrudListener.
     *
     * @param entity Entidade a ser processada.
     * @throws ValidacaoException Se a validação falhar.
     */
    private <T> void processarListener(T entity) throws ValidacaoException {
        if (Objects.isNull(entity)) return;

        Class<?> clazz = entity.getClass();

        if (!clazz.isAnnotationPresent(CrudListener.class)) return;

        CrudListener crudListener = clazz.getAnnotation(CrudListener.class);

        processarEventos(entity, crudListener);

        processarValidacoes(entity, crudListener);
    }

    /**
     * Método privado que processa eventos associados à entidade.
     *
     * @param entity Entidade para a qual os eventos serão processados.
     * @param crudListener Anotação que contém informações sobre os eventos.
     */
    @SuppressWarnings("unchecked")
    private <T> void processarEventos(T entity, CrudListener crudListener) {
        List<Evento<T>> eventos = Stream
            .of(crudListener.eventosBeans())
            .map(eventoClass -> {
                try {
                    return (Evento<T>) context.getBean(eventoClass);
                } catch (Exception e) {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .toList();

        for (Evento<T> evento: eventos) {
            evento.executar(entity);
        }
    }

    /**
     * Método privado que processa validações associadas à entidade.
     *
     * @param entity Entidade para a qual as validações serão processadas.
     * @param crudListener Anotação que contém informações sobre as validações.
     * @throws ValidacaoException Se alguma validação falhar.
     */
    @SuppressWarnings("unchecked")
    private <T> void processarValidacoes(T entity, CrudListener crudListener) throws ValidacaoException {
        List<Validacao<T>> validacoes = Stream
            .of(crudListener.validacoesBeans())
            .map(eventoClass -> {
                try {
                    return (Validacao<T>) context.getBean(eventoClass);
                } catch (Exception e) {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .toList();

        for (Validacao<T> validacao: validacoes) {
            validacao.validar(entity);
        }
    }

}
