package cs3500.planner.model;

import java.util.List;

/**
 * Represents a planner for managing users' schedules and events.
 */

public interface INUPlanner {

  /**
   * Adds a user's schedule to the central system.
   *
   * @param userId   the ID of the user
   * @param schedule the schedule to be added
   */
  void addUserSchedule(String userId, Schedule schedule);

  /**
   * Removes a user's schedule from the central system.
   *
   * @param userId the ID of the user
   */
  void removeUserSchedule(String userId);

  /**
   * Adds an event to a user's schedule and updates the schedules of invited users.
   *
   * @param userId the ID of the user whose schedule the event is added to
   * @param event  the event to be added
   */
  void addEvent(String userId, Event event);

  /**
   * Removes an event from a user's schedule and updates the schedules of invited users.
   *
   * @param userId the ID of the user whose schedule the event is removed from
   * @param event  the event to be removed
   */
  void removeEvent(String userId, Event event);

  /**
   * Modifies an existing event in a user's schedule and updates the schedules of invited users.
   *
   * @param userId   the ID of the user whose schedule the event is modified in
   * @param oldEvent the original event to be modified
   * @param newEvent the new event replacing the old event
   */
  void modifyEvent(String userId, Event oldEvent, Event newEvent);

  /**
   * Gets the events for a given user.
   *
   * @param userId the ID of the user
   * @return a list of events in the user's schedule
   */
  List<Event> getEventsForUser(String userId);

  /**
   * Gets the events at a specific time for a given user.
   *
   * @param userId the ID of the user
   * @param time   the time to check for events
   * @return a list of events occurring at the specified time in the user's schedule
   */
  List<Event> getEventsAtTimeForUser(String userId, Time time);

  /**
   * Automatically schedules an event for users based on certain criteria.
   *
   * @param event the event to be automatically scheduled
   */
  void automaticallyScheduleEvent(Event event);

  /**
   * Gets the schedule for a given user.
   *
   * @param userId the ID of the user
   * @return the schedule of the user, or null if the user does not exist
   */
  Schedule getScheduleForUser(String userId);

  /**
   * Adds a schedule to the planner.
   *
   * @param schedule the schedule to be added
   */
  void addSchedule(Schedule schedule);

  /**
   * Modifies an existing schedule in the planner.
   *
   * @param oldSchedule the original schedule to be modified
   * @param newSchedule the new schedule replacing the old schedule
   */
  void modifySchedule(Schedule oldSchedule, Schedule newSchedule);
}
