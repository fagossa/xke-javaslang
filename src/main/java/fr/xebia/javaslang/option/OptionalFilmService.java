package fr.xebia.javaslang.option;

import fr.xebia.film.Film;
import javaslang.control.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptionalFilmService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private OptionalFilmRepository filmRepository;

    public OptionalFilmService(OptionalFilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void addFilm(Film film) {
        final Option<Film> maybeFilm = filmRepository.add(film);
        if (maybeFilm.isEmpty()) {
            logger.warn("Error adding film {}", film);
        }
    }
}
