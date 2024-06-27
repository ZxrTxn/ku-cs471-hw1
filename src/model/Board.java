package model;

import java.util.Set;

public class Board {
    private Set<Square> squares;

    Board(Set<Square> squares) {
        this.squares = squares;
    }

    public Square getSquare(Square oldLoc, int fvTot) {
        int startRefNumber = squares.stream().findFirst().get().getRefNumber();
        int newRefInd = (oldLoc.getRefNumber() - startRefNumber + fvTot) % this.squares.size();

        return squares.stream().filter(square -> (square.getRefNumber() == newRefInd + startRefNumber)).findFirst().get();
    }
}
