package HundirLaFlota.clases;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Tablero {

    private static String[][] tablero = new String[10][10];

    static Scanner sc = new Scanner(System.in);

    public Tablero() {
        rellenarTablero();
    }

    public static void rellenarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            Arrays.fill(tablero[i], " ~ ");
        }
    }

    public static void mostrarTablero() {
        System.out.print("   ");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();

        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static Map<Integer, String> colocarBarco(int fila, int columna,  int size) {

        Map<Integer, String> mapOpciones;
        return mapOpciones = analizarPos(fila, columna, size);

    }


    public static Map<Integer, String> analizarPos(int fila, int columna, int size) {

        Map<Integer, String> mapOpciones = new LinkedHashMap<>();

        int opcion = 1;

        if (analizarVertArriba(fila, columna, size)) {
            mapOpciones.put(opcion, "Vertical Arriba");
            opcion++;
        }

        if (analizarVertAbajo(fila, columna, size)) {
            mapOpciones.put(opcion, "Vertical Abajo");
            opcion++;
        }

        if (analizarHorDerecha(fila, columna, size)) {
            mapOpciones.put(opcion, "Horizontal Derecha");
            opcion++;
        }

        if (analizarHorIzquierda(fila, columna, size)) {
            mapOpciones.put(opcion, "Horizontal Izquierda");
            opcion++;
        }

        mapOpciones.put(opcion, "Cambiar Posición");

        return mapOpciones;

    }

    public static boolean analizarVertArriba(int fila, int columna, int size) {

        boolean sePuede = Boolean.FALSE;

        for (int i = fila; i <= (fila - size); i--) {
            if (tablero[i][columna].equalsIgnoreCase(" S ")) {
                sePuede = Boolean.FALSE;
            } else {
                sePuede = Boolean.TRUE;
            }
        }

        return sePuede;
    }

    public static boolean analizarVertAbajo(int fila, int columna, int size) {

        boolean sePuede = Boolean.FALSE;

        for (int i = fila; i <= (fila + size); i++) {
            if (tablero[i][columna].equalsIgnoreCase(" S ")) {
                sePuede = Boolean.FALSE;
            } else {
                sePuede = Boolean.TRUE;
            }
        }

        return sePuede;

    }

    public static boolean analizarHorDerecha(int fila, int columna, int size) {

        boolean sePuede = Boolean.FALSE;

        for (int i = columna; i <= (columna + size); i++) {
            if (tablero[fila][i].equalsIgnoreCase(" S ")) {
                sePuede = Boolean.FALSE;
            } else {
                sePuede = Boolean.TRUE;
            }
        }

        return sePuede;

    }

    public static boolean analizarHorIzquierda(int fila, int columna, int size) {

        boolean sePuede = Boolean.FALSE;

        for (int i = columna; i <= (columna - size); i--) {
            if (tablero[fila][i].equalsIgnoreCase(" S ")) {
                sePuede = Boolean.FALSE;
            } else {
                sePuede = Boolean.TRUE;
            }
        }

        return sePuede;

    }

}


//TODO implementar la colocacion de los barcos
//TODO MOSTRAR LAS COORDENADAS DEL TABLERO
//TODO MOSTRAR LOS BARCOS DEL PROPIO JUGADOR EN EL TABLERO
//TODO CUANDO ES TURNO DE INTENTAR HUNDIR MOSTRAR DONDE SE A LANZADO PROYECTIL Y MOSTRAR DONDE A IMPACTADO
//TODO GUARDAR TODAS LAS TIRADAS Y MOSTRARLAS EN EL TABLERO

