package fr.xebia.film;

import java.util.HashMap;
import java.util.Map;

public class InMemoryFilmRepository implements FilmRepository {

    private Map<String, Film> films = new HashMap<>();

    public void add(Film film) {
        films.put(film.getId(), film);
    }

    public void update(Film film) {
        films.putIfAbsent(film.getId(), film);
    }

    public Film get(String id) {
        return films.get(id);
    }

}
