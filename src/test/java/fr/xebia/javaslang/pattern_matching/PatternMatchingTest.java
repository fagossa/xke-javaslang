package fr.xebia.javaslang.pattern_matching;

import fr.xebia.film.Film;
import fr.xebia.film.Participant;
import fr.xebia.film.Person;
import fr.xebia.film.Role;
import org.junit.Test;

import java.util.Map;

import static fr.xebia.film.Role.ACTOR;
import static fr.xebia.film.Role.DIRECTOR;
import static fr.xebia.film.Role.WRITER;
import static org.assertj.core.api.Assertions.assertThat;

public class PatternMatchingTest {
    PatternMatching patternMatching = new PatternMatching();

    @Test
    public void should_calculate_movie_cost_javaslang(){
        //given
        final Film movie = new Film("1", "start trek")
                .setRate(3)
                .addParticipant(new Participant(new Person(), ACTOR).setBonus(50f))
                .addParticipant(new Participant(new Person(), ACTOR).setBonus(40f))
                .addParticipant(new Participant(new Person(), DIRECTOR));

        //when
        Map<Role, Double> result = patternMatching.calculateMovieCostByRole(movie);

        //then
        assertThat(result.get(ACTOR)).isEqualTo(890);
        assertThat(result.get(WRITER)).isEqualTo(null);
        assertThat(result.get(DIRECTOR)).isEqualTo(300);
    }

    @Test
    public void should_calculate_movie_cost_java8(){
        //given
        final Film movie = new Film("1", "start trek")
                .setRate(3)
                .addParticipant(new Participant(new Person(), ACTOR).setBonus(50f))
                .addParticipant(new Participant(new Person(), ACTOR).setBonus(40f))
                .addParticipant(new Participant(new Person(), DIRECTOR));

        //when
        Map<Role, Double> result = patternMatching.calculateMovieCostByRoleJava8(movie);

        //then
        assertThat(result.get(ACTOR)).isEqualTo(890);
        assertThat(result.get(WRITER)).isEqualTo(null);
        assertThat(result.get(DIRECTOR)).isEqualTo(300);
    }

}