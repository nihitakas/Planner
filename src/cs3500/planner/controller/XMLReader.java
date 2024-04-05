package cs3500.planner.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import cs3500.planner.model.DaysOfTheWeek;
import cs3500.planner.model.Event;
import cs3500.planner.model.Schedule;
import cs3500.planner.model.Time;
import cs3500.planner.model.User;

/**
 * A  class for reading schedules from XML files and creating Schedules from the input.
 * This method is in the controller because this is the part of the MVC that handles and parses
 * or outputs user result.
 */
public class XMLReader implements IReadXML {

  /**
   * Map used to store users using their name and User for easy access.
   */
  private Map<String, User> userMap;

  /**
   * Constructs a new XMLReader with an empty user map.
   */
  public XMLReader() {
    this.userMap = new HashMap<>();
  }

  /**
   * Reads schedules from XML files.
   *
   * @param directoryPath the path to the directory containing XML files
   * @return a schedule in the desired format.
   */
  public List<Schedule> readSchedulesFromDirectory(String directoryPath) {
    List<Schedule> schedules = new ArrayList<>();
    File directory = new File(directoryPath);
    File[] files = directory.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isFile() && file.getName().endsWith(".xml")) {
          try {
            Schedule schedule = readScheduleFromFile(file);
            if (schedule != null) {
              schedules.add(schedule);
            }
          } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("Error reading schedule from file: " + file.getName());
            e.printStackTrace();
          }
        }
      }
    }
    return schedules;
  }

  /**
   * Reads a schedule from an XML file.
   *
   * @param file the XML file
   * @return the schedule parsed from the XML file
   * @throws IOException                  if an I/O error occurs
   * @throws ParserConfigurationException if a DocumentBuilder cannot be created
   * @throws SAXException                 if any parse errors occur
   */
  public Schedule readScheduleFromFile(File file)
          throws IOException, ParserConfigurationException, SAXException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(file);
    Element root = document.getDocumentElement();
    String scheduleId = root.getAttribute("id");
    NodeList eventNodes = root.getElementsByTagName("event");
    List<Event> events = new ArrayList<>();
    for (int i = 0; i < eventNodes.getLength(); i++) {
      Node eventNode = eventNodes.item(i);
      if (eventNode.getNodeType() == Node.ELEMENT_NODE) {
        Element eventElement = (Element) eventNode;
        String name = getTextFromTag(eventElement, "name");
        DaysOfTheWeek startDay = DaysOfTheWeek.valueOf(getTextFromTag(eventElement,
                "start-day").toUpperCase());
        int start = Integer.parseInt(getTextFromTag(eventElement, "start"));
      }
    }
    return new Schedule(scheduleId, events);
  }

  private String getTextFromTag(Element parent, String tagName) {
    return parent.getElementsByTagName(tagName).item(0).getTextContent();
  }

  //parses a string representing time in "HH:MM" format into a Time object.
  private Time parseTime(String timeStr) {
    String[] parts = timeStr.split(":");
    int hour = Integer.parseInt(parts[0]);
    int minute = Integer.parseInt(parts[1]);
    return new Time(hour, minute);
  }

  //parses a list of user names into a list of User objects.
  private List<User> parseUserList(String userListStr) {
    List<User> userList = new ArrayList<>();
    String[] usernames = userListStr.split(",");
    for (String username : usernames) {
      Schedule schedule = getUserScheduleByUsername(username);
      if (schedule != null) {
        User user = new User(username, schedule);
        userList.add(user);
      } else {
        System.out.println("User with username '" + username + "' not found.");
      }
    }
    return userList;
  }

  //Retrieves the schedule of a user by their username.
  private Schedule getUserScheduleByUsername(String username) {
    User user = userMap.get(username);
    if (user == null) {
      throw new IllegalStateException("User cannot be null");
    } else {
      return user.getSchedule();
    }
  }
}