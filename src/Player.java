public class Player {
    private final String name;

    private Piece piece;
    private Board board;

    private Die[] dice;

    Player(String name, Piece piece, Board board, Die[] dice) {
        this.name = name;
        this.piece = piece;
        this.board = board;
        this.dice = dice;
    }

    public String getName() {
        return this.name;
    }
}
