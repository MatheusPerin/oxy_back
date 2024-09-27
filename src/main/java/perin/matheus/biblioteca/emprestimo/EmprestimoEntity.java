package perin.matheus.biblioteca.emprestimo;

import jakarta.persistence.*;
import lombok.*;
import perin.matheus.biblioteca.base.listener.CrudListener;
import perin.matheus.biblioteca.emprestimo.enums.EmprestimoSituacao;
import perin.matheus.biblioteca.emprestimo.validacoes.EmprestimoValidacaoCamposObrigatorios;
import perin.matheus.biblioteca.emprestimo.validacoes.EmprestimoValidacaoDataDevolucao;
import perin.matheus.biblioteca.emprestimo.validacoes.EmprestimoValidacaoDataEmprestimo;
import perin.matheus.biblioteca.emprestimo.validacoes.EmprestimoValidacaoLivroDisponivel;
import perin.matheus.biblioteca.livro.LivroEntity;
import perin.matheus.biblioteca.livro.enums.LivroCategoria;
import perin.matheus.biblioteca.livro.validacoes.LivroValidacaoCamposObrigatorios;
import perin.matheus.biblioteca.usuario.UsuarioEntity;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "EMPRESTIMO")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@CrudListener(
    validacoesBeans = {
        EmprestimoValidacaoCamposObrigatorios.class,
        EmprestimoValidacaoDataEmprestimo.class,
        EmprestimoValidacaoDataDevolucao.class,
        EmprestimoValidacaoLivroDisponivel.class
    }
)
public class EmprestimoEntity {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_EMPRESTIMO", sequenceName = "SEQ_EMPRESTIMO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_EMPRESTIMO", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
    private UsuarioEntity usuario;

    @Column(name = "DATA_EMPRESTIMO")
    private LocalDate dataEmprestimo;

    @Column(name = "DATA_DEVOLUCAO")
    private LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO")
    private EmprestimoSituacao situacao;

    @ManyToMany
    @JoinTable(
        name = "EMPRESTIMOLIVRO",
        joinColumns = @JoinColumn(name = "EMPRESTIMO_ID", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "LIVRO_ID", referencedColumnName = "ID")
    )
    private Set<LivroEntity> livros;

}
