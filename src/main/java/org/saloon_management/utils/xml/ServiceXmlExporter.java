package org.saloon_management.utils.xml;

import org.saloon_management.models.Client;
import org.saloon_management.models.ServiceModel;
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

public class ServiceXmlExporter {
    public static void exportServicesToXml(List<ServiceModel> services, File outputFile) {
        try {
            // Создаем новый документ XML
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Создаем корневой элемент "clients"
            Element rootElement = document.createElement("services");
            document.appendChild(rootElement);

            // Добавляем каждого клиента как элемент "client" в XML-документ
            for (ServiceModel serviceModel : services) {
                Element clientElement = document.createElement("client");
                rootElement.appendChild(clientElement);

                // Добавляем поля клиента в XML-элемент
                Element fullNameElement = document.createElement("Name");
                fullNameElement.appendChild(document.createTextNode(serviceModel.getName()));
                clientElement.appendChild(fullNameElement);

                Element phoneElement = document.createElement("Price");
                phoneElement.appendChild(document.createTextNode(serviceModel.getPrice()));
                clientElement.appendChild(phoneElement);
            }

            // Сохраняем XML-документ в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputFile);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
            // Обработка ошибок при выгрузке в XML-файл
        }
    }
}

