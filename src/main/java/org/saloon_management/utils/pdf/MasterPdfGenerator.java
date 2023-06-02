package org.saloon_management.utils.pdf;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.saloon_management.models.Client;
import org.saloon_management.models.Master;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class MasterPdfGenerator {
    public static void generateMastersPdf(List<Master> masters, File outputFile) {
        try {
            // Создаем новый PDF-документ
            PdfWriter writer = new PdfWriter(outputFile);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            // Создаем таблицу с четырьмя колонками
            Table table = new Table(4);

            // Заголовки колонок
            addTableHeader(table);

            // Данные клиентов
            addTableData(table, masters);

            // Добавляем таблицу в документ
            document.add(table);

            // Закрываем документ
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void addTableHeader(Table table) {
        table.addHeaderCell(createCell("Id"));
        table.addHeaderCell(createCell("Full name"));
        table.addHeaderCell(createCell("Specialization"));
    }

    private static void addTableData(Table table, List<Master> masters) {
        for (Master master : masters) {
            table.addCell(createCell(String.valueOf(master.getId())));
            table.addCell(createCell(master.getFullName()));
            table.addCell(createCell(master.getSpecialization()));
        }
    }

    private static Cell createCell(String text) {
        Cell cell = new Cell();
        cell.add(new Paragraph(text)); // Use 'Paragraph' instead of 'Text'
        cell.setTextAlignment(TextAlignment.CENTER);
        return cell;
    }

}





