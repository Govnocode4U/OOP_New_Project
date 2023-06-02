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
import org.saloon_management.models.ServiceModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ServicePdfGenerator {
    public static void generateServicesPdf(List<ServiceModel> serviceModels, File outputFile) {
        try {
            // Создаем новый PDF-документ
            PdfWriter writer = new PdfWriter(outputFile);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            // Создаем таблицу с четырьмя колонками
            Table table = new Table(3);

            // Заголовки колонок
            addTableHeader(table);

            // Данные клиентов
            addTableData(table, serviceModels);

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
        table.addHeaderCell(createCell("Name"));
        table.addHeaderCell(createCell("Price"));
    }

    private static void addTableData(Table table, List<ServiceModel> serviceModels) {
        for (ServiceModel serviceModel : serviceModels) {
            table.addCell(createCell(String.valueOf(serviceModel.getId())));
            table.addCell(createCell(serviceModel.getName()));
            table.addCell(createCell(serviceModel.getPrice()));
        }
    }

    private static Cell createCell(String text) {
        Cell cell = new Cell();
        cell.add(new Paragraph(text)); // Use 'Paragraph' instead of 'Text'
        cell.setTextAlignment(TextAlignment.CENTER);
        return cell;
    }

}





