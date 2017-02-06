package fr.xebia.javaslang.trymonad;

import fr.xebia.film.Film;
import fr.xebia.film.Participant;
import fr.xebia.film.Person;
import fr.xebia.film.Role;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static fr.xebia.film.Role.*;
import static org.assertj.core.api.Assertions.assertThat;

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

}