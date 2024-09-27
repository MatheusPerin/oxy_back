package perin.matheus.biblioteca.emprestimo.validacoes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import perin.matheus.biblioteca.base.listener.validacao.Validacao;
import perin.matheus.biblioteca.base.listener.validacao.ValidacaoException;
import perin.matheus.biblioteca.emprestimo.EmprestimoEntity;
import perin.matheus.biblioteca.emprestimo.enums.EmprestimoSituacao;
import perin.matheus.biblioteca.livro.LivroEntity;
import perin.matheus.biblioteca.livro.jdbc.LivroJdbcPesquisas;
import perin.matheus.biblioteca.livro.jdbc.models.LivroDisponivelDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EmprestimoValidacaoLivroDisponivel implements Validacao<EmprestimoEntity> {

    private final LivroJdbcPesquisas livroJdbcPesquisas;

    @Override
    public void validar(EmprestimoEntity entity) throws ValidacaoException {
        if (EmprestimoSituacao.DEVOLVIDO.equals(entity.getSituacao())) return;

        String livros = converterPesquisadosParString(
            pesquisarLivrosDisponiveis(
                obterLivrosId(entity)
            )
        );

        if (!livros.isEmpty())
            throw new ValidacaoException("Os seguintes livros não estão disponiveis: \n" + livros);
    }

    private String converterPesquisadosParString(List<LivroDisponivelDTO> pesquisados) {
        return pesquisados
            .stream()
            .filter(livro -> !livro.getDisponivel())
            .map(livro -> " * ".concat(livro.toString()))
            .collect(Collectors.joining("\n"));
    }

    private List<LivroDisponivelDTO> pesquisarLivrosDisponiveis(List<Long> ids) {
        return livroJdbcPesquisas.pesquisarLivrosDisponiveisDTOPorIds(ids);
    }

    private List<Long> obterLivrosId(EmprestimoEntity entity) {
        return entity
            .getLivros()
            .stream()
            .map(LivroEntity::getId)
            .filter(Objects::nonNull)
            .toList();
    }

}
