package cs3500.model;
import java.util.List;

// Class to represent an event
public class EventImpl implements Event {
  private String name;
  private Time startTime;
  private Time endTime;
  private String location;
  private boolean online;
  private User host;
  private List<User> invitees;

  public EventImpl(String name, Time startTime, Time endTime, String location,
                   boolean online, User host, List<User> invitees) {
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
    this.location = location;
    this.online = online;
    this.host = host;
    this.invitees = invitees;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Time getStartTime() {
    return startTime;
  }

  @Override
  public Time getEndTime() {
    return endTime;
  }

  @Override
  public String getLocation() {
    return location;
  }

  @Override
  public boolean isOnline() {
    return online;
  }

  @Override
  public User getHost() {
    return host;
  }

  @Override
  public List<User> getInvitees() {
    return invitees;
  }

  @Override
  public String toXMLString() {
    return null;
  }
}