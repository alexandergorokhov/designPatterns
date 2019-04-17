package design.patterns.structural;

import org.hibernate.criterion.Restrictions;

public class TemplateMethod {
    public static void main(String[] args) {
        Chess chess = new Chess();
        chess.run();
    }

}

abstract class Game {
    protected int currentPlayer;
    protected final int numberOfPlayer;

    protected abstract int getWinningPlayer();

    protected abstract void taketurn();

    protected abstract boolean haveWinner();

    protected abstract void start();


    public Game(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public void run() {
        start();
        while (!haveWinner()) {
            taketurn();
            System.out.println("Player " + getWinningPlayer() + " wins");
        }
    }


}

class Chess extends Game {


    private int maxturns = 10;
    private int turn = 1;

    public Chess() {
        super(2);
    }

    @Override
    protected int getWinningPlayer() {
        return 0;
    }

    @Override
    protected void taketurn() {
        System.out.println("Turn  " + (turn++) + " taken by player " + currentPlayer);
    }

    @Override
    protected boolean haveWinner() {
        return turn==maxturns;
    }

    @Override
    protected void start() {
        System.out.println("Starting a ne game");
    }
}

