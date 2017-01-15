package fr.xebia.javaslang.trymonad;

import fr.xebia.film.Film;
import javaslang.control.Try;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.util.function.Consumer;

public class FilmExport {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void export(Film ... films) {
        final Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        Row headRow = sheet.createRow(0);

        Cell headFilm = headRow.createCell(0);
        headFilm.setCellValue("Film");

        Cell headRate = headRow.createCell(1);
        headRate.setCellValue("Rate");

        Cell headParticipant = headRow.createCell(2);
        headParticipant.setCellValue("Count Participant");

        for (int i = 0; i < films.length; i++) {
            addRow(films[i], sheet, i);
        }

        // javaslang doesn't implement try with resource
        // other implementation like cyclops have it
        // https://github.com/aol/cyclops/wiki/try-:-functional-exception-handling-for-java-8
        Try.of(() -> new FileOutputStream("films.xls"))
                .onSuccess(writeAndClose(workbook))
                .onFailure(error -> logger.error("error when open file ", error));
    }

    private Row addRow(Film film, Sheet sheet, int index) {
        Row row = sheet.createRow(index + 1);
        row.createCell(0).setCellValue(film.getName());
        row.createCell(1).setCellValue(film.getRate());
        row.createCell(2).setCellValue(film.getPeople().size());
        return row;
    }

    private Consumer<FileOutputStream> writeAndClose(Workbook workbook) {
        return fileOutputStream -> {
            Try.run(() -> workbook.write(fileOutputStream))
                    .onFailure(error -> logger.error("error when create xls", error));
            Try.run(fileOutputStream::close);
        };
    }

}
