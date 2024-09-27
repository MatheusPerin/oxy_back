package perin.matheus.biblioteca.livro.jdbc.models;

import lombok.*;
import perin.matheus.biblioteca.livro.enums.LivroCategoria;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class LivroQuantidadePorCategoriaDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private LocalDate dataPublicacao;
    private LivroCategoria categoria;
    private Integer quantidade;

}
