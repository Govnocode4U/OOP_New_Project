package org.saloon_management.utils.pdf;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.saloon_management.models.Appointment;
import org.saloon_management.models.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class AppointmentPdfGenerator {
    public static void generateAppointmentssPdf(List<Appointment> appointments, File outputFile) {
        try {
            // Создаем новый PDF-документ
            PdfWriter writer = new PdfWriter(outputFile);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            // Создаем таблицу с четырьмя колонками
            Table table = new Table(5);

            // Заголовки колонок
            addTableHeader(table);

            // Данные клиентов
            addTableData(table, appointments);

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
        table.addHeaderCell(createCell("Client"));
        table.addHeaderCell(createCell("Master"));
        table.addHeaderCell(createCell("Service"));
        table.addHeaderCell(createCell("Date"));
    }

    private static void addTableData(Table table, List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            table.addCell(createCell(String.valueOf(appointment.getId())));
            table.addCell(createCell(appointment.getClientName()));
            table.addCell(createCell(appointment.getMasterName()));
            table.addCell(createCell(appointment.getServiceName()));
            table.addCell(createCell(String.valueOf(appointment.getDateTime())));
        }
    }

    private static Cell createCell(String text) {
        Cell cell = new Cell();
        cell.add(new Paragraph(text)); // Use 'Paragraph' instead of 'Text'
        cell.setTextAlignment(TextAlignment.CENTER);
        return cell;
    }

}





