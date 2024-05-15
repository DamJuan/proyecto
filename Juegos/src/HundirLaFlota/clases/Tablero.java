package HundirLaFlota.clases;

import HundirLaFlota.clases.*;

public class Tablero {

    private static Character[][] tablero = new Character[10][10];

    //TODO mostrar el tablero principal vacio
    //Todo cada jugador tiene su propio tablero
    //TODO ir mostrando los barcos en el tablero

    public static void mostrarTablero() {
        System.out.print("  ");
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < tablero.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == null) {
                    tablero[i][j] = '~';
                }
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean esPosicionValida(int fila, int columna) {
        if (fila < 0 || fila >= tablero.length || columna < 0 || columna >= tablero[0].length) {
            return false;
        }

        if (tablero[fila][columna] != '~') {
            return false;
        }

        if (barco.getPosicion().equalsIgnoreCase("horizontal")) {
            if (columna + barco.getTamano() > tablero[0].length) {
                return false;
            }
        } else if (barco.getPosicion().equalsIgnoreCase("vertical")) {
            if (fila + barco.getTamano() > tablero.length) {
                return false;
            }
        }

        return true;
    }

    public static void colocarBarco(int fila, int columna, barco Barco) {
        if (!esPosicionValida(fila, columna, barco)) {
            System.out.println("No se puede colocar el barco en la posición dada.");
            return;
        }

        for (int i = 0; i < Barco.getTamano(); i++) {
            if (Barco.getPosicion().equalsIgnoreCase("horizontal")) {
                tablero[fila][columna + i] = Barco.getNombre().charAt(0);
            } else if (Barco.getPosicion().equalsIgnoreCase("vertical")) {
                tablero[fila + i][columna] = Barco.getNombre().charAt(0);
            }
        }
    }

}

//TODO implementar la colocacion de los barcos
    //TODO MOSTRAR LAS COORDENADAS DEL TABLERO
    //TODO MOSTRAR LOS BARCOS DEL PROPIO JUGADOR EN EL TABLERO
    //TODO CUANDO ES TURNO DE INTENTAR HUNDIR MOSTRAR DONDE SE A LANZADO PROYECTIL Y MOSTRAR DONDE A IMPACTADO
    //TODO GUARDAR TODAS LAS TIRADAS Y MOSTRARLAS EN EL TABLERO




}
