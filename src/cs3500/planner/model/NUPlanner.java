package cs3500.planner.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a planner for managing users' schedules and events.
 */
public class NUPlanner implements MutablePlanner {
  private Map<String, User> users;
  private Map<String, Schedule> userSchedules;


  /**
   * Constructs a new NUPlanner.
   */
  public NUPlanner() {
    users = new HashMap<>();
    this.userSchedules = new HashMap<>();
  }

  /**
   * Adds a user's schedule to the central system.
   *
   * @param userId   the ID of the user
   * @param schedule the schedule to be added
   */
  @Override
  public void addUserSchedule(String userId, Schedule schedule) {
    userSchedules.put(userId, schedule);
  }

  /**
   * Removes a user's schedule from the central system.
   *
   * @param userId the ID of the user
   */
  @Override
  public void removeUserSchedule(String userId) {
    userSchedules.remove(userId);
  }

  /**
   * Adds an event to a user's schedule and updates the schedules of invited users.
   *
   * @param userId the ID of the user whose schedule the event is added to
   * @param event  the event to be added
   */
  @Override
  public void addEvent(String userId, Event event) {
    Schedule userSchedule = userSchedules.get(userId);
    if (userSchedule != null) {
      userSchedule.addEvent(event);
      updateInvitedUsersSchedules(userId, event.getInvitees(), event, true);
      notifyAttendees(event, "Event created: " + event.getName());
    }
  }

  /**
   * Removes an event from a user's schedule and updates the schedules of invited users.
   *
   * @param userId the ID of the user whose schedule the event is removed from
   * @param event  the event to be removed
   */
  @Override
  public void removeEvent(String userId, Event event) {
    Schedule userSchedule = userSchedules.get(userId);
    if (userSchedule != null) {
      userSchedule.removeEvent(event);
      updateInvitedUsersSchedules(userId, event.getInvitees(), event, false);
      notifyAttendees(event, "Event deleted: " + event.getName());
    }
  }

  /**
   * Modifies an existing event in a user's schedule and updates the schedules of invited users.
   *
   * @param userId   the ID of the user whose schedule the event is modified in
   * @param oldEvent the original event to be modified
   * @param newEvent the new event replacing the old event
   */
  @Override
  public void modifyEvent(String userId, Event oldEvent, Event newEvent) {
    Schedule userSchedule = userSchedules.get(userId);
    if (userSchedule != null) {
      userSchedule.modifyEvent(oldEvent, newEvent);
      updateInvitedUsersSchedules(userId, oldEvent.getInvitees(), oldEvent, false);
      updateInvitedUsersSchedules(userId, newEvent.getInvitees(), newEvent, true);
      notifyAttendees(newEvent, "Event modified: " + oldEvent.getName() + " -> "
              + newEvent.getName());
    }
  }

  @Override
  public List<User> getUsers() {
    return new ArrayList<>(users.values());
  }

  @Override
  public List<Schedule> getAllSchedules() {
    return new ArrayList<>(userSchedules.values());
  }

  @Override
  public boolean hasUser(String userId) {
    return users.containsKey(userId);
  }

  @Override
  public boolean hasEvent(Event event) {
    for (Schedule schedule : userSchedules.values()) {
      if (schedule.getEvents().contains(event)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean hasEventConflict(Event event) {
    for (Schedule schedule : userSchedules.values()) {
      if (!schedule.isAvailable(event)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Retrieves the events for a given user.
   *
   * @param userId the ID of the user
   * @return a list of events in the user's schedule
   */
  @Override
  public List<Event> getEventsForUser(String userId) {
    Schedule userSchedule = userSchedules.get(userId);
    return (userSchedule != null) ? userSchedule.getEvents() : new ArrayList<>();
  }

  /**
   * Retrieves the events at a specific time for a given user.
   *
   * @param userId the ID of the user
   * @param time   the time to check for events
   * @return a list of events occurring at the specified time in the user's schedule
   */
  @Override
  public List<Event> getEventsAtTimeForUser(String userId, Time time) {
    Schedule userSchedule = userSchedules.get(userId);
    List<Event> eventsAtTime = new ArrayList<>();
    if (userSchedule != null) {
      for (Event event : userSchedule.getEvents()) {
        if (event.getStartTime().equals(time)) {
          eventsAtTime.add(event);
        }
      }
    }
    return eventsAtTime;
  }

  /**
   * Retrieves the schedule for a given user.
   *
   * @param userId the ID of the user
   * @return the schedule of the user, or null if the user does not exist
   */
  public Schedule getScheduleForUser(String userId) {
    return userSchedules.get(userId);
  }

  /**
   * Automatically schedules an event for users based on certain criteria.
   *
   * @param event the event to be automatically scheduled
   */
  @Override
  public void automaticallyScheduleEvent(Event event) {
    List<User> invitedUsers = event.getInvitees();
    Time suggestedTime = findCorrectTime(invitedUsers, event);
    if (suggestedTime != null) {
      event.setStartTime(suggestedTime);
      addEventToSchedule(event.getHostUserId(), event);
      notifyAttendees(event, "Event automatically scheduled: " + event.getName());
    } else {
      System.out.println("Unable to automatically schedule event: " + event.getName());
    }
  }

  /**
   * Finds the optimal time for scheduling an event based on the availability of invited users.
   * This is just an example method and should be replaced with your actual implementation.
   */
  private Time findCorrectTime(List<User> invitedUsers, Event event) {
    for (User user : invitedUsers) {
      List<Event> conflictingEvents = getEventsAtTimeForUser(user.getUserName(), event.getStartTime());
      if (conflictingEvents.isEmpty()) {
        return event.getStartTime();
      }
    }
    return null;
  }

  /**
   * Adds an event to the schedule of the specified user.
   */
  private void addEventToSchedule(String userId, Event event) {
    Schedule userSchedule = userSchedules.get(userId);
    if (userSchedule != null) {
      userSchedule.addEvent(event);
    }
  }

  /**
   * Updates the schedules of invited users when an event is added or removed from
   * a host's schedule.
   * This method ensures that invited users' schedules are synchronized with
   * the host's schedule.
   */
  private void updateInvitedUsersSchedules(String hostUserId, List<User> invitedUsers, Event event,
                                           boolean addEvent) {
    for (User invitedUser : invitedUsers) {
      String invitedUserName = invitedUser.getUserName();
      if (!invitedUserName.equals(hostUserId)) {
        Schedule invitedUserSchedule = userSchedules.get(invitedUserName);
        if (invitedUserSchedule != null) {
          if (addEvent) {
            invitedUserSchedule.addEvent(event);
          } else {
            invitedUserSchedule.removeEvent(event);
          }
        }
      }
    }
  }

  /**
   * Notifies attendees of an event about a change in the event's status.
   * This method sends a notification message to all users invited to the event.
   */
  private void notifyAttendees(Event event, String message) {
    for (User attendee : event.getInvitees()) {
      attendee.notify();
    }
  }

  /**
   * Adds a schedule to the planner.
   *
   * @param schedule the schedule to be added
   */
  public void addSchedule(Schedule schedule) {
    String userId = schedule.getUserId();
    if (!userSchedules.containsKey(userId)) {
      userSchedules.put(userId, schedule);
    } else {
      // Handle case where schedule for this user already exists
      // You may want to throw an exception or handle it according to your requirements
      throw new IllegalStateException("Schedule for user" + userId + "already exists.");
    }
  }

  /**
   * Modifies an existing schedule in the planner.
   *
   * @param oldSchedule the original schedule to be modified
   * @param newSchedule the new schedule replacing the old schedule
   */
  public void modifySchedule(Schedule oldSchedule, Schedule newSchedule) {
    String userId = oldSchedule.getUserId();
    if (userSchedules.containsKey(userId)) {
      // Replace the old schedule with the new one
      userSchedules.put(userId, newSchedule);
    } else {
      // Handle case where the old schedule does not exist
      // You may want to throw an exception or handle it according to your requirements
      throw new IllegalStateException("Schedule for user " + userId + " does not exist.");
    }
  }
}