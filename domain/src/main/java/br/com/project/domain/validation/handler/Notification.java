package br.com.project.domain.validation.handler;

import br.com.project.domain.validation.Error;
import br.com.project.domain.validation.ValidationHandler;
import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {

    private final List<Error> errors;

    private Notification(final List<Error> errors) {
        this.errors = errors;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    @Override
    public Notification append(final Error anError) {
        this.errors.add(anError);
        return this;
    }

    @Override
    public List<Error> getErrors() {
        return this.errors;
    }
}