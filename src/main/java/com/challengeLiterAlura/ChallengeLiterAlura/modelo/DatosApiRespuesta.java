package com.challengeLiterAlura.ChallengeLiterAlura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosApiRespuesta(
        @JsonAlias("results") List<DatosLibro> resultado
) {
}
