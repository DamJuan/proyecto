import java.util.Scanner;

import Bingo.*;
import MultiplicationGame.*;
import Ahorcado.*;

public class Main {

    //TODO MEJORAR SISTEMA DE JUEGOS
    //TODO IMPLEMENTAR MEJORA BINGO
    //TODO AÑADIR EXCEPCIONES


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {

            System.out.println("Bienvenido a la plataforma de juegos de Java.  \n " +
                    "Por favor, introduzca el numero de jugadores que jugaran al juego, \n" +
                    " al ser juegos multijugadores tiene que ser como minimo 2 y el maximo es 3:");

            int numJugadores = sc.nextInt();

            if (numJugadores < 2 || numJugadores > 3) {
                System.out.println("Numero de jugadores no valido. Saliendo del programa.");
                sc.close();
                return;
            }

            System.out.println("Seleccione el juego:");
            System.out.println("1. Juego de Multiplicación");
            System.out.println("2. Juego del Ahorcado");
            System.out.println("3. Bingo");
            System.out.println("4. Hundir la flota (no implementado)");
            System.out.print("introduzca su elección: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    MultiplicationGame.multiplicationGame(numJugadores);
                    break;
                case 2:
                    Ahorcado juegoAhorcado = new Ahorcado();
                    juegoAhorcado.Jugar();
                    break;
                case 3:
                    JuegoBingo.bingo(numJugadores);
                    break;
                case 4:
                    System.out.println("Juego en desarollo lamentamos las molestias.");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
