package perin.matheus.biblioteca.client.google.book.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class BookVolumes {

    private String kind;
    private Integer totalItems;
    private List<BookVolume> items;

}
