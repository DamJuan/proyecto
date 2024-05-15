package MultiplicationGame;


import java.util.Random;

public abstract class MultiplicationGame {
    protected int numPlayers;
    protected String[] players;
    protected MultiplicationProblem problem;
    protected int actualPlayer;

    public MultiplicationGame(int numPlayers) {
        this.numPlayers = numPlayers;
        players = new String[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = "Player " + (i + 1);
        }
        problem = new MultiplicationProblem();
        actualPlayer = 0;
    }

    public abstract void play();

    public static void multiplicationGame(int numPlayers) {
        MultiplicationGame game;
        if (numPlayers == 2) {
            game = new TwoPlayerGame(numPlayers);
        } else if (numPlayers == 3) {
            game = new ThreePlayerGame(numPlayers);
        } else {
            System.out.println("Numero no valido de jugadores.");
            return;
        }
        game.play();
    }


    public class MultiplicationProblem {
        private int num1;
        private int num2;
        private int respuesta;

        public MultiplicationProblem() {
            generateProblem();
        }

        public void generateProblem() {
            Random rand = new Random();
            num1 = rand.nextInt(50) + 1;
            num2 = rand.nextInt(50) + 1;
            respuesta = num1 * num2;
        }

        public int getrespuesta() {
            return respuesta;
        }

        @Override
        public String toString() {
            return num1 + " * " + num2;
        }

    }


}
