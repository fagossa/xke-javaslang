package fr.xebia.film;

import javaslang.control.Try;

public interface FilmRepositoryWithTry {

    Try<Void> add(Film film);

    Try<Film> update(Film film);

    Try<Film> get(String id);

}
