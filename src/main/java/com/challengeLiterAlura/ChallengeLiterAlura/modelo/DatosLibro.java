package com.challengeLiterAlura.ChallengeLiterAlura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DatosLibro(
        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages")List<String> idioma,
        @JsonAlias("copyright")Boolean copyright,
        @JsonAlias("download_count")Integer cantidadDescargas
) {
}
