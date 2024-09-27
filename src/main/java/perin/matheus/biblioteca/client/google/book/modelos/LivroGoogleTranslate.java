package perin.matheus.biblioteca.client.google.book.modelos;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class LivroGoogleTranslate {

    public static LivroGoogle apartirDe(BookVolume volume) {
        if (Objects.isNull(volume)) return null;

        return LivroGoogle.builder()
            .id(volume.getId())
            .titulo(volume.getVolumeInfo().getTitle())
            .autor(String.join(" | ", volume.getVolumeInfo().getAuthors()))
            .descricao(volume.getVolumeInfo().getDescription())
            .editora(volume.getVolumeInfo().getPublisher())
            .paginas(volume.getVolumeInfo().getPageCount())
            .dataPublicacao(parseDate(volume))
        .build();
    }

    private static LocalDate parseDate(BookVolume volume) {
        try {
            return LocalDate.parse(volume.getVolumeInfo().getPublishedDate());
        } catch (Exception e) {
            return null;
        }
    }

    public static List<LivroGoogle> apartirDe(BookVolumes volumes) {
        if (Objects.isNull(volumes)) return List.of();

        return volumes
            .getItems()
            .stream()
            .map(LivroGoogleTranslate::apartirDe)
            .filter(Objects::nonNull)
            .toList();
    }

}
