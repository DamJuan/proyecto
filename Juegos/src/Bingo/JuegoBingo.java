package Bingo;

import java.util.Scanner;
import java.io.*;

public class JuegoBingo {

    public static void bingo(int numJugadores) {

        Scanner sc = new Scanner(System.in);

        Jugador[] jugadores = new Jugador[numJugadores];

        for (int i = 0; i < numJugadores; i++) {
            System.out.println("Introduzca el nombre del jugador " + (i + 1) + ":");
            String nombre = sc.nextLine();
            System.out.println("Introduzca la ciudad del jugador " + (i + 1) + ":");
            String ciudad = sc.nextLine();
            System.out.println("Introduzca la edad del jugador " + (i + 1) + ":");
            int edad = sc.nextInt();
            sc.nextLine();
            jugadores[i] = new Jugador(nombre, ciudad, edad);

            if (edad < 18) {
                System.out.println("El jugador " + nombre + " es menor de edad y no puede jugar al Bingo.");
                break;
            }

        }

        System.out.println("Los cartones se han generado correctamente.");

        for (int i = 0; i < numJugadores; i++) {
            System.out.println("Cartón del jugador " + (i + 1) + ":");
            jugadores[i].mostrarCarton();
            System.out.println();
        }

        System.out.println("¿Estais listos para empezar el juego? (s/n)");
        String respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Empezamos el juego.");
            jugarBingo(jugadores);
        } else {
            System.out.println("Saliendo del juego.");
            sc.close();
        }
    }

    public static void jugarBingo(Jugador[] jugadores) {
        int numGanadores = 0;
        int numBolas = 0;
        boolean bingo = false;
        Scanner sc = new Scanner(System.in);
        while (!bingo) {
            numBolas++;
            System.out.println("Bola número " + numBolas + ":");
            int bola = (int) (Math.random() * 90) + 1;
            System.out.println("Bola: " + bola);
            for (int i = 0; i < jugadores.length; i++) {
                if (jugadores[i].contieneNumero(bola)) {
                    System.out.println("El jugador " + jugadores[i].getNombre() + " tiene el número " + bola + " en su cartón.");
                    if (jugadores[i].cartonCompletado()) {
                        System.out.println("El jugador " + jugadores[i].getNombre() + " ha completado el cartón.");
                        numGanadores++;
                        if (numGanadores == jugadores.length) {
                            bingo = true;
                            break;
                        }
                    }
                }
            }
            System.out.println("¿Desea guardar la partida? (s/n)");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                guardarPartida(jugadores, numBolas);
            }
        }
        System.out.println("¡BINGO!");
        sc.close();
    }

    public static void guardarPartida(Jugador[] jugadores, int numBolas) {
        try {
            FileOutputStream fileOut = new FileOutputStream("partida.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(jugadores);
            out.writeInt(numBolas);
            out.close();
            fileOut.close();
            System.out.println("Partida guardada en partida.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void cargarPartida() {
        Jugador[] jugadores = null;
        int numBolas = 0;
        try {
            FileInputStream fileIn = new FileInputStream("partida.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            jugadores = (Jugador[]) in.readObject();
            numBolas = in.readInt();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Clase Jugador no encontrada");
            c.printStackTrace();
            return;
        }
        System.out.println("Partida cargada.");
        jugarBingo(jugadores);
    }
}