    package Ahorcado;

    import java.io.*;
    import java.util.ArrayList;

    enum TipoIncognita {
        PELICULA, LIBRO, GRUPO_MUSICAL
    }

    class Incognita {
        private String texto;
        private TipoIncognita tipo;

        public Incognita(String texto, TipoIncognita tipo) {
            this.texto = texto;
            this.tipo = tipo;
        }

        public static Incognita[] leerIncognitas(File archivo, TipoIncognita tipoIncognita) {
            ArrayList<Incognita> incognitas = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    incognitas.add(new Incognita(linea.trim(), tipoIncognita));
                }
            } catch (FileNotFoundException e) {
                System.out.println("No se encontró el archivo");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return incognitas.toArray(new Incognita[0]);
        }

        public TipoIncognita getTipo() {
            return tipo;
        }

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Incognita incognita = (Incognita) obj;
            return tipo == incognita.tipo && texto.equals(incognita.texto);
        }

        @Override
        public String toString() {
            return "Tipo: " + tipo + ", Texto: " + texto;
        }
    }
