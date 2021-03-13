package ru.sber.report;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ExcelReport implements Report {
    @Override
    public byte[] asBytes() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        writeTo(output);

        return output.toByteArray();
    }

    @Override
    public void writeTo(OutputStream os) {
        try {
            book.write(os);
            book.close();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write", e);
        }
    }

    public ExcelReport(Workbook book) {
        this.book = book;
    }

    private final Workbook book;
}
