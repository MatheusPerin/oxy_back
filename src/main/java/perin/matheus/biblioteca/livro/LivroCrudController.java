package perin.matheus.biblioteca.livro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import perin.matheus.biblioteca.base.controller.CrudController;
import perin.matheus.biblioteca.livro.jdbc.models.LivroQuantidadePorCategoriaDTO;

import java.util.List;

/**
 * Controlador para operações relacionadas a livros
 */
@RestController
@RequestMapping("/livro")
public class LivroCrudController extends CrudController<LivroEntity, Long> {

    /**
     * Endpoint para obter recomendações de livros baseadas nas categorias
     * de interesse de um usuário específico.
     *
     * @param userId Identificador do usuário para o qual as recomendações serão geradas.
     * @return ResponseEntity contendo uma lista de objetos LivroQuantidadePorCategoriaDTO
     *         que representam a quantidade de livros recomendados por categoria.
     */
    @GetMapping("/recomendacao/{userId}")
    public ResponseEntity<List<LivroQuantidadePorCategoriaDTO>> recomendacao(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(((LivroCrudService) super.getService()).pesquisarLivroQuantidadePorCategoriaDTORecomendacaoPorUsuario(userId));
    }

    /**
     * Endpoint para obter uma lista de livros que estão disponíveis.
     *
     * @return ResponseEntity contendo uma lista de objetos LivroEntity
     *         que representam os livros disponíveis.
     */
    @GetMapping("/disponiveis")
    public ResponseEntity<List<LivroEntity>> disponiveis() {
        return ResponseEntity.ok(((LivroCrudService) super.getService()).pesquisarLivrosDisponiveis());
    }

}
