package fr.xebia.film;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Film {

    private String id;
    private String name;

    private Integer rate;

    private List<Participant> people = new ArrayList<>();

    public Film(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Film setRate(Integer rate) {
        this.rate = rate;
        return this;
    }

    public Film addParticipant(Participant participant) {
        people.add(participant);
        return this;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getRate() {
        return rate;
    }

    public List<Participant> getPeople() {
        return new ArrayList<>(people);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return Objects.equals(name, film.name) &&
                Objects.equals(rate, film.rate) &&
                Objects.equals(people, film.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rate, people);
    }

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", people=" + people +
                '}';
    }

}
