package fr.xebia.javaslang.trymonad;

import fr.xebia.film.*;
import javaslang.collection.Stream;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static fr.xebia.film.Role.ACTOR;
import static fr.xebia.film.Role.DIRECTOR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TryTest {

    private FilmExport filmExport = new FilmExport();

    @Test
    public void should_export_films_to_xls() throws IOException {
        // Given
        final Film firstFilm = new Film("1", "start trek")
                .setRate(3)
                .addParticipant(new Participant(new Person(), ACTOR))
                .addParticipant(new Participant(new Person(), DIRECTOR));
        final Film secondFilm = new Film("2", "start wars")
                .setRate(5)
                .addParticipant(new Participant(new Person(), ACTOR));

        // When
        filmExport.export(firstFilm, secondFilm);

        // Then
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("films.xls"));
        assertThat(workbook.getNumberOfSheets()).isEqualTo(1);
        HSSFSheet sheet = workbook.getSheetAt(0);

        final HSSFRow firstRow = sheet.getRow(1);
        assertThat(firstRow.getCell(0).getStringCellValue()).isEqualTo("start trek");
        assertThat(firstRow.getCell(1).getNumericCellValue()).isEqualTo(3);
        assertThat(firstRow.getCell(2).getNumericCellValue()).isEqualTo(2);

        final HSSFRow secondRow = sheet.getRow(2);
        assertThat(secondRow.getCell(0).getStringCellValue()).isEqualTo("start wars");
        assertThat(secondRow.getCell(1).getNumericCellValue()).isEqualTo(5);
        assertThat(secondRow.getCell(2).getNumericCellValue()).isEqualTo(1);
    }

    @Test
    public void should_find_director() throws IOException {
        // Given
        final Film firstFilm = new Film("1", "start trek")
                .setRate(3)
                .addParticipant(new Participant(new Person(), ACTOR))
                .addParticipant(new Participant(new Person(), DIRECTOR));
        final Film secondFilm = new Film("2", "start wars")
                .setRate(5)
                .addParticipant(new Participant(new Person(), ACTOR));

        // When
        filmExport.export(firstFilm, secondFilm);
    }


    @Test
    public void should_find_all_movies_director_javaslang_version() throws Exception {
        //given
        FilmRepository filmRepository = Mockito.mock(FilmRepository.class);
        String[] isbns = {"1", "4", "5", "8", "9"};
        mockFindMovies(filmRepository, isbns);

        //when
        List<Participant> directors = filmExport.getDirectorsTry(filmRepository, isbns);

        //then
        directors.forEach(System.out::println);
        assertThat(directors).hasSize(5);
        assertThat(directors).extracting(Participant::getRole).containsOnly(Role.DIRECTOR);
        assertThat(directors).extracting(Participant::getPerson)
                .extracting(Person::getName)
                .containsExactly(Stream.of(isbns)
                        .map(s -> "director" + s)
                        .toJavaArray(String.class));
    }

    @Test
    public void should_find_all_movies_director_java8_version() throws Exception {
        //given
        FilmRepository filmRepository = Mockito.mock(FilmRepository.class);
        String[] isbns = {"1", "4", "5", "8", "9"};
        mockFindMovies(filmRepository, isbns);

        //when
        List<Participant> directors = filmExport.getDirectors(filmRepository, isbns);

        //then
        directors.forEach(System.out::println);
        assertThat(directors).hasSize(5);
        assertThat(directors).extracting(Participant::getRole).containsOnly(Role.DIRECTOR);
        assertThat(directors).extracting(Participant::getPerson)
                .extracting(Person::getName)
                .containsExactly(java.util.stream.Stream.of(isbns)
                        .map(s -> "director" + s)
                        .toArray(String[]::new));
    }

    private void mockFindMovies(FilmRepository filmRepository, String[] isbns) throws Exception {
        for (String isbn : isbns) {
            when(filmRepository.get(isbn)).thenReturn(new Film("movie" + isbn, "start trek")
                    .setRate(3)
                    .addParticipant(new Participant(new Person("actor" + isbn, LocalDateTime.now(), ""), ACTOR))
                    .addParticipant(new Participant(new Person("director" + isbn, LocalDateTime.now(), ""), DIRECTOR)));
        }
    }
}