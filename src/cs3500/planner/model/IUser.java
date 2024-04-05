package cs3500.planner.model;

/**
 * Represents a user in the NUPlanner system. A user has a name and a specific Schedule. A User can
 * be a host. Users also have the ability to add or delete events on their schedule. If a user is a
 * Host, the event that they modify will be modified for all the attendees and invitees of that
 * Event.
 */
public interface IUser {

  /**
   * Retrieves the username of the user.
   *
   * @return the username of the user
   */
  String getUserName();

  /**
   * Retrieves the schedule of the user.
   *
   * @return the schedule of the user
   */
  Schedule getSchedule();

  /**
   * Adds an event to the user's schedule.
   *
   * @param event the event to add
   */
  void addEvent(Event event);

  /**
   * Removes an event from the user's schedule.
   *
   * @param event the event to remove
   */
  void removeEvent(Event event);

}
