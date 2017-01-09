package fr.xebia.javaslang.validation;

import javaslang.control.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class CommonValidation<E, O> implements Function<O, Validation<List<E>, O>>{
    private List<Function<O, Validation<E, O>>> validations = new ArrayList<>();

    public CommonValidation<E, O> combine(Function<O, Validation<E, O>> f) {
        validations.add(f);
        return this;
    }

    public Validation<List<E>, O> apply(O obj) {
        List<E> errors = validations.stream()
                .map(f -> f.apply(obj))
                .filter(Validation::isInvalid)
                .map(Validation::getError)
                .collect(toList());

        return (errors.isEmpty())
                ? Validation.valid(obj)
                : Validation.invalid(errors);
    }
}
