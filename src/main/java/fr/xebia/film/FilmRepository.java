package fr.xebia.film;

public interface FilmRepository {

    void add(Film film);

    Film update(Film film);

    Film get(String id);

}
