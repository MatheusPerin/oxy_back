package perin.matheus.biblioteca.client.google.book;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import perin.matheus.biblioteca.client.google.book.modelos.BookVolume;
import perin.matheus.biblioteca.client.google.book.modelos.BookVolumes;

@FeignClient(name = "ClientGoogleBookVolume", url = "https://www.googleapis.com/books/v1/volumes")
public interface ClientGoogleBookVolume {

    @GetMapping("/{id}")
    BookVolume get(@PathVariable("id") String id);

    @GetMapping
    BookVolumes porQuery(@RequestParam("q") String query);

}
