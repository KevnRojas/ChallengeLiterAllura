package com.challengeLiterAlura.ChallengeLiterAlura.principal;

import com.challengeLiterAlura.ChallengeLiterAlura.servicio.ServicioLibro;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);

    private ServicioLibro servicioLibro;

    public Principal(ServicioLibro service) {
        this.servicioLibro = service;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Búsqueda de libro por título 
                    2 - Lista de todos los libros
                    3 - Lista de autores
                    4 - Lista de autores vivos en determinado año
                    5 - Listar libros por idioma
                    6 - Búsqueda por autor
                    7 - Top 10 libros más descargados
                    8 - Estadísticas generales
                    9 - Listar autores que nacieron y murieron en un determinado rango de años
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listaLibros();
                    break;
                case 3:
                    listaAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listraLibroPorIdioma();
                    break;
                case 6:
                    mostrarDatosEstadisticos();
                    break;
                case 7:
                    listaTop10Libros();
                    break;
                case 8:
                    buscarAutorPorNombre();
                    break;
                case 9:
                    listarAutoresVivosDentroDeRango();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }


    private void buscarLibro() {

        System.out.println("Escribe el nombre del libro que deseas buscar:");
        var titulo = teclado.nextLine();

        servicioLibro.buscarLibroPorTitulo(titulo);

    }

    private void listaLibros() {
        servicioLibro.listaLibros();
    }

    private void listaAutores() {
        servicioLibro.listaAutores();
    }

    private void listarAutoresVivos() {
        System.out.println("Ingresa el año desde el que deseas ver vivo a los autores");
        int anio = Integer.parseInt(teclado.nextLine());
        servicioLibro.listaAutoresVivos(anio);
    }

    private void listraLibroPorIdioma() {
        System.out.println("Ingresa el acrónimo del idioma de los libros que deseas visualizar");
        System.out.println("Por ejemplo: \nes - español\nen - inglés\nfr - francés\npt - portugués");
        String idioma = teclado.nextLine();
        servicioLibro.ListaLibrosPorIdioma(idioma);
    }

    private void mostrarDatosEstadisticos() {
        servicioLibro.obtenerDatosEstadisticos();
    }

    private void listaTop10Libros() {
        servicioLibro.obtenerTopLibros();
    }

    private void buscarAutorPorNombre() {
        System.out.println("Ingresa el nombre del autor que deseas buscar");
        String nombre = teclado.nextLine();
        servicioLibro.obtenerAutor(nombre);
    }

    private void listarAutoresVivosDentroDeRango() {
        System.out.println("Ingresa el año desde el que deseas buscar los autores vivos");
        int anioDesde = Integer.parseInt(teclado.nextLine());
        System.out.println("Ingresa el año hasta el que deseas buscar los autores vivos ");
        int anioHasta = Integer.parseInt(teclado.nextLine());
        servicioLibro.obtenerAutoresVivosRango(anioDesde, anioHasta);
    }
}
