package cs3500.planner.model;

import java.util.List;

/**
 * Represents an event in the schedule.
 * An event is something that can be added to a calendar. It requires a non-null name, location
 * (online or not), indication of a host, start day, end day, start time, end time, a list of
 * invitees, and a list of attendees.
 */
public interface IEvent {
  /**
   * Retrieves the name of the event.
   *
   * @return the name of the event.
   * @throws IllegalStateException if the name is null
   */
  String getName();

  /**
   * Retrieves the location of the event.
   *
   * @return the location of the event.
   */
  String getLocation();

  /**
   * Checks if the event is online.
   *
   * @return true if the event is online, false otherwise.
   */
  boolean isOnline();

  /**
   * Checks if the User is a host of the event.
   *
   * @return true if the User is a host, false otherwise.
   */
  boolean isHost();

  /**
   * Retrieves the day of the week when the event starts.
   *
   * @return the day of the week when the event starts.
   */
  DaysOfTheWeek getStartDay();

  /**
   * Retrieves the day of the week when the event ends.
   *
   * @return the day of the week when the event ends.
   */
  DaysOfTheWeek getEndDay();

  /**
   * Retrieves the start time of the event.
   *
   * @return the start time of the event.
   */
  Time getStartTime();

  /**
   * Retrieves the end time of the event.
   *
   * @return the end time of the event.
   */
  Time getEndTime();


  /**
   * Retrieves the list of users invited to the event.
   *
   * @return the list of users invited to the event.
   */
  List<User> getInvitees();

  /**
   * Retrieves the list of users attending the event.
   *
   * @return the list of users attending the event.
   */
  List<User> getAttendees();
}
