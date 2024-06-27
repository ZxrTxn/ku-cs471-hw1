/*
 * Name:    Supakrit Pamakama
 * ID:      6510450968
 */

import java.util.Set;
import java.util.TreeSet;

import java.util.ArrayList;

import model.Die;
import model.Board;
import model.Piece;
import model.Player;
import model.Square;

public class MGame {
    private static final int DICE_NUMBER = 2;

    private static final int SQUARE_NUMBER = 40;
    private static final int SQUARE_REFERENCE_OFFSET = 0;

    private int roundCnt;
    private int roundNumber;

    private Board board;

    private Die[] dice;
    private Set<Square> squares;
    private Set<Player> players;

    MGame(ArrayList<String> playerNames, int roundNumber) {
        this.roundCnt = 0;
        this.roundNumber = roundNumber;

        this.squares = new TreeSet<>();
        for (int i = 0; i < MGame.SQUARE_NUMBER; i++) {
            this.squares.add(new Square(i + MGame.SQUARE_REFERENCE_OFFSET, ""));
        }

        this.board = new Board(squares);

        this.dice = new Die[MGame.DICE_NUMBER];
        for (int i = 0; i < MGame.DICE_NUMBER; i++) {
            this.dice[i] = new Die();
        }

        this.players = new TreeSet<>();
        for (int i = 0; i < playerNames.size(); i++) {
            this.players.add(new Player(playerNames.get(i), new Piece(this.squares.stream().findFirst().get()), board, dice));
        }
    }

    public void playGame() {
        int N = roundNumber;

        while (roundCnt < N) {
            playRound();
        }
    }

    private void playRound() {
        players.forEach(player -> player.takeTurn());
    }
}
