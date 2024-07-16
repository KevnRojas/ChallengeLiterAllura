package com.challengeLiterAlura.ChallengeLiterAlura.servicio;

import com.challengeLiterAlura.ChallengeLiterAlura.modelo.Autor;
import com.challengeLiterAlura.ChallengeLiterAlura.modelo.DatosApiRespuesta;
import com.challengeLiterAlura.ChallengeLiterAlura.modelo.DatosLibro;
import com.challengeLiterAlura.ChallengeLiterAlura.modelo.Libro;
import com.challengeLiterAlura.ChallengeLiterAlura.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioLibro {
    @Autowired
    private LibroRepositorio repoLibro;


    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvertirDatos conversor = new ConvertirDatos();
    private String URL_BASE = "https://gutendex.com";

    public ServicioLibro(LibroRepositorio repoLibro) {
        this.repoLibro = repoLibro;
    }

    public void guardarLibro(DatosLibro d) {
        Libro nuevoLibro = new Libro(d);
        repoLibro.save(nuevoLibro);
        System.out.println(nuevoLibro.toString());
    }

    public void buscarLibroPorTitulo(String titulo) {
        Optional<Libro> libroBuscado = repoLibro.findByTituloContainsIgnoreCase(titulo);

        if (libroBuscado.isPresent()) {
            System.out.println("El libro es: " + libroBuscado.get());
        } else {
            String json = consumoApi.obtenerDatos(URL_BASE + "/books/?search=" + titulo.replace(" ", "%20"));
            DatosApiRespuesta libroBuscadoWeb = conversor.obtenerDatos(json, DatosApiRespuesta.class);
            List<DatosLibro> libroWeb = libroBuscadoWeb.resultado();


            guardarLibro(libroWeb.get(0));

        }
    }

    public void listaLibros() {
        List<Libro> libros = repoLibro.findAll();
        libros.stream().forEach(System.out::println);
    }

    public void listaAutores() {
        List<Autor> autores = repoLibro.encontrarAutores();
        autores.forEach(a -> System.out.println(a.toString()));
    }

    public void listaAutoresVivos(int anio) {
        List<Autor> autoresVivos = repoLibro.encontrarAutoresVivos(anio);
        autoresVivos.stream().forEach(System.out::println);
    }

    public void ListaLibrosPorIdioma(String idioma) {
        List<Libro> librosIdioma = repoLibro.encontrarLibroPorIdioma(idioma);
        librosIdioma.stream().forEach(System.out::println);
    }

    public void obtenerDatosEstadisticos() {
        List<Libro> librosRepo = repoLibro.findAll();
        ArrayList<Libro> libros = new ArrayList<Libro>(librosRepo);
        DoubleSummaryStatistics data = libros.stream().collect(Collectors.summarizingDouble(Libro::getCantidadDescargas));
        System.out.println(
                "----- DATOS DE LOS LIBROS -----"+
                        "\nMedia de descargas: "+ String.format("%1.2f", data.getAverage())+
                        "\nMayor descargada: "+data.getMax()+
                        "\nMenor descargada "+data.getMin()+
                        "\nCantidad de libros almacenados: "+data.getCount()+
                        "\n------------------------------");
    }

    public void obtenerTopLibros() {
        List<Libro> topLibros = repoLibro.obtenerTopLibros();
        topLibros.forEach(System.out::println);
    }

    public void obtenerAutor(String nombre) {
        Autor autor = repoLibro.encontrarAutor(nombre);
        System.out.println(autor.toString());
    }

    public void obtenerAutoresVivosRango(int anioDesde, int anioHasta) {
        List<Autor> autores = repoLibro.obtenerAutoresVivosRango(anioDesde, anioHasta);
        autores.forEach(System.out::println);
    }

}
