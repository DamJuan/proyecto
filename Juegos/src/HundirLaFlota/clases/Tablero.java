package HundirLaFlota.clases;

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

//TODO implementar la colocacion de los barcos
    //TODO MOSTRAR LAS COORDENADAS DEL TABLERO
    //TODO MOSTRAR LOS BARCOS DEL PROPIO JUGADOR EN EL TABLERO
    //TODO CUANDO ES TURNO DE INTENTAR HUNDIR MOSTRAR DONDE SE A LANZADO PROYECTIL Y MOSTRAR DONDE A IMPACTADO
    //TODO GUARDAR TODAS LAS TIRADAS Y MOSTRARLAS EN EL TABLERO




}
