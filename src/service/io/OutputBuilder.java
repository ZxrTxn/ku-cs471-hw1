/*
 * Name:    Supakrit Pamakama
 * ID:      6510450968
 */

package service.io;

import java.util.ArrayList;

public abstract class OutputBuilder<S, T> {
    protected ArrayList<OutputModel<S>> outputModels;

    protected ArrayList<Controller<T>> controllers;

    public OutputBuilder() {
        this.outputModels = new ArrayList<>();
        this.controllers = new ArrayList<>();
    }

    abstract T build();

    final void rebuild() {
        controllers.forEach(controller -> controller.load());
    };

    public void addOutputModel(OutputModel<S> model) {
        this.outputModels.add(model);
        model.addBuilder(this);

        rebuild();
    }

    void addController(Controller<T> controller) {
        this.controllers.add(controller);
    }

    public void removeOutputModel(OutputModel<S> model) {
        this.outputModels.remove(model);

        rebuild();
    }
}
