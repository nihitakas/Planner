package cs3500.planner.model;

import java.util.List;

/**
 * Represents a schedule for a user. A schedule allows the user to see a week by week view of their
 * events (of they have any). Each schedule requires a unique userID for the user, and a list of
 * events for the given user. Depending on the capabilities of the user (host of an event or not),
 * they are able to modify events for all the attendees/invitees.
 */
public interface ISchedule {
  /**
   * Retrieves the user ID associated with the schedule.
   *
   * @return the user ID associated with the schedule
   */
  String getUserId();

  /**
   * Retrieves the list of users who are invited to events in the schedule.
   *
   * @return a list of user IDs representing the invited users
   */
  List<String> getInvitedUsers();

  /**
   * Retrieves the events in the schedule.
   *
   * @return a list of events in the schedule
   */
  List<Event> getEvents();

  /**
   * Adds an event to the schedule.
   *
   * @param event the event to be added
   */
  void addEvent(Event event);


  /**
   * Removes an event from the schedule.
   *
   * @param event the event to be removed
   */
  void removeEvent(Event event);

  /**
   * Modifies an existing event in the schedule.
   *
   * @param oldEvent the original event to be modified
   * @param newEvent the new event replacing the old event
   */
  void modifyEvent(Event oldEvent, Event newEvent);

  /**
   * Checks if the schedule is available for the given event.
   *
   * @param event the event to check availability for
   * @return true if the schedule is available for the event, false otherwise
   */
  boolean isAvailable(Event event);

  /**
   * Retrieves the events at a specific time in the schedule.
   *
   * @param time the time to check for events
   * @return a list of events occurring at the specified time in the schedule
   */
  List<Event> getEventsAtTime(Time time);

  /**
   * Displays the schedule.
   */
  void display();

  /**
   * Allows the client to pick a User, and then loads their Schedule as modeled by a List of
   * Events.
   * @param userId the specific user.
   * @return a list of Events, models a Schedule.
   */
  List<Event> pickUser(String userId);

}
