package perin.matheus.biblioteca.livro.jdbc.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class LivroDisponivelDTO {

    private Long id;
    private String titulo;
    private Boolean disponivel;

    @Override
    public String toString() {
        return id.toString().concat(" - ").concat(titulo);
    }
}
