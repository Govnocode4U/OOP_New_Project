package org.saloon_management.utils.xml;

import org.saloon_management.models.Master;
import org.saloon_management.models.Master;
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

public class MasterXmlExporter {
    public static void exportMastersToXml(List<Master> masters, File outputFile) {
        try {
            // Создаем новый документ XML
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Создаем корневой элемент "masters"
            Element rootElement = document.createElement("masters");
            document.appendChild(rootElement);

            // Добавляем каждого клиента как элемент "master" в XML-документ
            for (Master master : masters) {
                Element masterElement = document.createElement("master");
                rootElement.appendChild(masterElement);

                // Добавляем поля клиента в XML-элемент
                Element fullNameElement = document.createElement("fullName");
                fullNameElement.appendChild(document.createTextNode(master.getFullName()));
                masterElement.appendChild(fullNameElement);

                Element phoneElement = document.createElement("specialization");
                phoneElement.appendChild(document.createTextNode(master.getSpecialization()));
                masterElement.appendChild(phoneElement);
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

