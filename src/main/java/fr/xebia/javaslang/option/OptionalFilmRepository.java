package fr.xebia.javaslang.option;

import fr.xebia.film.Film;
import javaslang.control.Option;

public interface OptionalFilmRepository {

    Option<Film> add(Film film);

    Option<Film> update(Film film);

    Option<Film> get(String id);

}
