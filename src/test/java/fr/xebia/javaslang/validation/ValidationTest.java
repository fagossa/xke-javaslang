package fr.xebia.javaslang.validation;

import fr.xebia.film.Film;
import javaslang.control.Validation;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidationTest {

    @Test
    public void should_detect_films_no_participants_and_bad_rate() {
        // given
        Film aFilm = new Film(UUID.randomUUID().toString(), "Red Dragon");

        // when
        FilmValidation filmValidation = new FilmValidation(aFilm);

        // then
        final Validation<List<String>, Film> validation = filmValidation.validate();
        assertTrue(validation.isInvalid());
        assertThat(validation.getError()).hasSize(2).containsOnly("No participants", "invalid rate");
    }
}
