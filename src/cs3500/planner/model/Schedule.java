package cs3500.planner.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a schedule for a user.
 * Class Invariant
 *  - The user ID and list of events cannot be null
 */
public class Schedule implements ISchedule {
  private String userId;
  private List<Event> events;
  private List<String> invitedUsers;

  /**
   * Constructs a schedule for a user.
   *
   * @param userId the ID of the user
   * @param events the list of events in the schedule
   */
  public Schedule(String userId, List<Event> events) {
    this.userId = userId;
    this.events = new ArrayList<>(events);
    this.invitedUsers = new ArrayList<>(invitedUsers);

  }

  /**
   * Retrieves the user ID associated with the schedule.
   *
   * @return the user ID associated with the schedule
   */
  @Override
  public String getUserId() {
    if (userId == null) {
      throw new IllegalStateException("User Id cannot be null");
    }
    return userId;
  }

  /**
   * Retrieves the list of users who are invited to events in the schedule.
   *
   * @return a list of user IDs representing the invited users
   */
  @Override
  public List<String> getInvitedUsers() {
    return new ArrayList<>(invitedUsers);
  }

  /**
   * Retrieves the events in the schedule.
   *
   * @return a list of events in the schedule
   */
  @Override
  public List<Event> getEvents() {
    if (events == null) {
      throw new IllegalStateException("List of events cannot be null or empty");
    }
    return new ArrayList<>(events);
  }

  /**
   * Adds an event to the schedule.
   *
   * @param event the event to be added.
   * @throws IllegalArgumentException if the user is not available.
   *
   */
  @Override
  public void addEvent(Event event) {
    if (!isAvailable(event)) {
      throw new IllegalArgumentException("Time conflict.");
    } else {
      events.add(event);
    }
  }


  /**
   * Removes an event from the schedule.
   *
   * @param event the event to be removed
   */
  @Override
  public void removeEvent(Event event) {
    if (!event.isHost()) {
      throw new IllegalArgumentException("Only the host can delete the event.");
    } else {
      events.remove(event);
    }
  }

  /**
   * Modifies an existing event in the schedule.
   *
   * @param oldEvent the original event to be modified
   * @param newEvent the new event replacing the old event
   */
  @Override
  public void modifyEvent(Event oldEvent, Event newEvent) {
    if (!newEvent.isHost()) {
      throw new IllegalArgumentException("Only the host can delete the event.");
    } else {
      int index = events.indexOf(oldEvent);
      if (index != -1) {
        events.set(index, newEvent);
      }
    }
  }

  /**
   * Checks if the schedule is available for the given event.
   *
   * @param event the event to check availability for
   * @return true if the schedule is available for the event, false otherwise
   */
  @Override
  public boolean isAvailable(Event event) {
    for (Event e : events) {
      if (e.getStartTime().compareTo(event.getEndTime()) < 0
              && e.getEndTime().compareTo(event.getStartTime()) > 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * Retrieves the events at a specific time in the schedule.
   *
   * @param time the time to check for events
   * @return a list of events occurring at the specified time in the schedule
   */
  @Override
  public List<Event> getEventsAtTime(Time time) {
    List<Event> eventsAtTime = new ArrayList<>();
    for (Event event : events) {
      Time startTime = event.getStartTime();
      Time endTime = event.getEndTime();
      if (time.compareTo(startTime) >= 0 && time.compareTo(endTime) < 0) {
        eventsAtTime.add(event);
      }
    }
    return eventsAtTime;
  }

  /**
   * Displays the schedule.
   */
  @Override
  public void display() {
    System.out.println("Schedule for user: " + userId);
    for (Event event : events) {
      System.out.println(event.toString());
    }
  }

  /**
   * Retrieves the schedule for a specific user.
   *
   * @param userId the ID of the user whose schedule is to be retrieved
   * @return the schedule of the specified user
   * @throws IllegalArgumentException if the user ID is not found in the schedule
   */
  public List<Event> pickUser(String userId) {
    if (!this.userId.equals(userId)) {
      throw new IllegalArgumentException("User ID not found in the schedule");
    }
    return new ArrayList<>(events);
  }

  /**
   * Checks if all invitees and the host are available for the given duration starting at the specified time.
   *
   * @param durationInMin the duration of the event in minutes
   * @param startTime     the start time of the event
   * @return true if all invitees and the host are available, false otherwise
   */
  public boolean isAvailableForAll(int durationInMin, Time startTime) {
    // Check if the user is available at the specified time
    if (!isUserAvailable(userId, startTime, durationInMin)) {
      return false;
    }

    // Check if all invitees are available at the specified time
    for (Event event : events) {
      if (!isUserAvailable(event.getHostUserId(), startTime, durationInMin) ||
              !areInviteesAvailable(getUserIds(event.getInvitees()), startTime, durationInMin)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Checks if a user is available at the specified time for the given duration.
   *
   * @param userId       the ID of the user to check availability for
   * @param startTime    the start time to check for availability
   * @param durationInMin the duration of the event in minutes
   * @return true if the user is available, false otherwise
   */
  private boolean isUserAvailable(String userId, Time startTime, int durationInMin) {
    for (Event event : events) {
      if (event.getHostUserId().equals(userId)) {
        if (!isTimeAvailable(startTime, durationInMin, event.getStartTime(), event.getEndTime())) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Checks if all invitees are available at the specified time for the given duration.
   *
   * @param invitees     the list of invitees to check availability for
   * @param startTime    the start time to check for availability
   * @param durationInMin the duration of the event in minutes
   * @return true if all invitees are available, false otherwise
   */
  private boolean areInviteesAvailable(List<String> invitees, Time startTime, int durationInMin) {
    for (String invitee : invitees) {
      for (Event event : events) {
        if (event.getInvitees().contains(invitee)) {
          if (!isTimeAvailable(startTime, durationInMin, event.getStartTime(), event.getEndTime())) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Checks if a time slot is available for the given duration.
   *
   * @param proposedStartTime the proposed start time of the event
   * @param durationInMin     the duration of the event in minutes
   * @param eventStartTime    the start time of an existing event
   * @param eventEndTime      the end time of an existing event
   * @return true if the time slot is available, false otherwise
   */
  private boolean isTimeAvailable(Time proposedStartTime, int durationInMin,
                                  Time eventStartTime, Time eventEndTime) {
    Time proposedEndTime = proposedStartTime.addMinutes(durationInMin);
    if (proposedStartTime.compareTo(eventEndTime) >= 0 || proposedEndTime.compareTo(eventStartTime) <= 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Extracts the user IDs from a list of User objects.
   *
   * @param users the list of User objects from which to extract user IDs
   * @return a list of user IDs extracted from the given list of User objects
   */
  private List<String> getUserIds(List<User> users) {
    List<String> userIds = new ArrayList<>();
    for (User user : users) {
      userIds.add(user.getUserName());
    }
    return userIds;
  }

  /**
   * Retrieves the user ID of the host of the event.
   *
   * @return the user ID of the host
   */
  public String getHostUserId() {
    for (Event event : events) {
      if (event.isHost()) {
        return event.getHostUserId();
      }
    }
    return null;
  }
}
