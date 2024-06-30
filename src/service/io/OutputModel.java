/*
 * Name:    Supakrit Pamakama
 * ID:      6510450968
 */

package service.io;

import java.util.ArrayList;

import property.layout.Padding;
import property.layout.Position;

public abstract class OutputModel<T> {
    protected boolean border;
    protected boolean transparent;

    protected Position position;
    protected Padding padding;

    private ArrayList<OutputBuilder<T, ?>> builders;

    public OutputModel() {
        this.border = false;
        this.transparent = false;

        this.position = new Position(0, 0);
        this.padding = new Padding(0);

        this.builders = new ArrayList<>();
    }

    abstract T buildModel();

    public OutputModel<T> setBorder(boolean border) {
        this.border = border;

        onChange();

        return this;
    }

    public OutputModel<T> setTransparent(boolean transparent) {
        this.transparent = transparent;

        onChange();

        return this;
    }

    public OutputModel<T> setPadding(Padding padding) {
        this.padding = padding;

        onChange();

        return this;
    }

    public OutputModel<T> setPosition(Position position) {
        this.position = position;

        onChange();

        return this;
    }

    void addBuilder(OutputBuilder<T, ?> builder) {
        this.builders.add(builder);
    }

    protected abstract T getModel();

    public boolean hasBorder() {
        return this.border;
    }

    public boolean isTransparent() {
        return this.transparent;
    }

    public Padding getPadding() {
        return this.padding;
    }

    public Position getPosition() {
        return this.position;
    }

    protected final void onChange() {
        builders.forEach(builder -> builder.rebuild());
    }
}
