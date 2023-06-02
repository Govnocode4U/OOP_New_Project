package org.saloon_management.utils.xml;

import org.saloon_management.models.Appointment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class AppointmentXmlExporter {
    public static void exportAppointmentsToXml(List<Appointment> appointments, File outputFile) {
        try {
            // Создаем новый документ XML
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Создаем корневой элемент "clients"
            Element rootElement = document.createElement("appointments");
            document.appendChild(rootElement);

            // Добавляем каждого клиента как элемент "client" в XML-документ
            for (Appointment appointment : appointments) {
                Element clientElement = document.createElement("appointment");
                rootElement.appendChild(clientElement);

                // Добавляем поля клиента в XML-элемент
                Element fullNameElement = document.createElement("Client");
                fullNameElement.appendChild(document.createTextNode(appointment.getClientName()));
                clientElement.appendChild(fullNameElement);

                Element phoneElement = document.createElement("Master");
                phoneElement.appendChild(document.createTextNode(appointment.getMasterName()));
                clientElement.appendChild(phoneElement);

                Element emailElement = document.createElement("Service");
                emailElement.appendChild(document.createTextNode(appointment.getServiceName()));
                clientElement.appendChild(emailElement);

                Element dateElement = document.createElement("Date");
                dateElement.appendChild(document.createTextNode(String.valueOf(appointment.getDateTime())));
                clientElement.appendChild(emailElement);
            }

            // Сохраняем XML-документ в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputFile);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

