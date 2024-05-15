package Ahorcado;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ahorcado {

    private static final int MAX_INTENTOS = 12;
    private static final int PISTA_INTENTOS = 6;

    private Incognita[] incognitas;
    private Incognita incognitaAdivinar;
    private String palabraEnJuego;
    private int intentosRestantes;

    public Ahorcado() {
        inicializarIncognitas();
        seleccionarIncognita();
        intentosRestantes = MAX_INTENTOS;
        palabraEnJuego = incognitaAdivinar.getTexto().toLowerCase();
    }

    public void Jugar() {
        Scanner sc = new Scanner(System.in);
        char[] palabraDescubierta = new char[palabraEnJuego.length()];
        inicializarPalabraDescubierta(palabraDescubierta);

        while (intentosRestantes > 0) {
            mostrarEstadoJuego(palabraDescubierta);

            char letra = getLetra(sc);
            boolean acierto = actualizarPalabraDescubierta(palabraDescubierta, letra);

            if (!acierto) {
                intentosRestantes--;
            }

            if (intentosRestantes == PISTA_INTENTOS) {
                System.out.println("¡Últimos intentos! La incógnita es de tipo: " + incognitaAdivinar.getTipo());
            }

            if (String.valueOf(palabraDescubierta).equals(palabraEnJuego)) {
                System.out.println("¡Felicidades! Has adivinado la palabra: " + palabraEnJuego);
                return;
            }
        }
        System.out.println("¡Oh no! Te has quedado sin intentos. La palabra era: " + palabraEnJuego);
    }

    private char[] inicializarPalabraDescubierta(char[] palabraDescubierta) {
        for (int i = 0; i < palabraDescubierta.length; i++) {
            palabraDescubierta[i] = '_';
        }
        return palabraDescubierta;
    }

    private void mostrarEstadoJuego(char[] palabraDescubierta) {
        System.out.println("Palabra: " + String.valueOf(palabraDescubierta));
        System.out.println("Intentos restantes: " + intentosRestantes);
    }

    private char getLetra(Scanner sc) {
        System.out.print("Introduce una letra: ");
        return sc.next().toLowerCase().charAt(0);
    }

    private boolean actualizarPalabraDescubierta(char[] palabraDescubierta, char letra) {
        boolean acierto = false;
        for (int i = 0; i < palabraEnJuego.length(); i++) {
            if (palabraEnJuego.charAt(i) == letra) {
                palabraDescubierta[i] = letra;
                acierto = true;
            }
        }
        return acierto;
    }

    private void seleccionarIncognita() {
        Random random = new Random();
        incognitaAdivinar = incognitas[random.nextInt(incognitas.length)];
        palabraEnJuego = incognitaAdivinar.getTexto().toLowerCase();
    }

    private void inicializarIncognitas() {
        File peliculas = new File("src/Ahorcado/peliculas.txt");
        File libros = new File("src/Ahorcado/libros.txt");
        File gruposMusicales = new File("src/Ahorcado/grupos_musicales.txt");

        ArrayList<Incognita> incognitasList = new ArrayList<>();
        incognitasList.addAll(leerIncognitas(peliculas, TipoIncognita.PELICULA));
        incognitasList.addAll(leerIncognitas(libros, TipoIncognita.LIBRO));
        incognitasList.addAll(leerIncognitas(gruposMusicales, TipoIncognita.GRUPO_MUSICAL));

        incognitas = incognitasList.toArray(new Incognita[0]);
    }

    private ArrayList<Incognita> leerIncognitas(File archivo, TipoIncognita tipoIncognita) {
        ArrayList<Incognita> incognitas = new ArrayList<>();
        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                incognitas.add(new Incognita(linea.trim(), tipoIncognita));
            }
        } catch (Exception e) {
            System.out.println("No se encontró el archivo");
        }
        return incognitas;
    }
}