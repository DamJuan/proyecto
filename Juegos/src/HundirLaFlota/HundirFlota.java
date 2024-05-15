package HundirLaFlota;

import HundirLaFlota.clases.Tablero;
import HundirLaFlota.clases.barco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HundirFlota {

    private static ArrayList<barco> barcos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido a Hundir la Flota");
        System.out.println("Conoces las reglas del juego? S/N:");
        String respuesta = sc.nextLine();

        if (respuesta.equalsIgnoreCase("N")) {
            mostrarInstrucciones();
        } else if (respuesta.equalsIgnoreCase("S")) {
            System.out.println("Vamos a jugar!");
        }

        System.out.println("Contra quién quieres jugar? Jugador o Máquina?");
        String PVPoPVE = sc.nextLine();

        if (PVPoPVE.equalsIgnoreCase("Jugador")) {
            namePVP();
        } else if (PVPoPVE.equalsIgnoreCase("Máquina")) {
            namePVE();
        }

    }

    public static void mostrarInstrucciones() {
        final File reglas = new File("src\\HundirLaFlota\\Reglas.");
        System.out.println("A continuación se mostrarán las reglas del juego:");

        try {
            BufferedReader br = new BufferedReader(new FileReader(reglas));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

    }

    public static void namePVP() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del jugador 1:");
        String nombreJugador1 = sc.nextLine();
        System.out.println("Introduce el nombre del jugador 2:");
        String nombreJugador2 = sc.nextLine();

        System.out.println("Bienvenidos " + nombreJugador1 + " y " + nombreJugador2 + "!");
        empezarJuego();

    }


    public static void namePVE() {
        System.out.println("Lo siento, esta opción no está disponible todavía.");
        //Scanner sc = new Scanner(System.in);
        //System.out.println("Introduce tu nombre:");
        //String nombreJugador = sc.nextLine();
    }

    public static void empezarJuego() {

        Tablero.mostrarTablero();

    }

    publ
}
