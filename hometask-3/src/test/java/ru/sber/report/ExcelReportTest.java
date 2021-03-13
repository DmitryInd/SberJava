package ru.sber.report;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExcelReportTest {

    @Test
    void checkAsBytesOutputTest() {
        List<DummyClass> example = createDummyList();
        Report report = new ExcelReportGenerator<DummyClass>().generate(example);
        Workbook book = getReportBookAsByteStyle(report);
        checkDataOfBook(book);
    }

    @Test
    void checkWriteToTest() {
        List<DummyClass> example = createDummyList();
        Report report = new ExcelReportGenerator<DummyClass>().generate(example);
        Workbook book = getReportBookWriteToStyle(report);
        checkDataOfBook(book);
    }

    private void checkDataOfBook(Workbook book) {
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

    private Workbook getReportBookAsByteStyle(Report report) {
        ByteArrayInputStream input = new ByteArrayInputStream(report.asBytes());
        Workbook book;
        try {
            book = new XSSFWorkbook(input);
        } catch (IOException e) {
            throw new AssertionError("Wrong format of asBytes() output");
        }

        return book;
    }

    private Workbook getReportBookWriteToStyle(Report report) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        report.writeTo(output);
        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        Workbook book;
        try {
            book = new XSSFWorkbook(input);
        } catch (IOException e) {
            throw new AssertionError("Wrong format of WriteTo() output");
        }

        return book;
    }
}