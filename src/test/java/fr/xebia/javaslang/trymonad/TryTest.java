package fr.xebia.javaslang.trymonad;

import fr.xebia.film.Film;
import fr.xebia.film.Participant;
import fr.xebia.film.Person;
import fr.xebia.film.Role;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TryTest {

    @Test
    public void should_test_export_file() throws IOException {
        //given
        FilmExport filmExport = new FilmExport();
        List<Film> films = new ArrayList<>();
        films.add(new Film("1", "start trek")
                .setRate(3)
                .addParticipant(new Participant(new Person(), Role.ACTOR))
                .addParticipant(new Participant(new Person(), Role.DIRECTOR)));
        films.add(new Film("2", "start wars").setRate(5).addParticipant(new Participant(new Person(), Role.ACTOR)));

        //when
        filmExport.export(films);

        //

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("films.xls"));
        assertThat(workbook.getNumberOfSheets()).isEqualTo(1);
        HSSFSheet sheet = workbook.getSheetAt(0);
        assertThat(sheet.getRow(1).getCell(0).getStringCellValue()).isEqualTo("start trek");
        assertThat(sheet.getRow(1).getCell(1).getNumericCellValue()).isEqualTo(3);
        assertThat(sheet.getRow(1).getCell(2).getNumericCellValue()).isEqualTo(2);

        assertThat(sheet.getRow(2).getCell(0).getStringCellValue()).isEqualTo("start wars");
        assertThat(sheet.getRow(2).getCell(1).getNumericCellValue()).isEqualTo(5);
        assertThat(sheet.getRow(2).getCell(2).getNumericCellValue()).isEqualTo(1);
    }

}