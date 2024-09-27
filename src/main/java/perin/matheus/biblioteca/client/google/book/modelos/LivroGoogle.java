package perin.matheus.biblioteca.client.google.book.modelos;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Builder
public class LivroGoogle {

    private String id;
    private String titulo;
    private String autor;
    private String editora;
    private LocalDate dataPublicacao;
    private String descricao;
    private Integer paginas;

}
