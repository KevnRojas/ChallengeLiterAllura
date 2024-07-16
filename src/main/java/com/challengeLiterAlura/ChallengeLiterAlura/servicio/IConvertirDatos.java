package com.challengeLiterAlura.ChallengeLiterAlura.servicio;

public interface IConvertirDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
