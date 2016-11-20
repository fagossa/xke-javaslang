package fr.xebia.film;

public interface FilmRepository {

    void add(Film film);

    void update(Film film);

    Film get(String id);

}
