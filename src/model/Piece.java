package model;

public class Piece {
    private Square location;

    Piece(Square location) {
        this.location = location;
    }

    public void setLocation(Square location) {
        this.location = location;
    }

    public Square getLocation() {
        return this.location;
    }
}
