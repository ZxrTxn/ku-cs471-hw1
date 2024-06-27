/*
 * Name:    Supakrit Pamakama
 * ID:      6510450968
 */

package model;

public class Piece {
    private Square location;

    public Piece(Square location) {
        this.location = location;
    }

    public void setLocation(Square location) {
        this.location = location;
    }

    public Square getLocation() {
        return this.location;
    }
}
