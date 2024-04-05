package cs3500.planner.model;

import java.util.List;

/**
 * An interface for accessing read-only information from the NU Planner system.
 */
public interface IReadOnlyPlanner {
  /**
   * Returns a list of all the users in the planner.
   *
   * @return a list of all users in the planner
   */
  List<User> getUsers();

  /**
   * Returns a list of all the schedules in the planner.
   *
   * @return a list of all schedules in the planner
   */
  List<Schedule> getAllSchedules();

  /**
   * Checks if the planner has a user with the given user ID.
   *
   * @param userId the ID of the user to check
   * @return true if the planner contains the user, false otherwise
   */
  boolean hasUser(String userId);

  /**
   * Checks if the planner contains an event.
   *
   * @param event the event to check
   * @return true if the planner contains the event, false otherwise
   */
  boolean hasEvent(Event event);

  /**
   * Checks if adding the given event would cause a conflict with existing events in the planner.
   * This could be as a result of a time conflict, or a user conflict.
   *
   * @param event the event to check for conflicts
   * @return true if the event would cause a conflict, false otherwise
   */
  boolean hasEventConflict(Event event);

  /**
   * Returns a list of events for a specified user.
   *
   * @param userId the ID of the user whose events to retrieve
   * @return a list of events associated with the specified user
   */
  List<Event> getEventsForUser(String userId);
}
