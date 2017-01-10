package fr.xebia.film;

import java.time.LocalDateTime;
import java.util.Objects;

public class Person {
    private String name;

    private LocalDateTime birthday;

    private String bio;

    public Person() {
    }

    public Person(String name, LocalDateTime birthday, String bio) {
        this.name = name;
        this.birthday = birthday;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(birthday, person.birthday) &&
                Objects.equals(bio, person.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, bio);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", bio='" + bio + '\'' +
                '}';
    }
}
