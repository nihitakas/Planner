package cs3500.planner.model;

/**
 * Represents a host user.
 */
public class Host extends User {

  /**
   * Constructs a host with the given username and schedule.
   *
   * @param userName the username of the host
   * @param schedule the schedule of the host
   */
  public Host(String userName, Schedule schedule) {
    super(userName, schedule);
  }

}
