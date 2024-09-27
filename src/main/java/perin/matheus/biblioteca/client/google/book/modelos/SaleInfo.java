package perin.matheus.biblioteca.client.google.book.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleInfo {

    private String country;
    private String saleability;
    private Boolean isEbook;
    private Price listPrice;
    private Price retailPrice;
    private String buyLink;
    private List<Offer> offers;

}
