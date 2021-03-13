package ru.sber.report;

import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ExcelReportGeneratorTest {

    @Test
    void emptyListTest() {
        List<DummyClass> emptyList = new ArrayList<>();
        ReportGenerator<DummyClass> generator = new ExcelReportGenerator<>();
        try {
            generator.generate(emptyList);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "List is empty");
            return;
        }

        throw new AssertionError("Generated report from empty list");
    }

    @Test
    void checkColumnNamesTest() {
        List<DummyClass> example = createDummyList();
        ReportGenerator<DummyClass> generator = new ExcelReportGenerator<>();
        Workbook book = getReportBook(generator.generate(example));
        Sheet currentSheet = book.getSheet("DummyClass");
        Row row1 = currentSheet.getRow(0);
        assertEquals("id", row1.getCell(0).getStringCellValue());
        assertEquals("Full id", row1.getCell(1).getStringCellValue());
        assertEquals("path", row1.getCell(2).getStringCellValue());
        assertEquals("Key word", row1.getCell(3).getStringCellValue());
        assertEquals("cvc", row1.getCell(4).getStringCellValue());
    }

    @Test
    void checkColumnValuesTest() {
        List<DummyClass> example = createDummyList();
        ReportGenerator<DummyClass> generator = new ExcelReportGenerator<>();
        Workbook book = getReportBook(generator.generate(example));
        Sheet currentSheet = book.getSheet("DummyClass");
        Row row = currentSheet.getRow(1);
        assertEquals("23443", row.getCell(0).getStringCellValue());
        assertEquals("345343", row.getCell(1).getStringCellValue());
        assertEquals("Ovchinkin street", row.getCell(2).getStringCellValue());
        assertEquals("Pinapple pie", row.getCell(3).getStringCellValue());
        assertEquals("937.01", row.getCell(4).getStringCellValue());
        row = currentSheet.getRow(2);
        assertEquals("23343443", row.getCell(0).getStringCellValue());
        assertEquals("345232343", row.getCell(1).getStringCellValue());
        assertEquals("Moscow", row.getCell(2).getStringCellValue());
        assertEquals("I have a secret", row.getCell(3).getStringCellValue());
        assertEquals("392.0", row.getCell(4).getStringCellValue());
        row = currentSheet.getRow(3);
        assertNull(row);
    }

    private List<DummyClass> createDummyList() {
        List<DummyClass> example = new ArrayList<>();
        example.add(new DummyClass(23443,
                345343,
                "Ovchinkin street",
                "Pinapple pie",
                937.01));
        example.add(new DummyClass(23343443,
                345232343,
                "Moscow",
                "I have a secret",
                392.));

        return example;
    }

    private Workbook getReportBook(Report report) {
        Field bookField;
        try {
            bookField = report.getClass().getDeclaredField("book");
        } catch (NoSuchFieldException e) {
            throw new AssertionError("Wrong returned type");
        }

        bookField.setAccessible(true);
        Workbook book;
        try {
            book = (Workbook) bookField.get(report);
        } catch (IllegalAccessException e) {
            throw new AssertionError("Impossible, but not accessible");
        }

        return book;
    }
}