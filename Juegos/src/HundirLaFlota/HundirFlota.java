package HundirLaFlota;

import HundirLaFlota.clases.Jugador;
import HundirLaFlota.clases.Tablero;
import HundirLaFlota.clases.TiposBarco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class HundirFlota {

    static final File REGLAS = new File("src\\HundirLaFlota\\Reglas.");
    static Scanner sc = new Scanner(System.in);
    static Set<TiposBarco> setBarcosJugador1 = new HashSet<>();
    static Set<TiposBarco> setBarcosJugador2 = new HashSet<>();
    static List<Jugador> listaJugadores = new LinkedList<>();

    public static void main(String[] args) {

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
        System.out.println("A continuación se mostrarán las reglas del juego:");

        try {
            BufferedReader br = new BufferedReader(new FileReader(REGLAS));
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
        System.out.println("👤 Introduce el nombre del jugador 1 👤: ");
        String nombreJugador1 = sc.nextLine();
        System.out.println("👤 Introduce el nombre del jugador 2 👤:");
        String nombreJugador2 = sc.nextLine();

        Jugador jugador1 = new Jugador(nombreJugador1);
        Jugador jugador2 = new Jugador(nombreJugador2);

        listaJugadores.add(jugador1);
        listaJugadores.add(jugador2);

        System.out.println("Bienvenidos " + listaJugadores.get(0).getNombre() + " y " + listaJugadores.get(1).getNombre() + "! 🎉🎊🎉🎊");
        empezarJuego();

    }


    public static void namePVE() {
        System.out.println("Lo siento, esta opción no está disponible todavía.");
        //Scanner sc = new Scanner(System.in);
        //System.out.println("Introduce tu nombre:");
        //String nombreJugador = sc.nextLine();
    }

    public static void empezarJuego() {

        System.out.println("Vamos a colocar los barcos en el tablero.");

        Tablero.mostrarTablero();

        while (setBarcosJugador1.size() < 5) {
            colocarBarcos();
            Tablero.mostrarTablero();

        }

        while (setBarcosJugador2.size() < 5) {
            colocarBarcos();
            Tablero.mostrarTablero();
        }

        //TODO implementar el juego
        //TODO implementar el turno de los jugadores



    }

    //TODO cuando se golpea un barco el turno del jugador debe seguir
//TODO cuando se golpea agua el turno del jugador debe cambiar

    //TODO MOSTRAR LOS BARCOS QUE QUEDAN POR PONER EN EL TABLERO
    //TODO PRIMER TURNO ES PARA COLOCAR LOS BARCOS EL TURNO ACABA CUANDO SE HAN COLOCADO TODOS LOS BARCOS

    public static void colocarBarcos() {

        int opcionBarco = menuOpcionesJuego();

        TiposBarco barco = null;

        switch (opcionBarco) {
            case 1, 2, 3, 4, 5:
                if (opcionBarco == 1) {
                    barco = TiposBarco.PORTAVIONES;
                } else if (opcionBarco == 2) {
                    barco = TiposBarco.ACORAZADO;
                } else if (opcionBarco == 3) {
                    barco = TiposBarco.SUBMARINO;
                } else if (opcionBarco == 4) {
                    barco = TiposBarco.DESTRUCTOR;
                } else if (opcionBarco == 5) {
                    barco = TiposBarco.FRAGATA;
                }

                if (setBarcosJugador1.contains(barco)) {
                    System.out.println("Ya has colocado este barco.");
                    return;
                }

                System.out.println("Elige la posición de tu barco:" +
                        "\n1. Horizontal" +
                        "\n2. Vertical");
                int posicion = sc.nextInt();
                if (posicion == 1) {
                    System.out.println("Elige la fila donde quieres colocar el barco:");
                    int fila = sc.nextInt();
                    System.out.println("Elige la columna donde quieres colocar el barco:");
                    int columna = sc.nextInt();
                    Tablero.colocarBarco(fila, columna, barcos.get(opcionBarco));
                } else if (posicion == 2) {
                    System.out.println("Elige la fila donde quieres colocar el barco:");
                    int fila = sc.nextInt();
                    System.out.println("Elige la columna donde quieres colocar el barco:");
                    int columna = sc.nextInt();
                    Tablero.colocarBarco(fila, columna, barcos.get(opcionBarco));
                }
                break;

            default:
                System.out.println("Opción no válida.");
                break;

        }
        //TODO al elegir el barco se tiene que decidir si se coloca en horizontal o vertical y en qué posición


    }

    public static int menuOpcionesJuego() {


        System.out.println("Vamos a colocar los barcos en el tablero." +
                "\nElige la posición de tu barco:" +
                "\n1. " + TiposBarco.PORTAVIONES.getNombre() + " (" + TiposBarco.PORTAVIONES.getSize() + " casillas)" +
                "\n2. " + TiposBarco.ACORAZADO.getNombre() + " (" + TiposBarco.ACORAZADO.getSize() + " casillas)" +
                "\n3. " + TiposBarco.SUBMARINO.getNombre() + " (" + TiposBarco.SUBMARINO.getSize() + " casillas)" +
                "\n4. " + TiposBarco.DESTRUCTOR.getNombre() + " (" + TiposBarco.DESTRUCTOR.getSize() + " casillas)" +
                "\n5. " + TiposBarco.FRAGATA.getNombre() + " (" + TiposBarco.FRAGATA.getSize() + " casillas)");
        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;

    }


    private static void guardarPartida() {
        //TODO guardar la partida en un archivo
        System.out.println("Partida guardada con éxito!");
    }

    public static void salir() {
        System.out.println("Gracias por jugar!");
        System.exit(0);
    }

}
