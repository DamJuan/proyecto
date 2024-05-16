package HundirLaFlota;

import HundirLaFlota.clases.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/*
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
*/

public class HundirFlota {

    static final File REGLAS = new File("src\\HundirLaFlota\\Reglas");
    static Scanner sc = new Scanner(System.in);
    static List<Jugador> listaJugadores = new LinkedList<>();
    static List<Tablero> listaTableros = new LinkedList<>();
    //private static final Logger LOGGER = LogManager.getLogger();

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
        } else if (PVPoPVE.equalsIgnoreCase("Máquina") || PVPoPVE.equalsIgnoreCase("Maquina")) {
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

        Tablero tableroJugador1 = new Tablero();
        Tablero tableroJugador2 = new Tablero();

        Jugador jugador1 = new Jugador(nombreJugador1, Boolean.FALSE, tableroJugador1);
        Jugador jugador2 = new Jugador(nombreJugador2, Boolean.FALSE, tableroJugador2);

        listaJugadores.add(jugador1);
        listaJugadores.add(jugador2);

        listaTableros.add(tableroJugador1);
        listaTableros.add(tableroJugador2);

        System.out.println("Bienvenidos " + listaJugadores.get(0).getNombre() + " y " + listaJugadores.get(1).getNombre() + "! 🎉🎊🎉🎊");

        empezarJuego();

    }


    public static void namePVE() {
        Scanner sc = new Scanner(System.in);

        System.out.println("👤 Introduce el nombre del jugador 👤: ");
        String nombreJugador = sc.nextLine();

        System.out.println("🤖 Introduce el nombre de la maquina 🤖: ");
        String nombreMaquina = sc.nextLine();

        Tablero tableroJugador = new Tablero();
        Tablero tableroMaquina = new Tablero();

        Jugador jugador = new Jugador(nombreJugador, Boolean.FALSE, tableroJugador);
        Jugador maquina = new Jugador(nombreMaquina, Boolean.TRUE, tableroMaquina);

        listaJugadores.add(jugador);
        listaJugadores.add(maquina);

        listaTableros.add(tableroJugador);
        listaTableros.add(tableroMaquina);

        System.out.println("Bienvenidos " + listaJugadores.get(0).getNombre() + " y " + listaJugadores.get(1).getNombre() + "! 🎉🎊🎉🎊");

        empezarJuego();

    }

    public static void empezarJuego() {

        System.out.println("Vamos a colocar los barcos en el tablero.");

        for (Jugador jugador : listaJugadores) {
            System.out.println("Turno de " + jugador.getNombre());

            while (jugador.getBarcos().size() < 5) {
                colocarBarcos(jugador, jugador.getTablero());
                jugador.getTablero().mostrarTablero();
            }
        }

        Tablero.mostrarTablero();


        boolean juegoEnCurso = true;
        int jugadorActual = 0;
        while (juegoEnCurso) {

            turnoJugador(listaJugadores.get(jugadorActual));

            juegoEnCurso = comprobarEstadoJuego();

            jugadorActual = (jugadorActual + 1) % listaJugadores.size();
        }

        System.out.println("Fin del juego!");



        //TODO implementar el juego
        //TODO implementar el turno de los jugadores



    }

    //TODO cuando se golpea un barco el turno del jugador debe seguir
    //TODO cuando se golpea agua el turno del jugador debe cambiar

    //TODO MOSTRAR LOS BARCOS QUE QUEDAN POR PONER EN EL TABLERO
    //TODO PRIMER TURNO ES PARA COLOCAR LOS BARCOS EL TURNO ACABA CUANDO SE HAN COLOCADO TODOS LOS BARCOS

    public static void colocarBarcos(Jugador jugador, Tablero tablero) {

        int opcionBarco = menuOpcionesJuego();

        TiposBarco barco = null;

        if (opcionBarco == 1) {
            barco = TiposBarco.PORTAVIONES;
        } else if (opcionBarco == 2) {
            barco = TiposBarco.ACORAZADO;
        } else if (opcionBarco == 3) {
            barco = TiposBarco.SUBMARINO;
        } else if (opcionBarco == 4) {
            barco = TiposBarco.DESTRUCTOR;
        } else if (opcionBarco == 5){
            barco = TiposBarco.FRAGATA;
        }

        switch (opcionBarco) {
            case 1, 2, 3, 4, 5:

                if (jugador.getBarcos().contains(barco)) {
                    System.out.println("Ya has colocado este barco.");
                    break;
                }

                int fila, columna;


                if (!jugador.getEsMaquina()) {
                    System.out.println("Introduce la fila donde quieres colocar el barco: ");
                    fila = sc.nextInt();

                    System.out.println("Introduce la columna donde quieres colocar el barco: ");
                    columna = sc.nextInt();

                } else {
                    fila = generarNumeroRandom(0, 9);
                    columna = generarNumeroRandom(0, 9);
                }


                Map<Integer, String> mapOpciones = tablero.colocarBarco(fila, columna, barco.getSize());

                mostrarOpcionesColocarBarco(mapOpciones);

                jugador.addBarco(barco);
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }

    }

    public static void mostrarOpcionesColocarBarco(Map<Integer, String> mapOpciones) {
        System.out.println("Elige la posición de tu barco:");
        for (Map.Entry<Integer, String> entry : mapOpciones.entrySet()) {
            System.out.println("\n" + entry.getKey() + ". " + entry.getValue());
        }
        int opcion = sc.nextInt();
        sc.nextLine();
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
    public static int generarNumeroRandom(int limiteInicial, int limiteFinal) {
        Random random = new Random();
        return random.nextInt(limiteFinal - limiteInicial + 1) + limiteInicial;
    }

    public static void turnoJugador(Jugador jugador) {
        System.out.println("Turno de " + jugador.getNombre());
        System.out.println("Elige una posición para atacar (primero la fila, luego la columna):");
        int fila = sc.nextInt();
        int columna = sc.nextInt();
        sc.nextLine();

        /*
         = Tablero.analizarPos(fila, columna, size);
        if (barco != null) {
            System.out.println("Has golpeado un " + barco.getNombre() + "!");
            // Cambiar el turno al mismo jugador

        } else {
            System.out.println("Has golpeado agua. El turno pasa al otro jugador.");
            // Cambiar el turno al otro jugador
            // ...
        }
         */
    }

    public static boolean comprobarEstadoJuego() {
        //TODO comprobar si todos los barcos de un jugador han sido hundidos
        return false;
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
