package HundirLaFlota.clases;

import java.util.Map;
import java.util.Set;

public enum TiposBarco {
    PORTAVIONES(5, "Portaviones"),
    ACORAZADO(4, "Acorazado"),
    SUBMARINO(3, "Submarino"),
    DESTRUCTOR(2, "Destructor"),
    FRAGATA(1, "Fragata");

    private int size;
    private String nombre;


    TiposBarco(int size, String nombre) {
        this.size=size;
        this.nombre = nombre;
    }

    public int getSize() {
        return size;
    }

    public String getNombre() {
        return nombre;
    }

}