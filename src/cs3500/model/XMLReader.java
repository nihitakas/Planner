package cs3500.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {

  public static List<Event> readXMLFile(String filePath) {
    List<Event> events = new ArrayList<>();

    try {
      File xmlFile = new File(filePath);
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(xmlFile);
      doc.getDocumentElement().normalize();

      NodeList eventList = doc.getElementsByTagName("event");
      for (int i = 0; i < eventList.getLength(); i++) {
        Element eventElement = (Element) eventList.item(i);
        String name = eventElement.getElementsByTagName("name").item(0).getTextContent();
        // Parse other event details similarly...
        // Construct Event object and add to the list
      //  Event event = new Event(name, startTimeObj, endTimeObj, location, online, host, invitees));
     //   events.add(event);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return events;
  }
}
