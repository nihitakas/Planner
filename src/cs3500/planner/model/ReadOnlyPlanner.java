package cs3500.planner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Read-only implementation of the planner, allowing clients to retrieve information and
 * schedules.
 */
public class ReadOnlyPlanner implements IReadOnlyPlanner {
  private Map<String, User> users;
  private Map<String, Schedule> schedules;

  /**
   * Constructs a new ReadOnlyPlanner with empty user and schedule maps.
   */
  public ReadOnlyPlanner() {
    users = new HashMap<>();
    schedules = new HashMap<>();
  }

  /**
   * Gets a list of all users in the planner.
   *
   * @return a list of all users in the planner
   */
  @Override
  public List<User> getUsers() {
    return new ArrayList<>(users.values());
  }

  /**
   * Gets a list of all schedules that are loaded in the planner.
   *
   * @return a list of all schedules in the planner
   */
  @Override
  public List<Schedule> getAllSchedules() {
    return new ArrayList<>(schedules.values());
  }

  /**
   * Checks if the planner has a user with the given user ID.
   *
   * @param userId the ID of the user to check
   * @return true if the planner contains the user, false otherwise
   */
  @Override
  public boolean hasUser(String userId) {
    return users.containsKey(userId);
  }

  /**
   * Checks if the planner has a certain event.
   *
   * @param event the event to check
   * @return true if the planner contains the event, false otherwise
   */
  @Override
  public boolean hasEvent(Event event) {
    for (Schedule schedule : schedules.values()) {
      if (schedule.getEvents().contains(event)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if adding the given event would cause a conflict with existing events in the planner.
   *
   * @param event the event to check for conflicts
   * @return true if the event would cause a conflict, false otherwise
   */
  @Override
  public boolean hasEventConflict(Event event) {
    for (Schedule schedule : schedules.values()) {
      if (!schedule.isAvailable(event)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets a list of events associated with the specified user, including events they are invited to.
   *
   * @param userId the ID of the user whose events to retrieve
   * @return a list of events associated with the specified user
   */
  @Override
  public List<Event> getEventsForUser(String userId) {
    List<Event> events = new ArrayList<>();
    for (Schedule schedule : schedules.values()) {
      if (schedule.getUserId().equals(userId)) {
        events.addAll(schedule.getEvents());
      }
    }
    return events;
  }
}
