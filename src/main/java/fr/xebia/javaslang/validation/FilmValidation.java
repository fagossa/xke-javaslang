package fr.xebia.javaslang.validation;

import fr.xebia.film.Film;
import javaslang.control.Validation;

import java.util.List;

public class FilmValidation {

    private Film film;

    public FilmValidation(Film film) {
        this.film = film;
    }

    public Validation<List<String>, Film> validate() {
        return new CommonValidation<String, Film>()
                .combine(this::validName)
                .combine(this::validRate)
                .combine(this::atLeastOneParticipant)
                .apply(film);
    }

    private Validation<String, Film> atLeastOneParticipant(Film film) {
        return (film.getPeople().isEmpty())
                ? Validation.invalid("No participants")
                : Validation.valid(film);
    }

    private Validation<String, Film> validRate(Film film) {
        return film.getRate() < 1 && film.getRate() > 5
                ? Validation.invalid("invalid rate")
                : Validation.valid(film);
    }

    private Validation<String, Film> validName(Film film) {
        return film.getName().isEmpty()
                ? Validation.invalid("Invalid Name")
                : Validation.valid(film);
    }
}
