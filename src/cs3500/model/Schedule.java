package cs3500.model;
import java.util.List;

public interface Schedule {
  String getUserId();
    List<Event> getEvents();
    void addEvent(Event event);
    void removeEvent(Event event);
    void modifyEvent(Event oldEvent, Event newEvent);
    boolean isAvailable(Event event);
    List<Event> getEventsAtTime(Time time);
    String toXMLString();
    void display();
    void manipulateEvent(Event event);
}