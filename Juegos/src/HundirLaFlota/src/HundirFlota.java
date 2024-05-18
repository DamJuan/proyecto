import clases.Jugador;
import clases.Tablero;
import clases.TiposBarco;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;


public class HundirFlota {

    static final File REGLAS = new File("src\\Reglas");
    static Scanner sc = new Scanner(System.in);
    static List<Jugador> listaJugadores = new LinkedList<>();
    static List<Tablero> listaTableros = new LinkedList<>();
    private static final Logger LOGGER = LogManager.getLogger();
    static int jugadorActual = 0;

    public static void main(String[] args) {

        try {
            System.out.println("Bienvenido a Hundir la Flota");
            System.out.println("¿Conoces las reglas del juego? S/N:");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("N")) {
                mostrarInstrucciones();
            } else if (respuesta.equalsIgnoreCase("S")) {
                System.out.println("¡Vamos a jugar!");
            }

            System.out.println("¿Contra quién quieres jugar? Jugador o Máquina?");
            String PVPoPVE = sc.nextLine();

            if (PVPoPVE.equalsIgnoreCase("Jugador")) {
                namePVP();
            } else if (PVPoPVE.equalsIgnoreCase("Máquina") || PVPoPVE.equalsIgnoreCase("Maquina")) {
                namePVE();
            }

            System.out.println("¿Quieres salir del juego? (S/N)");
            String respuestaSalir = sc.nextLine();
            if (respuestaSalir.equalsIgnoreCase("S")) {
                System.out.println("Saliendo del juego...");
                salir();
            } else {
                System.out.println("¡Vamos a seguir jugando!");
            }
        } catch (NoSuchElementException e) {
            LOGGER.error("Error al leer la entrada del usuario: " + e.getMessage());
        }
    }

    public static void mostrarInstrucciones() {
        System.out.println("A continuación se mostrarán las reglas del juego:");

        try (BufferedReader br = new BufferedReader(new FileReader(REGLAS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            LOGGER.error("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void namePVP() {
        String nombreJugador1 = "Jugador 1";
        String nombreJugador2 = "Jugador 2";

        try {
            System.out.println("👤 Introduce el nombre del jugador 1 👤: ");
            nombreJugador1 = sc.nextLine();

            System.out.println("👤 Introduce el nombre del jugador 2 👤:");
            nombreJugador2 = sc.nextLine();
        } catch (NoSuchElementException e) {
            LOGGER.error("Error al leer el nombre de los jugadores: " + e.getMessage());
        }

        Tablero tableroJugador1 = new Tablero();
        Tablero tableroJugador2 = new Tablero();

        Jugador jugador1 = new Jugador(nombreJugador1, Boolean.FALSE, tableroJugador1);
        Jugador jugador2 = new Jugador(nombreJugador2, Boolean.FALSE, tableroJugador2);

        try {
            listaJugadores.add(jugador1);
            listaJugadores.add(jugador2);

            listaTableros.add(tableroJugador1);
            listaTableros.add(tableroJugador2);

            System.out.println("Bienvenidos " + listaJugadores.get(0).getNombre() + " y " + listaJugadores.get(1).getNombre() + "! 🎉🎊🎉🎊");
        } catch (NullPointerException e) {
            LOGGER.error("Error al añadir los jugadores o los tableros a la lista: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error("Error al acceder a la lista de jugadores: " + e.getMessage());
        }



        empezarJuego();
    }

    public static void namePVE() {
        String nombreJugador = "Jugador";
        String nombreMaquina = "Máquina";

        try {
            System.out.println("👤 Introduce el nombre del jugador 👤: ");
            nombreJugador = sc.nextLine();

            System.out.println("🤖 Introduce el nombre de la máquina 🤖: ");
            nombreMaquina = sc.nextLine();
        } catch (NoSuchElementException e) {
            LOGGER.error("Error al leer el nombre de los jugadores: " + e.getMessage());
        }

        Tablero tableroJugador = new Tablero();
        Tablero tableroMaquina = new Tablero();

        Jugador jugador = new Jugador(nombreJugador, Boolean.FALSE, tableroJugador);
        Jugador maquina = new Jugador(nombreMaquina, Boolean.TRUE, tableroMaquina);

        try {
            listaJugadores.add(jugador);
            listaJugadores.add(maquina);

            listaTableros.add(tableroJugador);
            listaTableros.add(tableroMaquina);

            System.out.println("Bienvenidos " + listaJugadores.get(0).getNombre() + " y " + listaJugadores.get(1).getNombre() + "! 🎉🎊🎉🎊");
        } catch (NullPointerException e) {
            LOGGER.error("Error al añadir los jugadores o los tableros a la lista: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error("Error al acceder a la lista de jugadores: " + e.getMessage());
        }


        empezarJuego();

        // La maquina coloca los barcos automaticamente
        //maquina.colocarBarcosAutomaticamente();

        System.out.println("Tablero de la maquina: ");
        maquina.getTablero().mostrarTablero();

        //Asi comprobamos si coloca los barcos automaticamente

        empezarJuego();
    }

    public static void empezarJuego() {
        System.out.println("Vamos a colocar los barcos en el tablero. \n");

        for (Jugador jugador : listaJugadores) {
            System.out.println("Turno de " + jugador.getNombre());

            List<Integer> opciones = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

            while (jugador.getBarcos().size() < 5) {

                colocarBarcos(jugador, opciones);

                if (!jugador.getEsMaquina()) {
                    jugador.getTablero().mostrarTablero();
                }
            }
        }

        boolean juegoEnCurso = Boolean.TRUE;
        jugadorActual = 0;

        while (juegoEnCurso) {
            turnoJugador(listaJugadores.get(jugadorActual));
            juegoEnCurso = comprobarEstadoJuego();
        }

        System.out.println("¡Fin del juego!");
    }

    public static void colocarBarcos(Jugador jugador, List<Integer> opciones) {

        int opcionBarco = menuOpcionesJuego(jugador, opciones);


        TiposBarco barco = switch (opcionBarco) {
            case 1 -> TiposBarco.PORTAVIONES;
            case 2 -> TiposBarco.ACORAZADO;
            case 3 -> TiposBarco.SUBMARINO;
            case 4 -> TiposBarco.DESTRUCTOR;
            case 5 -> TiposBarco.FRAGATA;
            default -> null;
        };


        if (barco == null) {
            System.out.println("Opción no válida.");
            return;
        }

        if (jugador.getBarcos().contains(barco)) {
            System.out.println("Ya has colocado este barco.");
            return;
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

        Map<Integer, String> mapOpciones = jugador.getTablero().colocarBarco(fila, columna, barco.getSize());

        int opcionColocarBarco = mostrarOpcionesColocarBarco(mapOpciones, jugador);

        ejecutarOpcion(opcionColocarBarco, mapOpciones, jugador, barco.getSize(), fila, columna);


        if (opcionColocarBarco < mapOpciones.size()) {
            jugador.addBarco(barco);
        }
    }


    public static void ejecutarOpcion(int opcion, Map<Integer, String> mapOpciones, Jugador jugador, int size, int fila, int columna) {
        if (opcion < mapOpciones.size()) {
            jugador.getTablero().marcarPosicionesBarco(mapOpciones.get(opcion), size, fila, columna);
        } else if (opcion == mapOpciones.size()) {
            System.out.println("⚠ Parece ser que no hay espacio suficiente para colocar el barco en esa posición. ⚠");
            System.out.println("🔄 Vuelve a probar con otra posición. 🔄");
        } else {
            System.out.println("❌Opción inválida. Vuelve a intentarlo.❌");
        }
    }

    public static int mostrarOpcionesColocarBarco(Map<Integer, String> mapOpciones, Jugador jugador) {
        if (!jugador.getEsMaquina()) {
            System.out.println("Elige la posición de tu barco:");
            for (Map.Entry<Integer, String> entry : mapOpciones.entrySet()) {
                System.out.println("\n" + entry.getKey() + ". " + entry.getValue());
            }
            return sc.nextInt();
        } else {
            if (mapOpciones.size() > 1) {
                return generarNumeroRandom(1, mapOpciones.size());
            }
            return mapOpciones.size();
        }
    }

    public static int menuOpcionesJuego(Jugador jugador, List<Integer> opciones) {
        System.out.println("Elige el barco que quieras poner:" +
                "\n1. " + TiposBarco.PORTAVIONES.getNombre() + " (" + TiposBarco.PORTAVIONES.getSize() + " casillas)" +
                "\n2. " + TiposBarco.ACORAZADO.getNombre() + " (" + TiposBarco.ACORAZADO.getSize() + " casillas)" +
                "\n3. " + TiposBarco.SUBMARINO.getNombre() + " (" + TiposBarco.SUBMARINO.getSize() + " casillas)" +
                "\n4. " + TiposBarco.DESTRUCTOR.getNombre() + " (" + TiposBarco.DESTRUCTOR.getSize() + " casillas)" +
                "\n5. " + TiposBarco.FRAGATA.getNombre() + " (" + TiposBarco.FRAGATA.getSize() + " casillas)");
        int opcion;

        if (!jugador.getEsMaquina()) {
            opcion = sc.nextInt();
            return opcion;

        } else {
/*
            boolean validOption = false;
            while (!validOption) {
                opcion = generarNumeroRandom(1, 5);
                if (opciones.contains(opcion) && !jugador.getBarcos().contains(TiposBarco.values()[opcion - 1])) {
                    validOption = true;
                }
            }


            List<Integer> opcionesDisponibles = new ArrayList<>(opciones);
            for (int i = 0; i < opciones.size(); i++) {
                if (jugador.getBarcos().contains(TiposBarco.values()[opciones.get(i) - 1])) {
                    opcionesDisponibles.remove(opciones.get(i));
                }
            }
            if (!opcionesDisponibles.isEmpty()) {
                opcion = opcionesDisponibles.get(generarNumeroRandom(0, opcionesDisponibles.size()));
                opciones.remove((Integer) opcion);
            } else {
                opcion = -1;
            }


*/

            List<Integer> opcionesDisponibles = new ArrayList<>();

            opcion = generarNumeroRandom(1, 5);
            if (!opcionesDisponibles.contains(opcion)) {
                opcionesDisponibles.add(opcion);
                return opcion;
            } else if (opcionesDisponibles.size() == 5) {
                //
            }

        }
        return -1;
    }

    public static int generarNumeroRandom(int limiteInicial, int limiteFinal) {
        Random random = new Random();
        return random.nextInt(limiteFinal - limiteInicial) + limiteInicial;
    }

    public static void turnoJugador(Jugador jugador) {
        System.out.println("Turno de " + jugador.getNombre());

        System.out.println("Elige una posición para atacar (primero la fila, luego la columna):");
        int fila = sc.nextInt();
        int columna = sc.nextInt();
        sc.nextLine();

        System.out.println("El jugador " + jugador.getNombre() + " ha atacado a la posición " + fila + ", " + columna + " del tablero del oponente.");

        Jugador oponente = listaJugadores.get((listaJugadores.indexOf(jugador) + 1) % listaJugadores.size());

        if (oponente.getTablero().hayBarcoEnPosicion(fila, columna)) {
            System.out.println("Has golpeado un barco!");
            oponente.getTablero().marcarGolpe(fila, columna);
            oponente.getTablero().mostrarTableroConOponente();

        } else {
            System.out.println("Has golpeado agua.");
            oponente.getTablero().marcarAgua(fila, columna);
            oponente.getTablero().mostrarTableroConOponente();
            // Cambio de turno solo si se golpea agua
            jugadorActual = (jugadorActual + 1) % listaJugadores.size();

        }
    }

    public static boolean comprobarEstadoJuego() {
        for (Jugador jugador : listaJugadores) {
            if (jugador.getTablero().todosBarcosHundidos()) {
                System.out.println("¡" + jugador.getNombre() + " ha perdido todos sus barcos!");
                return Boolean.FALSE; // El juego termina si un jugador pierde todos sus barcos
            }
        }
        return Boolean.TRUE; // El juego sigue si ningún jugador ha perdido todos sus barcos
    }

    public static void salir() {
        System.out.println("Gracias por jugar!");
        System.exit(0);
    }
}
