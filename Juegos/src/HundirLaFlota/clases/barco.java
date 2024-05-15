package HundirLaFlota.clases;

import java.util.ArrayList;
import java.util.Objects;

public class barco {
    private String nombre;
    private int tamano;
    private String posicion;
    public ArrayList<Integer> posicionesx=new ArrayList<Integer>();
    public ArrayList<Integer> posicionesy=new ArrayList<Integer>();

    enum barcos{
        PORTAAVIONES(5),ACORAZADO(4),SUBMARINO(3),DESTRUCTOR(2);
        private final int SIZE;
        barcos(int tamano){
            this.SIZE=tamano;
        }
        public int getSIZE(){
            return SIZE;
        }
    }

    public barco(int tamano,String nombre,String posicion) {
        this.tamano = tamano;
        this.nombre = nombre;
        this.posicion=posicion;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int posiciones(){
        return this.posicionesx.size();
    }
    public void tocado(int fila,int columna){
        this.posicionesx.add(fila);
        this.posicionesy.add(columna);
    }
    @Override
    public String toString() {
        return "barco{" + "nombre=" + nombre + ", tamano=" + tamano + ", posicion=" + posicion + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        barco barco = (barco) o;
        return tamano == barco.tamano && Objects.equals(nombre, barco.nombre) && Objects.equals(posicion, barco.posicion) && Objects.equals(posicionesx, barco.posicionesx) && Objects.equals(posicionesy, barco.posicionesy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, tamano, posicion, posicionesx, posicionesy);
    }
}
