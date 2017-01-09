package fr.xebia.javaslang.validation;

import javaslang.control.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CommonValidation<E, O> {
    private List<Function<O, Validation<E, O>>> validations = new ArrayList<>();

    public CommonValidation<E, O> combine(Function<O, Validation<E, O>> f) {
        validations.add(f);
        return this;
    }

    public Validation<List<E>, O> fold(O obj) {
        List<E> errors = validations.stream()
                .map(f -> f.apply(obj))
                .filter(Validation::isInvalid)
                .reduce(new ArrayList<E>(), (list, invalid) -> {
                    list.add(invalid.getError());
                    return list;
                }, (err, err2) -> err2);

        return (errors.isEmpty())
                ? Validation.valid(obj)
                : Validation.invalid(errors);
    }
}
