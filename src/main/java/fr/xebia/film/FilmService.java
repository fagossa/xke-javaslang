package fr.xebia.film;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FilmService {

    final Logger logger = LoggerFactory.getLogger(getClass());

    private FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void addFilm(Film film) {
        try {
        filmRepository.add(film);
        } catch (Exception ex) {
            logger.error("Error persisting film", ex);
        }
    }
}
