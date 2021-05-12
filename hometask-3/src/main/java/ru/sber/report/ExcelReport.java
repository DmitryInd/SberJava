package ru.sber.report;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ExcelReport implements Report {
    private final Workbook book;

    public ExcelReport(Workbook book) {
        this.book = book;
    }

    @Override
    public byte[] asBytes() {
        byte[] reportAsBytes;
        try(ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            writeTo(output);
            reportAsBytes = output.toByteArray();
            output.close();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't convert to bytes", e);
        }

        return reportAsBytes;
    }

    @Override
    public void writeTo(OutputStream os) {
        try(book){
            book.write(os);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write", e);
        }
    }
}
