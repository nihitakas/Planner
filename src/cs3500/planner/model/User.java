package cs3500.planner.model;

/**
 * Represents a user with a username and schedule.
 */
public class User {

  private String userName;
  private Schedule schedule;

  /**
   * Constructs a user with the given username and schedule.
   *
   * @param userName the username of the user
   * @param schedule the schedule of the user
   */
  public User(String userName, Schedule schedule) {
    this.userName = userName;
    this.schedule = schedule;
  }

  /**
   * Retrieves the username of the user.
   *
   * @return the username of the user
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Retrieves the schedule of the user.
   *
   * @return the schedule of the user
   */
  public Schedule getSchedule() {
    return schedule;
  }

  /**
   * Adds an event to the user's schedule.
   *
   * @param event the event to be added
   */
  public void addEvent(Event event) {
    schedule.addEvent(event);
  }

  /**
   * Removes an event from the user's schedule.
   *
   * @param event the event to be removed
   */
  public void removeEvent(Event event) {
    schedule.removeEvent(event);
  }
}
