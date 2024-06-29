/*
 * Name:    Supakrit Pamakama
 * ID:      6510450968
 */

package service.io;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.io.OutputStream;
import java.io.PrintStream;

import java.io.IOException;

import java.util.Arrays;

import property.layout.Position;

public class Controller {
    private static final int SCREEN_WIDTH = 120;
    private static final int SCREEN_HEIGHT = 30;
    
    private static final int MIN_SCREEN_WIDTH = 80;
    private static final int MIN_SCREEN_HEIGHT = 20;
    
    private static final char SPACE = ' ';
    private static final char NEW_LINE = '\n';
    
    private static final char VERTICAL_LINE = '|';
    private static final char HORIZONTAL_LINE = '-';
    
    private static final char VERTICAL_BORDER = VERTICAL_LINE;
    private static final char HORIZONTAL_BORDER = HORIZONTAL_LINE;
    
    private static final char BORDER_TOP = HORIZONTAL_BORDER;
    private static final char BORDER_RIGHT = VERTICAL_BORDER;
    private static final char BORDER_BOTTOM = HORIZONTAL_BORDER;
    private static final char BORDER_LEFT = VERTICAL_BORDER;

    public enum AlertType {
        WARNING ("warning"),
        ERROR ("error");

        private final String message;

        private AlertType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }

    private final int screenWidth;
    private final int screenHeight;

    // top-left corner of the screen
    private final Position start;
    // bottom-right corner of the screen
    private final Position end;

    private final BufferedReader in;
    private final PrintStream out;

    private char[][] screenBuffer;

    public Controller(InputStream in, OutputStream out) {
        this(SCREEN_WIDTH, SCREEN_HEIGHT, in, out);
    }

    public Controller(int screenWidth, int screenHeight, InputStream in, OutputStream out) {
        this.screenWidth = Math.max(screenWidth, MIN_SCREEN_WIDTH);
        this.screenHeight = Math.max(screenHeight, MIN_SCREEN_HEIGHT);

        this.start = new Position(0, 0);
        this.end = new Position(screenWidth - 1, screenHeight - 1);

        screenBuffer = new char[screenHeight][screenWidth];
        this.clearBuffer();

        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = new PrintStream(out);
    }

    public void waitForEnter() throws IOException {
        while (Character.compare((char) in.read(), NEW_LINE) != 0);
    }

    private boolean isOverflow(Position position) {
        return !(position.getX() >= this.start.getX() && position.getX() <= this.end.getX()) &&
                (position.getY() >= this.start.getY() && position.getY() <= this.end.getY());
    }

    public void add(String text, boolean center) {
        int x = (this.end.getX() - text.length() + 1) / 2;
        int y = this.end.getY() / 2;

        this.add(text, new Position(x, y));
    }

    public void add(String text, Position position) {
        for (int i = 0; i < text.length(); i++) {
            Position newPos = new Position(position.getX() + i, position.getY());

            if (!isOverflow(newPos)) {
                screenBuffer[newPos.getY()][newPos.getX()] = text.charAt(i);
            }
        }
    }

    public void alert(Exception e) {
        alert(Controller.AlertType.ERROR, e.getMessage());
    }

    public void alert(AlertType type, String message) {
        add(message, true);
    }

    public void show() {
        this.show(false);
    }

    public void show(boolean border) {
        if (border) {
            for (int i = 0; i < screenWidth; i++) {
                screenBuffer[this.start.getY()][i] = BORDER_TOP;
                screenBuffer[this.end.getY()][i] = BORDER_BOTTOM;
            }

            for (int i = 0; i < screenHeight; i++) {
                screenBuffer[i][this.start.getX()] = BORDER_LEFT;
                screenBuffer[i][this.end.getX()] = BORDER_RIGHT;
            }
        }

        for (int y = start.getY(); y <= end.getY(); y++) {
            for (int x = start.getX(); x <= end.getX(); x++) {
                out.print(screenBuffer[y][x]);
            }
            out.print(NEW_LINE);
        }
    }

    public void clearBuffer() {
        Arrays.stream(screenBuffer).forEach(row -> Arrays.fill(row, SPACE));
    }

    public void clearScreen() {
        System.out.println("\u001b[1J");
    }

    public void close() throws IOException {
        in.close();
        out.close();
    }
}
