package HundirLaFlota.clases;

enum TiposBarco {
    PORTAAVIONES(5),
    ACORAZADO(4),
    SUBMARINO(3),
    DESTRUCTOR(2);

    private int size;

    TiposBarco(int size) {
        this.size=size;
    }
    public int getSize() {
        return size;
    }
}
