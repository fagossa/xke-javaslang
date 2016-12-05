package fr.xebia.javaslang.option;

import fr.xebia.film.Film;
import fr.xebia.film.FilmRepository;
import javaslang.control.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptionalFilmService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private FilmRepository filmRepository;

    public OptionalFilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Option<Film> addFilm(Film film) {
        final Option<Film> maybeFilm = Option.of(filmRepository.get(film.getId()));
        if(maybeFilm.isEmpty()) {
            filmRepository.add(film);
            return Option.of(film);
        } else {
            return Option.none();
        }
    }

    public Option<Film> updateFilm(String id, Film that) {
        return Option
                .of(filmRepository.get(id))
                .map(f -> f.setRate(that.getRate()))
                .flatMap(f-> Option.of(filmRepository.update(f)));
    }
}
