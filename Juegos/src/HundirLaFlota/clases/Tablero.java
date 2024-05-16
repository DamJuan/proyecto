package HundirLaFlota.clases;

import java.util.Scanner;

public class Tablero {

    private static String[][] tablero = new String[10][10];

    static Scanner sc = new Scanner(System.in);

    //TODO mostrar el tablero principal vacio
    //Todo cada jugador tiene su propio tablero
    //TODO ir mostrando los barcos en el tablero

    public static void mostrarTablero() {
        System.out.print("   ");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();

        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == null) {
                    tablero[i][j] = " ~ ";
                }
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void colocarBarco() {
        System.out.println("Introduce la fila donde quieres colocar el barco: ");
        int fila = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce la columna donde quieres colocar el barco: ");
        int columna = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el tamaño del barco: ");
        int size = Integer.parseInt(sc.nextLine());

        System.out.println(analizarPos(fila, columna, size));
    }


    public static String analizarPos(int fila, int columna, int size) {

        boolean vertArriba;
        boolean vertAbajo;
        boolean horDerecha;
        boolean horIzquierda;

        vertArriba = analizarVertArriba(fila, columna, size);
        vertAbajo = analizarVertAbajo(fila, columna, size);
        horDerecha = analizarHorDerecha(fila, columna, size);
        horIzquierda = analizarHorIzquierda(fila, columna, size);

        String opciones = "Estas son tus opciones: ";

        int opcion = 1;

        if (vertArriba) {
            opciones += "\n" + opcion + ". Vertical Arriba";
            opcion++;
        }

        if (vertAbajo) {
            opciones += "\n" + opcion + ". Vertical Abajo";
            opcion++;
        }

        if (horDerecha) {
            opciones += "\n" + opcion + ". Horizontal Derecha";
            opcion++;
        }

        if (horIzquierda) {
            opciones += "\n" + opcion + ". Horizontal Izquierda";
            opcion++;
        }

        opciones += "\n" + opcion + ". Cambiar Posicion";

        return opciones;
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

