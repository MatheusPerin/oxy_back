package perin.matheus.biblioteca.base.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.base.service.CrudService;

import java.util.List;

/**
 * Classe abstrata que fornece métodos CRUD (Create, Read, Update, Delete)
 * para entidades genéricas em um controlador REST.
 *
 * @param <ENTITY> Tipo da entidade que será manipulada por este controlador.
 * @param <ID> Tipo do identificador da entidade.
 */
@Getter
public abstract class CrudController<ENTITY, ID> {

    /**
     * Serviço que fornece as operações CRUD para a entidade.
     */
    private CrudService<ENTITY, ID> service;

    /**
     * Método de injeção de dependência para o serviço CRUD.
     *
     * @param service Instância do serviço CRUD a ser injetada.
     */
    @Autowired
    public void setService(CrudService<ENTITY, ID> service) {
        this.service = service;
    }

    /**
     * Método para obter uma entidade pelo seu identificador.
     *
     * @param id Identificador da entidade a ser recuperada.
     * @return Resposta HTTP contendo a entidade encontrada ou um status 404 caso não exista.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ENTITY> get(@PathVariable("id") ID id) {
        return ResponseEntity.of(service.pesquisarPorId(id));
    }

    /**
     * Método para listar todas as entidades.
     *
     * @return Resposta HTTP com a lista de entidades.
     */
    @GetMapping("/listar")
    public ResponseEntity<List<ENTITY>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    /**
     * Método para salvar uma entidade.
     *
     * @param entity Entidade a ser salva.
     * @return Resposta HTTP com a entidade salva.
     * @throws ValidacaoException Se a entidade não for válida.
     */
    @PostMapping
    public ResponseEntity<ENTITY> post(@RequestBody ENTITY entity) throws ValidacaoException {
        return ResponseEntity.ok(service.salvar(entity));
    }

    /**
     * Método para excluir uma entidade pelo seu identificador.
     *
     * @param id Identificador da entidade a ser excluída.
     * @return Resposta HTTP com status 200 OK se a exclusão for bem-sucedida.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") ID id) {
        service.excluirPorId(id);

        return ResponseEntity.ok().build();
    }

}
