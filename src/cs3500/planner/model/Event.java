package cs3500.planner.model;

import java.util.List;

/**
 * An event that is present in the schedule.
 *  * Class Invariant:
 *  * - The name of an event must always be non-null.
 *  * - The name of a location must always be non-null.
 *  * - The start and end time must always be non-null.
 *  * - The start and end day must always be non-null.
 *  * - The list of invitees must always be non-null.
 *  * - The list of attendees must always be non-null.
 */
public class Event implements IEvent {
  private final String name;
  private Time startTime;
  private final Time endTime;
  private final String location;
  private final boolean online;
  private final boolean host;
  private final DaysOfTheWeek startDay;

  private final DaysOfTheWeek endDay;
  private final List<User> invitees;
  private final List<User> attendees;
  private String hostUserId;


  /**
   * Constructs an event.
   *
   * @param name     the name of the event.
   * @param startTime the start time of the event.
   * @param endTime   the end time of the event.
   * @param location the location of the event.
   * @param online   indicates if the event is online.
   * @param host     is the User a host or not.
   * @param invitees the list of users invited to the event.
   * @param startDay      the day of the week for the event.
   * @param endDay      the day of the week for the event.
   *
   */
  public Event(String name, Time startTime, Time endTime, String location, boolean online,
               boolean host, List<User> invitees, List<User> attendees, DaysOfTheWeek startDay,
               DaysOfTheWeek endDay) {
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
    this.location = location;
    this.online = online;
    this.host = host;
    this.invitees = invitees;
    this.attendees = attendees;
    this.startDay = startDay;
    this.endDay = endDay;
  }

  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }

  public String getHostUserId() {
    return hostUserId;
  }

  /**
   * Retrieves the name of the event.
   *
   * @return the name of the event.
   * @throws IllegalArgumentException if the name is null
   */
  @Override
  public String getName() {
    if (name == null) {
      throw new IllegalArgumentException("Event name cannot be null");
    }
    return name;
  }

  /**
   * Retrieves the location of the event.
   *
   * @return the location of the event.
   * @throws IllegalArgumentException if the location is null.
   */
  @Override
  public String getLocation() {
    if (location == null) {
      throw new IllegalArgumentException("Location name cannot be null");
    }
    return location;
  }

  /**
   * Checks if the event is online.
   *
   * @return true if the event is online, false otherwise.
   */
  @Override
  public boolean isOnline() {
    return online;
  }
  /**
   * Checks if the event is online.
   *
   * @return true if the event is online, false otherwise.
   */

  @Override
  public boolean isHost() {
    return host;
  }

  /**
   * Retrieves the day of the week when the event starts.
   *
   * @return the day of the week when the event starts.
   * @throws IllegalArgumentException if the start day null.
   */
  @Override
  public DaysOfTheWeek getStartDay() {
    if (startDay == null) {
      throw new IllegalArgumentException("The starting day cannot be null");
    }
    return startDay;
  }

  /**
   * Retrieves the day of the week when the event ends.
   *
   * @return the day of the week when the event ends.
   * @throws IllegalArgumentException if the end day is null.
   */
  @Override
  public DaysOfTheWeek getEndDay() {
    if (endDay == null) {
      throw new IllegalArgumentException("The ending day cannot be null");
    }
    return endDay;
  }

  /**
   * Retrieves the start time of the event.
   *
   * @return the start time of the event.
   * @throws IllegalArgumentException if the start time null.
   */
  @Override
  public Time getStartTime() {
    if (startTime == null) {
      throw new IllegalArgumentException("The starting time cannot be null");
    }
    return startTime;
  }

  /**
   * Retrieves the end time of the event.
   *
   * @return the end time of the event.
   * @throws IllegalArgumentException if the end time is null.
   */
  @Override
  public Time getEndTime() {
    if (endTime == null) {
      throw new IllegalArgumentException("The ending time cannot be null");
    }
    return endTime;
  }

  /**
   * Retrieves the list of users invited to the event.
   *
   * @return the list of users invited to the event.
   * @throws IllegalArgumentException if the invitees are null.
   */
  @Override
  public List<User> getInvitees() {
    if (invitees == null) {
      throw new IllegalStateException("The list of invitees cannot be null");
    }
    return invitees;
  }

  /**
   * Retrieves the list of users attending the event.
   *
   * @return the list of users attending the event.
   * @throws IllegalArgumentException if the attendees are null.
   */
  @Override
  public List<User> getAttendees() {
    if (attendees == null) {
      throw new IllegalArgumentException("The list of attendees cannot be null");
    }
    return attendees;
  }
}
