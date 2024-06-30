/*
 * Name:    Supakrit Pamakama
 * ID:      6510450968
 */

package property.layout;

public class Padding {
    private final int top;
    private final int right;
    private final int bottom;
    private final int left;

    public Padding(int all) {
        this(all, all, all, all);
    }

    public Padding(int vertical, int horizontal) {
        this(horizontal, vertical, horizontal, vertical);
    }

    public Padding(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public int getTop() {
        return this.top;
    }

    public int getRight() {
        return this.right;
    }

    public int getBottom() {
        return this.bottom;
    }

    public int getLeft() {
        return this.left;
    }
}
