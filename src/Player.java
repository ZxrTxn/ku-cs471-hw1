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

    public void takeTurn() {
        int fvTot = 0;

        for (int i = 0; i < dice.length; i++) {
            dice[i].roll();
            fvTot += dice[i].getFaceValue();
        }

        Square oldLoc = piece.getLocation();
        Square newLoc = board.getSquare(oldLoc, fvTot);
        piece.setLocation(newLoc);
    }

    public String getName() {
        return this.name;
    }
}
