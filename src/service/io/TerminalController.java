/*
 * Name:    Supakrit Pamakama
 * ID:      6510450968
 */

package service.io;

import java.io.InputStream;
import java.io.OutputStream;

import java.util.Arrays;

import property.layout.Position;

import core.constant.ANSI;
import core.constant.CharacterConstant;

public class TerminalController extends Controller<char[][]> {
    private static final int SCREEN_WIDTH = 120;
    private static final int SCREEN_HEIGHT = 30;

    private static final int MIN_SCREEN_WIDTH = 80;
    private static final int MIN_SCREEN_HEIGHT = 20;

    private final int screenWidth;
    private final int screenHeight;

    public TerminalController(InputStream in, OutputStream out, OutputBuilder<?, char[][]> builder) {
        this(in, out, builder, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public TerminalController(InputStream in, OutputStream out, OutputBuilder<?, char[][]> builder, int screenWidth, int screenHeight) {
        super(in, out, builder);

        this.screenWidth = Math.max(screenWidth, MIN_SCREEN_WIDTH);
        this.screenHeight = Math.max(screenHeight, MIN_SCREEN_HEIGHT);
    }

    @Override
    protected boolean isOverflow(Position position) {
        return !(position.getX() >= 0 && position.getX() < screenWidth) &&
                (position.getY() >= 0 && position.getY() < screenHeight);
    }

    @Override
    public void show() {
        clearScreen();

        final char[][] screen = new char[screenHeight][screenWidth];

        Arrays.stream(screen).forEach(row -> Arrays.fill(row, CharacterConstant.SPACE));

        for (int y = 0; y < buffer.length; y++) {
            for (int x = 0; x < buffer[y].length; x++) {
                if (!isOverflow(new Position(x, y))) {
                    screen[y][x] = buffer[y][x];
                }
            }
        }

        for (int x = 0; x < screenWidth; x++) {
            screen[0][x] = CharacterConstant.BORDER_TOP;
            screen[screenHeight - 1][x] = CharacterConstant.BORDER_BOTTOM;
        }

        for (int y = 0; y < screenHeight; y++) {
            screen[y][0] = CharacterConstant.BORDER_LEFT;
            screen[y][screenWidth - 1] = CharacterConstant.BORDER_RIGHT;
        }

        for (int y = 0; y < screenHeight; y++) {
            for (int x = 0; x < screenWidth; x++) {
                this.out.print(screen[y][x]);
            }

            this.out.print(CharacterConstant.NEW_LINE);
        }
    }

    protected void clearScreen() {
        System.out.println(ANSI.CSI_ED);
    }
}
