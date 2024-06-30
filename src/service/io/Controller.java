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

import property.layout.Position;

import core.constant.CharacterConstant;

public abstract class Controller<T> {
    protected final BufferedReader in;
    protected final PrintStream out;

    private final OutputBuilder<?, T> builder;

    private boolean autoRefresh;

    protected T buffer;

    public Controller(InputStream in, OutputStream out, OutputBuilder<?, T> builder) {
        this.builder = builder;
        builder.addController(this);

        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = new PrintStream(out);
    }

    public void waitForEnter() throws IOException {
        while (Character.compare((char) in.read(), CharacterConstant.NEW_LINE) != 0);
    }

    protected abstract boolean isOverflow(Position position);

    final void load() {
        this.buffer = builder.build();

        if (autoRefresh) {
            this.show();
        }
    }

    public abstract void show();

    public void enableAutoRefresh() {
        this.autoRefresh = true;
    }

    public void disableAutoRefresh() {
        this.autoRefresh = false;
    }

    public void close() throws IOException {
        in.close();
        out.close();
    }
}
