package HundirLaFlota.clases;

public enum Posiciones {

    VERTICAL_ARRIBA(1),
    VERTICAL_ABAJO(2),
    HORIZONTAL_DERECHA(3),
    HORIZONTAL_IZQUIERDA(4);

    private int posicion;

    Posiciones(int posicion) {
        this.posicion = posicion;
    }

    public int getPosicion() {
        return posicion;
    }

    public static Posiciones getPosicion(int posicion) {
        for (Posiciones pos : Posiciones.values()) {
            if (pos.getPosicion() == posicion) {
                return pos;
            }
        }
        return null;
    }

}


