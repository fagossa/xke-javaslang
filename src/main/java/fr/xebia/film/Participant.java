package fr.xebia.film;

import java.util.Objects;

public class Participant {
    private Person person;
    private Role role;
    private float bonus = 0;

    public Participant(Person person, Role role) {
        this.person = person;
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public Role getRole() {
        return role;
    }

    public float getBonus() {
        return bonus;
    }

    public Participant setBonus(float bonus) {
        this.bonus = bonus;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;
        Participant that = (Participant) o;
        return Objects.equals(person, that.person) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, role);
    }

    @Override
    public String toString() {
        return "Participation{" +
                "person=" + person +
                ", role=" + role +
                '}';
    }
}
