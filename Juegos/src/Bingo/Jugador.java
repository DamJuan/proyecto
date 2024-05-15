package Bingo;

public class Jugador {
    private String nombre;
    private String ciudad;
    private int edad;
    private Carton carton;

    public Jugador(String nombre, String ciudad, int edad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.edad = edad;
        carton = new Carton();
    }

    public void mostrarCarton() {
        carton.mostrarCarton();
    }

    public boolean contieneNumero(int num) {
        return carton.contieneNumero(num);
    }

    public boolean cartonCompletado() {
        return carton.cartonCompletado();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getEdad() {
        return edad;
    }

}
