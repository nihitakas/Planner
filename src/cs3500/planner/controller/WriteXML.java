package cs3500.planner.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import cs3500.planner.model.Event;
import cs3500.planner.model.Schedule;

/**
 * Writes a given schedule into an XML file format.
 */
public class WriteXML implements IWriteXML {

  /**
   * Writes schedules in XML to be sent to a directory.
   * @param schedules the list of schedules to be written to XML
   * @param directoryPath the directory path where XML files will be saved
   */

  public void writeSchedulesToXML(List<Schedule> schedules, String directoryPath) {
    for (Schedule schedule : schedules) {
      writeScheduleToXML(schedule, directoryPath);
    }
  }

  private void writeScheduleToXML(Schedule schedule, String directoryPath) {
    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder;
    try {
      documentBuilder = documentFactory.newDocumentBuilder();
      Document document = documentBuilder.newDocument();

      Element root = document.createElement("schedule");
      root.setAttribute("user_id", schedule.getUserId());
      document.appendChild(root);

      for (Event event : schedule.getEvents()) {
        Element eventElement = document.createElement("event");
        eventElement.setAttribute("name", event.getName());

        root.appendChild(eventElement);
      }

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource domSource = new DOMSource(document);
      String fileName = schedule.getUserId() + ".xml";
      StreamResult streamResult = new StreamResult(new File(directoryPath, fileName));

      transformer.transform(domSource, streamResult);

      System.out.println("Schedule written to XML file: " + fileName);
    } catch (ParserConfigurationException | TransformerException e) {
      e.printStackTrace();
    }
  }
}
