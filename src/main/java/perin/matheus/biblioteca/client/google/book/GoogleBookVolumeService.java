package perin.matheus.biblioteca.client.google.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import perin.matheus.biblioteca.client.google.book.modelos.LivroGoogle;
import perin.matheus.biblioteca.client.google.book.modelos.LivroGoogleTranslate;

import java.util.List;

@Service
public class GoogleBookVolumeService {

    private ClientGoogleBookVolume client;

    @Autowired
    public void setClient(ClientGoogleBookVolume client) {
        this.client = client;
    }

    public LivroGoogle pesquisarPorId(String id) {
        return LivroGoogleTranslate.apartirDe(client.get(id));
    }

    public List<LivroGoogle> pesquisarPorTitulo(String titulo) {
        return LivroGoogleTranslate.apartirDe(client.porQuery("intitle:".concat(titulo)));
    }

}
