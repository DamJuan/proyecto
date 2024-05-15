package MultiplicationGame;


import java.util.Scanner;

public class TwoPlayerGame extends MultiplicationGame {
    public TwoPlayerGame(int numPlayers) {
        super(numPlayers);
    }

    @Override
    public void play() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String player = players[actualPlayer];
            problem.generateProblem();
            System.out.println("Problema a resolver: " + problem);
            System.out.print(player + ", introduce tu respuesta: ");
            int guess = sc.nextInt();
            if (guess == problem.getrespuesta()) {
                System.out.println("Correcto! " + player + " sigues en el juego.");
            } else {
                System.out.println("Incorrecto! " + player + " te vas del juego aprende a calcular.");
                break;
            }
            actualPlayer = (actualPlayer + 1) % numPlayers;
        }
        sc.close();
    }

}
