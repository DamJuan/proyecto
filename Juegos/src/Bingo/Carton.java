package Bingo;

import java.util.*;

public class Carton {
    private int[][] numeros;

    public Carton() {
        numeros = new int[3][9];
        generarCarton();
    }

    public void generarCarton() {
        Random rand = new Random();
        List<Integer> posiblesNumeros = new ArrayList<>();
        for (int i = 1; i <= 90; i++) {
            posiblesNumeros.add(i);
        }

        for (int j = 0; j < 9; j++) {
            Set<Integer> columna = new HashSet<>();
            while (columna.size() < 3) {
                int index = rand.nextInt(posiblesNumeros.size());
                columna.add(posiblesNumeros.remove(index));
            }
            List<Integer> columnaOrdenada = new ArrayList<>(columna);
            Collections.sort(columnaOrdenada);
            for (int i = 0; i < 3; i++) {
                numeros[i][j] = columnaOrdenada.get(i);
            }
        }
        for (int i = 0; i < 3; i++) {
            Arrays.sort(numeros[i]);
        }

    }

            public void mostrarCarton () {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(numeros[i][j] + "\t");
                }
                System.out.println();
            }
        }

            public boolean contieneNumero ( int num){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                    if (numeros[i][j] == num) {
                        numeros[i][j] = 0;
                        return true;
                    }
                }
            }
            return false;
        }

            public boolean cartonCompletado () {
            for (int i = 0; i < 3; i++) {
                boolean completado = true;
                for (int j = 0; j < 9; j++) {
                    if (numeros[i][j] != 0) {
                        completado = false;
                        break;
                    }
                }
                if (completado) {
                    return true;
                }
            }
            return false;
        }

        }
