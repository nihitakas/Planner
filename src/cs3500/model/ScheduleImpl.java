package cs3500.model;

import java.util.ArrayList;
import java.util.List;

public class ScheduleImpl implements Schedule {
  private String userId;
  private List<Event> events;

  public ScheduleImpl(String userId) {
    this.userId = userId;
    this.events = new ArrayList<>();
  }

  @Override
  public String getUserId() {
    return userId;
  }

  @Override
  public List<Event> getEvents() {
    return events;
  }

  @Override
  public void addEvent(Event event) {
    events.add(event);
  }

  @Override
  public void removeEvent(Event event) {
    events.remove(event);
  }

  @Override
  public void modifyEvent(Event oldEvent, Event newEvent) {
    int index = events.indexOf(oldEvent);
    if (index != -1) {
      events.set(index, newEvent);
    }
  }

  @Override
  public boolean isAvailable(Event event) {
    for (Event e : events) {
      if (e.getStartTime().equals(event.getStartTime())) {
        return false; // Conflict found
      }
    }
    return true; // No conflicts found
  }

  @Override
  public List<Event> getEventsAtTime(Time time) {
    List<Event> eventsAtTime = new ArrayList<>();
    for (Event event : events) {
      Time startTime = event.getStartTime();
      Time endTime = event.getEndTime();
      if (time.compareTo(startTime) >= 0 && time.compareTo(endTime) < 0) {
        eventsAtTime.add(event);
      }
    }
    return eventsAtTime;
  }


  @Override
  public String toXMLString() {
    StringBuilder xmlBuilder = new StringBuilder();
    xmlBuilder.append("<?xml version=\"1.0\"?>\n");
    xmlBuilder.append("<schedule id=\"" + userId + "\">\n");
    for (Event event : events) {
      xmlBuilder.append(event.toXMLString());
    }
    xmlBuilder.append("</schedule>");
    return xmlBuilder.toString();
  }

  @Override
  public void display() {
    System.out.println("Schedule for user: " + userId);
    for (Event event : events) {
      System.out.println(event.toString());
    }
  }

  @Override
  public void manipulateEvent(Event event) {
    // Check if the event already exists in the schedule
    boolean eventExists = events.contains(event);

    if (eventExists) {
      // If the event exists, remove it from the schedule
      removeEvent(event);
    } else {
      // If the event doesn't exist, add it to the schedule
      addEvent(event);
    }
  }
}
