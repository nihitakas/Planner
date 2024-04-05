package cs3500.planner.model;

import java.util.List;

/**
 * An interface for overseeing the mutable operations in the NU Planner System.
 */
public interface MutablePlanner extends IReadOnlyPlanner {

  /**
   * Adds a schedule for the specified user.
   *
   * @param userId   the ID of the user
   * @param schedule the schedule to add
   */
  void addUserSchedule(String userId, Schedule schedule);

  /**
   * Removes the schedule for the specified user.
   *
   * @param userId the ID of the user to remove the schedule for
   */
  void removeUserSchedule(String userId);

  /**
   * Adds an event for the specified user.
   *
   * @param userId the ID of the user
   * @param event  the event to add
   */
  void addEvent(String userId, Event event);

  /**
   * Removes an event for the specified user.
   *
   * @param userId the ID of the user
   * @param event  the event to remove
   */
  void removeEvent(String userId, Event event);

  /**
   * Modifies an existing event for the specified user.
   *
   * @param userId   the ID of the user
   * @param oldEvent the event to be replaced
   * @param newEvent the new event to replace the old one
   */
  void modifyEvent(String userId, Event oldEvent, Event newEvent);

  /**
   * Automatically schedules the given event if the User is available.
   *
   * @param event the event to be automatically scheduled
   */
  void automaticallyScheduleEvent(Event event);

  /**
   * Adds a schedule to the planner.
   *
   * @param schedule the schedule to add
   */
  void addSchedule(Schedule schedule);

  /**
   * Modifies an existing schedule.
   *
   * @param oldSchedule the schedule to be replaced
   * @param newSchedule the new schedule to replace the old one
   */
  void modifySchedule(Schedule oldSchedule, Schedule newSchedule);

  /**
   * Retrieves the schedule for the specified user.
   *
   * @param userId the ID of the user
   * @return the schedule for the specified user
   */
  Schedule getScheduleForUser(String userId);

  /**
   * Retrieves a list of events at the specified time for the specified user.
   *
   * @param userId the ID of the user
   * @param time   the time to retrieve events for
   * @return a list of events at the specified time for the specified user
   */
  List<Event> getEventsAtTimeForUser(String userId, Time time);
}
