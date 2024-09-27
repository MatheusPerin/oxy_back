package perin.matheus.biblioteca.usuario;

import jakarta.persistence.*;
import lombok.*;
import perin.matheus.biblioteca.base.listener.CrudListener;
import perin.matheus.biblioteca.usuario.validacoes.UsuarioValidacaoCamposObrigatorios;
import perin.matheus.biblioteca.usuario.validacoes.UsuarioValidacaoEmail;
import perin.matheus.biblioteca.usuario.validacoes.UsuarioValidacaoTelefone;

import java.time.LocalDate;

@Entity
@Table(name = "USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@CrudListener(
    validacoesBeans = {
        UsuarioValidacaoCamposObrigatorios.class,
        UsuarioValidacaoEmail.class,
        UsuarioValidacaoTelefone.class
    }
)
public class UsuarioEntity {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_USUARIO", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DATA_CADASTRO")
    private LocalDate dataCadastro;

    @Column(name = "TELEFONE")
    private String telefone;

}
