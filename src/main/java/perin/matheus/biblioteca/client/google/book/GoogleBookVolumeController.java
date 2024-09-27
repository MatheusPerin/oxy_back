package perin.matheus.biblioteca.client.google.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import perin.matheus.biblioteca.client.google.book.modelos.BookVolume;
import perin.matheus.biblioteca.client.google.book.modelos.BookVolumes;
import perin.matheus.biblioteca.client.google.book.modelos.LivroGoogle;

import java.util.List;

/**
 * Controlador REST para gerenciar operações relacionadas aos volumes de livros do Google.
 * Este controlador expõe endpoints para buscar informações de livros utilizando a API do Google Books.
 */
@RestController
@RequestMapping("/google/livro")
public class GoogleBookVolumeController {

    @Autowired
    private GoogleBookVolumeService service;

    /**
     * Obtém um livro específico a partir do seu ID.
     *
     * @param id O ID do livro a ser pesquisado.
     * @return O objeto LivroGoogle correspondente ao ID fornecido.
     */
    @GetMapping("/{id}")
    public LivroGoogle get(@PathVariable("id") String id) {
        return service.pesquisarPorId(id);
    }

    /**
     * Obtém uma lista de livros que correspondem ao título fornecido.
     *
     * @param titulo O título do livro a ser pesquisado.
     * @return Uma lista de objetos LivroGoogle que correspondem ao título fornecido.
     */
    @GetMapping("/titulo/{titulo}")
    public List<LivroGoogle> porTitulo(@PathVariable("titulo") String titulo) {
        return service.pesquisarPorTitulo(titulo);
    }

}
