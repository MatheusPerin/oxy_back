package perin.matheus.biblioteca.livro.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import perin.matheus.biblioteca.livro.LivroEntity;
import perin.matheus.biblioteca.livro.enums.LivroCategoria;
import perin.matheus.biblioteca.livro.jdbc.models.LivroDisponivelDTO;
import perin.matheus.biblioteca.livro.jdbc.models.LivroQuantidadePorCategoriaDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório para realizar pesquisas relacionadas a livros no banco de dados
 * utilizando JDBC.
 */
@Repository
public class LivroJdbcPesquisas {

    private JdbcTemplate template;

    /**
     * Injeta a instância de JdbcTemplate necessária para realizar operações
     * de consulta ao banco de dados.
     *
     * @param template Instância de JdbcTemplate a ser injetada.
     */
    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Pesquisa livros disponíveis com base em uma lista de IDs.
     * Se a lista de IDs estiver vazia, um ID fictício (-1) é adicionado para
     * garantir que a consulta não falhe.
     *
     * @param ids Lista de IDs dos livros a serem pesquisados.
     * @return Lista de objetos LivroDisponivelDTO representando livros
     *         disponíveis, junto com seus títulos e status de disponibilidade.
     */
    public List<LivroDisponivelDTO> pesquisarLivrosDisponiveisDTOPorIds(List<Long> ids) {
        if (ids.isEmpty()) ids.add(-1L);

        final String IN = ids.stream().map(Object::toString).collect(Collectors.joining(", "));

        final String SQL =
            "select l.id,\n" +
            "       l.titulo,\n" +
            "       count(l.id) = 0 as disponivel\n" +
            "  from emprestimolivro el\n" +
            " inner join emprestimo e \n" +
            "    on e.id = el.emprestimo_id\n" +
            " inner join livro l \n" +
            "    on l.id = el.livro_id \n" +
            " where e.situacao = 'EMPRESTADO'\n" +
            "   and el.livro_id in ( " + IN + ")\n" +
            " group by l.id, l.titulo ";

        return template.query(
            SQL,
            (rs, rowNum) ->
                LivroDisponivelDTO.builder()
                    .id(rs.getLong("id"))
                    .titulo(rs.getString("titulo"))
                    .disponivel(rs.getBoolean("disponivel"))
                .build()
        );
    }

    /**
     * Pesquisa a quantidade de livros recomendados por categoria para um usuário
     * específico com base em seus empréstimos anteriores.
     *
     * @param usuarioId ID do usuário cujas recomendações devem ser buscadas.
     * @return Lista de objetos LivroQuantidadePorCategoriaDTO contendo
     *         informações sobre livros recomendados e suas quantidades
     *         por categoria.
     */
    public List<LivroQuantidadePorCategoriaDTO> pesquisarLivroQuantidadePorCategoriaDTORecomendacaoPorUsuario(Long usuarioId) {
        final String SQL =
            "with\n" +
            "\n" +
            "cte_quantidade_categoria as (\n" +
            "   select l.categoria,\n" +
            "          count(l.categoria) quantidade\n" +
            "     from livro l\n" +
            "    inner join emprestimolivro el \n" +
            "       on el.livro_id = l.id\n" +
            "    inner join emprestimo e \n" +
            "       on e.id = el.emprestimo_id \n" +
            "    where e.usuario_id = ?\n" +
            "    group by l.categoria\n" +
            ")\n" +
            "\n" +
            " select l.*,\n" +
            "        cqc.quantidade \n" +
            "   from cte_quantidade_categoria cqc\n" +
            "  inner join livro l\n" +
            "     on l.categoria = cqc.categoria\n" +
            "   left join emprestimolivro el\n" +
            "     on el.livro_id = l.id\n" +
            "  where el.livro_id is null\n" +
            "  order by cqc.quantidade desc";

        return template.query(
            SQL,
            (rs, rowNum) ->
                LivroQuantidadePorCategoriaDTO.builder()
                    .id(rs.getLong("id"))
                    .titulo(rs.getString("titulo"))
                    .autor(rs.getString("autor"))
                    .isbn(rs.getString("isbn"))
                    .categoria(LivroCategoria.valueOf(rs.getString("categoria")))
                    .dataPublicacao(rs.getDate("data_publicacao").toLocalDate())
                    .quantidade(rs.getInt("quantidade"))
                .build(),
            usuarioId
        );
    }

    /**
     * Pesquisa todos os livros que estão disponíveis.
     *
     * @return Lista de objetos LivroEntity representando livros que não
     *         estão emprestados.
     */
    public List<LivroEntity> pesquisarLivrosDisponiveis() {
        final String SQL =
            "select l.*\n" +
            "  from livro l\n" +
            "  left join emprestimolivro el\n" +
            "    on el.livro_id = l.id \n" +
            "  left join emprestimo e \n" +
            "    on e.id = el.emprestimo_id\n" +
            "   and e.situacao = 'EMPRESTADO'\n" +
            " where e.id is null";

        return template.query(
            SQL,
            (rs, rowNum) ->
                LivroEntity.builder()
                    .id(rs.getLong("id"))
                    .titulo(rs.getString("titulo"))
                    .autor(rs.getString("autor"))
                    .isbn(rs.getString("isbn"))
                    .categoria(LivroCategoria.valueOf(rs.getString("categoria")))
                    .dataPublicacao(rs.getDate("data_publicacao").toLocalDate())
                .build()
        );
    }
}
