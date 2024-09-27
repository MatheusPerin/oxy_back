package perin.matheus.biblioteca.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perin.matheus.biblioteca.base.service.CrudService;
import perin.matheus.biblioteca.livro.jdbc.LivroJdbcPesquisas;
import perin.matheus.biblioteca.livro.jdbc.models.LivroQuantidadePorCategoriaDTO;

import java.util.List;

@Service
public class LivroCrudService extends CrudService<LivroEntity, Long> {

    private LivroJdbcPesquisas livroJdbcPesquisas;

    @Autowired
    public void setLivroJdbcPesquisas(LivroJdbcPesquisas livroJdbcPesquisas) {
        this.livroJdbcPesquisas = livroJdbcPesquisas;
    }

    public List<LivroQuantidadePorCategoriaDTO> pesquisarLivroQuantidadePorCategoriaDTORecomendacaoPorUsuario(Long usuarioId) {
        return livroJdbcPesquisas.pesquisarLivroQuantidadePorCategoriaDTORecomendacaoPorUsuario(usuarioId);
    }

    public List<LivroEntity> pesquisarLivrosDisponiveis() {
        return livroJdbcPesquisas.pesquisarLivrosDisponiveis();
    }

}
