package clases;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Jugador implements Serializable, Comparable<Jugador> {

    private String nombre;
    private boolean esMaquina;
    private Set<TiposBarco> barcos = new HashSet<>();
    private Tablero tablero;

    // Constructor
    public Jugador(String nombre, boolean esMaquina, Tablero tablero) {
        setNombre(nombre);
        setEsMaquina(esMaquina);
        setTablero(tablero);
    }

    public void colocarBarcosAutomaticamente() {
        // Lista de barcos a colocar
        TiposBarco[] barcos = {TiposBarco.PORTAVIONES, TiposBarco.ACORAZADO, TiposBarco.SUBMARINO, TiposBarco.DESTRUCTOR, TiposBarco.FRAGATA};

        // Colocar cada barco en una posición aleatoria
        for (TiposBarco barco : barcos) {
            boolean barcoColocado = false;
            while (!barcoColocado) {
                int fila = HundirFlota.generarNumeroRandom(0, 9);
                int columna = HundirFlota.generarNumeroRandom(0, 9);
                Map<Integer, String> mapOpciones = tablero.colocarBarco(fila, columna, barco.getSize());
                if (!mapOpciones.isEmpty()) {
                    // Seleccionar una opción aleatoria del mapa de opciones
                    List<Integer> keys = new ArrayList<>(mapOpciones.keySet());
                    int opcion = keys.get(HundirFlota.generarNumeroRandom(0, keys.size()));
                    // Ejecutar la opción seleccionada
                    HundirFlota.ejecutarOpcion(opcion, mapOpciones, this, barco.getSize(), fila, columna);
                    // Agregar el barco a la lista de barcos del jugador
                    addBarco(barco);
                    barcoColocado = true;
                }
            }
        }
    }



    // Getters y Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEsMaquina(boolean esMaquina) {
        this.esMaquina = esMaquina;
    }

    public void addBarco(TiposBarco barco) {
        barcos.add(barco);
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public String getNombre() {
        return this.nombre;
    }

    public boolean getEsMaquina() {
        return this.esMaquina;
    }

    public Set<TiposBarco> getBarcos() {
        return barcos;
    }

    public Tablero getTablero() {
        return tablero;
    }

    // equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(nombre, jugador.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    // compareTo
    @Override
    public int compareTo(Jugador jugador) {
        if (this.barcos.size() > jugador.barcos.size()) {
            return 1;
        } else if (this.barcos.size() < jugador.barcos.size()) {
            return -1;
        } else {
            return 0;
        }
    }
}
