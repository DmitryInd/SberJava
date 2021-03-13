package ru.sber.report;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class ExcelReportGenerator<T> implements ReportGenerator<T> {

    @Override
    public Report generate(List<T> entities) {
        if (entities.isEmpty()) {
            throw new RuntimeException("List is empty");
        }

        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet(entities.get(0).getClass().getSimpleName());
        setColumnNames(getFieldNames(entities.get(0).getClass()), sheet);
        setObjectsValues(entities, sheet);
        sheet.autoSizeColumn(entities.size() + 1);

        return new ExcelReport(book);
    }

    private List<String> getFieldNames(Class<?> objClass) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = objClass.getDeclaredFields();
        for(Field field : fields) {
            String name;
            if (field.isAnnotationPresent(FieldName.class)) {
                FieldName nameAnnotation = field.getAnnotation(FieldName.class);
                name = nameAnnotation.value();
            } else {
                name = field.getName();
            }
            fieldNames.add(name);
        }
        return fieldNames;
    }

    private void setColumnNames(List<String> fieldNames, Sheet sheet) {
        Row columnNames = sheet.createRow(0);
        for (int i = 0; i < fieldNames.size(); i++) {
            Cell column = columnNames.createCell(i);
            column.setCellValue(fieldNames.get(i));
        }
    }

    private void setObjectsValues(List<T> entities, Sheet sheet) {
        Field[] fields = entities.get(0).getClass().getDeclaredFields();
        for (int i = 0; i < entities.size(); i++) {
            setObjectValues(entities.get(i), fields, sheet.createRow(i+1));
        }
    }

    private void setObjectValues(T obj, Field[] fields, Row valuesRow) {
        for (int i = 0; i < fields.length; i++) {
            Cell valueCell = valuesRow.createCell(i);
            fields[i].setAccessible(true);
            try {
                valueCell.setCellValue(fields[i].get(obj).toString());
            } catch (IllegalAccessException ignored) {}
        }
    }
}
