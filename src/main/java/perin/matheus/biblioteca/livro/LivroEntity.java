package perin.matheus.biblioteca.livro;

import jakarta.persistence.*;
import lombok.*;
import perin.matheus.biblioteca.base.listener.CrudListener;
import perin.matheus.biblioteca.livro.enums.LivroCategoria;
import perin.matheus.biblioteca.livro.validacoes.LivroValidacaoCamposObrigatorios;
import perin.matheus.biblioteca.usuario.validacoes.UsuarioValidacaoCamposObrigatorios;
import perin.matheus.biblioteca.usuario.validacoes.UsuarioValidacaoEmail;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "LIVRO")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@CrudListener(
    validacoesBeans = LivroValidacaoCamposObrigatorios.class
)
public class LivroEntity {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_LIVRO", sequenceName = "SEQ_LIVRO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_LIVRO", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "AUTOR")
    private String autor;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "DATA_PUBLICACAO")
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORIA")
    private LivroCategoria categoria;

    @Override
    public boolean equals(Object obj) {
        if (Objects.isNull(this.getId())) return super.equals(obj);

        if (!(obj instanceof LivroEntity entity)) return false;

        return Objects.equals(this.getId(), entity.getId());
    }

    @Override
    public int hashCode() {
        if (Objects.isNull(this.getId())) return super.hashCode();

        return Objects.hashCode(this.getId());
    }
}
