// NUPlannerSystem.java
package cs3500.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NUPlannerSystem {
  private Map<String, Schedule> schedules; // Map to store schedules by user ID

  public NUPlannerSystem() {
    this.schedules = new HashMap<>();
  }

  public void uploadScheduleXML(String userId, String filePath) {
    // Parse XML file and create corresponding Schedule object
    List<Event> events = XMLReader.readXMLFile(filePath);
 //   Schedule schedule = new Schedule(userId, events);

    // Add schedule to the system
  //  schedules.put(userId, schedule);
  }

  public void saveScheduleXML(String userId, String filePath) {
    // Retrieve the user's schedule
    Schedule schedule = schedules.get(userId);

    // Convert the schedule to XML format and write to file
    String xmlString = schedule.toXMLString();
///    FileIO.writeToFile(xmlString, filePath);
  }


  public void displaySchedule(String userId) {
    // Retrieve the user's schedule
    Schedule schedule = schedules.get(userId);

    // Display the schedule
    System.out.println("Schedule for user: " + userId);
    schedule.display();
  }

  public void manipulateEvent(String userId, Event event) {
    // Retrieve the user's schedule
    Schedule schedule = schedules.get(userId);

    // Manipulate the event in the schedule
    schedule.manipulateEvent(event);
  }

  public void autoScheduleEvent(Event event) {
    // Iterate through all schedules and check for availability
    for (Schedule schedule : schedules.values()) {
      if (schedule.isAvailable(event)) {
        // If available, add event to the schedule
        schedule.addEvent(event);
      }
    }
  }

  public List<Event> getEventsAtTime(String userId, Time time) {
    // Retrieve the user's schedule
    Schedule schedule = schedules.get(userId);

    // Get events at the given time from the schedule
    return schedule.getEventsAtTime(time);
  }

  public static void main(String[] args) {
    NUPlannerSystem planner = new NUPlannerSystem();

    // Example usage
    planner.uploadScheduleXML("user1", "user1-schedule.xml");
    planner.uploadScheduleXML("user2", "user2-schedule.xml");

    planner.displaySchedule("user1");
    planner.displaySchedule("user2");
  }
}
