package perin.matheus.biblioteca.client.google.book.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Offer {

    private Integer finskyOfferType;
    private Price listPrice;
    private Price retailPrice;
    private Boolean giftable;

}