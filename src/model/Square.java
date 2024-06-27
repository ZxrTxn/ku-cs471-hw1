/*
 * Name:    Supakrit Pamakama
 * ID:      6510450968
 */

package model;

public class Square implements Comparable<Square> {
    private final int refNumber;

    private final String name;

    public Square(int refNumber, String name) {
        this.refNumber = refNumber;
        this.name = name;
    }

    public int getRefNumber() {
        return this.refNumber;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Square square) {
        return Integer.compare(this.refNumber, square.refNumber);
    }
}