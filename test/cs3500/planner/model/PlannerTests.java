package cs3500.planner.model;


import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * This class ensures the testing and the functionality of the NUPlanner system and its methods.
 */
public class PlannerTests {

  private Schedule schedule;

  /**
   * Creates a new schedule for testing purposes.
   */
  @Before
  public void testMakeSchedules() {
    List<Event> events = new ArrayList<>();
    this.schedule = new Schedule("userOne", events);
  }

  /**
   * Checks that the schedule is able to get the userID of the schedule owner.
   */
  @Test
  public void testUserID() {
    assertEquals("userOne", schedule.getUserId());
  }

  /**
   * Tests that events can be added to a schedule after its creation.
   */
  @Test
  public void testAddEvent() {
    NUPlanner planner = new NUPlanner();
    Schedule schedule = new Schedule("John", new ArrayList<>());
    planner.addUserSchedule("John", schedule);

    Event lecture = new Event("OOD Lecture", new Time(9, 50),
            new Time(11, 30), "Conference Room", false,
            true, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.MONDAY, DaysOfTheWeek.MONDAY);
    planner.addEvent("John", lecture);

    List<Event> events = planner.getEventsForUser("John");
    assertTrue(events.contains(lecture));
  }

  /**
   * Creates that only a host can modify an event.
   */
  @Test
  public void testModifyEventWithHost() {
    NUPlanner planner = new NUPlanner();
    Schedule schedule = new Schedule("Mary", new ArrayList<>());
    planner.addUserSchedule("Mary", schedule);

    Event oldEvent = new Event("Work on project", new Time(13, 0),
            new Time(13, 30), "Amy's house", false,
            true, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.FRIDAY, DaysOfTheWeek.FRIDAY);
    planner.addEvent("Mary", oldEvent);

    Event newEvent = new Event("Work on project", new Time(17, 0),
            new Time(18, 40), "My house", false,
            true, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.SUNDAY, DaysOfTheWeek.SUNDAY);
    planner.modifyEvent("Mary", oldEvent, newEvent);

    List<Event> events = planner.getEventsForUser("Mary");
    assertFalse(events.contains(oldEvent));
    assertTrue(events.contains(newEvent));
  }

  /**
   * Checks that individual events can be deleted from a schedule.
   */
  @Test
  public void testDeleteOneOfTwoEvents() {
    NUPlanner planner = new NUPlanner();
    Schedule schedule = new Schedule("Mary", new ArrayList<>());
    planner.addUserSchedule("Mary", schedule);

    Event project = new Event("Work on project", new Time(13, 0),
            new Time(15, 30), "Amy's house", false,
            true, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.FRIDAY, DaysOfTheWeek.FRIDAY);
    planner.addEvent("user1", project);
    planner.removeEvent("Mary", project);

    Event newEvent = new Event("Work on project", new Time(14, 0),
            new Time(16, 40), "My house", false,
            true, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.SATURDAY, DaysOfTheWeek.SATURDAY);
    planner.addEvent("Mary", newEvent);

    List<Event> events = planner.getEventsForUser("Mary");
    assertFalse(events.contains(project));
    assertTrue(events.contains(newEvent));
  }

  /**
   * Checks that only a host can modify events. Throws an Illegal Argument Exception when someone
   * who is not a host tries to modify an event.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testModifyEventWhenNotAHost() {
    NUPlanner planner = new NUPlanner();
    Schedule schedule = new Schedule("Jack", new ArrayList<>());
    planner.addUserSchedule("Jack", schedule);

    Event event = new Event("Lunch with Roommate", new Time(12, 0),
            new Time(13, 30), "Steast", false,
            false, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.WEDNESDAY, DaysOfTheWeek.WEDNESDAY);
    planner.addEvent("Jack", event);

    Event newEvent = new Event("Breakfast with Roommate", new Time(9, 0),
            new Time(10, 0), "Conference Room", false,
            false, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.WEDNESDAY, DaysOfTheWeek.WEDNESDAY);
    planner.modifyEvent("Jack", event, newEvent);
  }

  /**
   * Checks that only a host can delete events. Throws an Illegal Argument Exception when someone
   * who is not a host tries to delete an event.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testDeleteEventWhenNotAHost() {
    NUPlanner jackPlanner = new NUPlanner();
    Schedule schedule = new Schedule("Jack", new ArrayList<>());
    jackPlanner.addUserSchedule("Jack", schedule);

    NUPlanner roommatePlanner = new NUPlanner();
    Schedule roommateSchedule = new Schedule("Roommate", new ArrayList<>());
    roommatePlanner.addUserSchedule("Roommate", roommateSchedule);

    Event lunch = new Event("Lunch with Roommate", new Time(12, 0),
            new Time(13, 30), "Steast", false,
            false, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.WEDNESDAY, DaysOfTheWeek.WEDNESDAY);
    jackPlanner.addEvent("Jack", lunch);

    roommatePlanner.removeEvent("Roommate", lunch);
  }


  /**
   * Tests that a whole event can be deleted.
   */
  @Test
  public void testDeleteEvent() {
    NUPlanner planner = new NUPlanner();
    Schedule schedule = new Schedule("Joe", new ArrayList<>());
    planner.addUserSchedule("Joe", schedule);

    Event event = new Event("Zoom interview", new Time(14, 0),
            new Time(14, 30), "Dorm", true,
            true, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.TUESDAY, DaysOfTheWeek.TUESDAY);
    planner.addEvent("Joe", event);

    planner.removeEvent("Joe", event);
    List<Event> events = planner.getEventsForUser("Joe");
    assertFalse(events.contains(event));
  }

  /**
   * Tests that two events can be added when they end/start directly after each other.
   */
  @Test
  public void testAddEventsOneEndsOtherStarts() {
    Schedule schedule = new Schedule("Jenny", new ArrayList<>());

    Event event1 = new Event("Interview", new Time(12, 0),
            new Time(13, 0), "Zoom",
            true, true, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.THURSDAY, DaysOfTheWeek.THURSDAY);
    schedule.addEvent(event1);

    Event event2 = new Event("Lunch", new Time(13, 0),
            new Time(14, 0), "Newbury Street",
            true, true, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.THURSDAY, DaysOfTheWeek.THURSDAY);
    schedule.addEvent(event2);
    assertTrue(schedule.getEvents().contains(event2));
  }

  /**
   * Tests the functionality of the isAvailable method for scheduling an event.
   * This tests makes sure that an intersecting or an interfering event cannot be scheduled
   * while another one is ongoing.
   */
  @Test
  public void testIsAvailableConflictWithExistingEvent() {
    Event existingEvent = new Event("Event1", new Time(13, 0),
            new Time(14, 0),
            "Park", true, true, new ArrayList<>(),
            new ArrayList<>(), DaysOfTheWeek.MONDAY,
            DaysOfTheWeek.MONDAY);
    List<Event> events = new ArrayList<>();
    events.add(existingEvent);
    Schedule schedule = new Schedule("userId", events);

    Event newEvent = new Event("New Event", new Time(13, 15),
            new Time(14, 15),
            "Park", true, true, new ArrayList<>(),
            new ArrayList<>(), DaysOfTheWeek.MONDAY,
            DaysOfTheWeek.MONDAY);

    assertFalse(schedule.isAvailable(newEvent));
  }

  /**
   * Tests that a user's schedule can be added into the planner.
   */
  @Test
  public void addUserSchedule() {
    NUPlanner planner = new NUPlanner();
    Schedule newUserSchedule = new Schedule("Jessie", new ArrayList<>());
    planner.addUserSchedule("Jessie", newUserSchedule);
    assertEquals(newUserSchedule, planner.getScheduleForUser("Jessie"));
  }

  /**
   * Tests that a user's entire schedule can be removed.
   */
  @Test
  public void testRemoveUserSchedule() {
    NUPlanner planner = new NUPlanner();
    Schedule schedule = new Schedule("Hello", new ArrayList<>());
    planner.addUserSchedule("Hello", schedule);
    planner.removeUserSchedule("Hello");
    assertNull(planner.getScheduleForUser("Hello"));
  }

  /**
   * Tests that the start day and end day can be retrieved.
   */
  @Test
  public void testGetStartDayAndEndDay() {
    Event event = new Event("Tutoring", new Time(4, 0),
            new Time(6, 0), "Snell", false, true, new ArrayList<>(),
            new ArrayList<>(), DaysOfTheWeek.MONDAY, DaysOfTheWeek.MONDAY);

    assertEquals(DaysOfTheWeek.MONDAY, event.getStartDay());
    assertEquals(DaysOfTheWeek.MONDAY, event.getEndDay());
  }

  /**
   * Tests that the start and end time can be retrieved.
   */
  @Test
  public void testGetStartTimeAndEndTime() {
    Time startTime = new Time(4, 0);
    Time endTime = new Time(6, 0);
    Event meeting = new Event("Meeting", startTime, endTime, "Snell",
            false, true, new ArrayList<>(), new ArrayList<>(),
            DaysOfTheWeek.MONDAY, DaysOfTheWeek.MONDAY);

    assertEquals(startTime, meeting.getStartTime());
    assertEquals(endTime, meeting.getEndTime());
  }


  @Test
  public void testUserForInviteesIsDeletedWhenHostDeletesIt() {
    NUPlanner hostPlanner = new NUPlanner();
    Schedule hostSchedule = new Schedule("Host", new ArrayList<>());
    hostPlanner.addUserSchedule("Host", hostSchedule);

    NUPlanner user1Planner = new NUPlanner();
    Schedule user1Schedule = new Schedule("user1", new ArrayList<>());
    User user1 = new User("user1", user1Schedule);
    user1Planner.addUserSchedule("user1", user1Schedule);

    NUPlanner user2Planner = new NUPlanner();
    Schedule user2Schedule = new Schedule("user2", new ArrayList<>());
    User user2 = new User("user2", user2Schedule);
    user2Planner.addUserSchedule("user2", user2Schedule);

    NUPlanner user3Planner = new NUPlanner();
    Schedule user3Schedule = new Schedule("user3", new ArrayList<>());
    User user3 = new User("user3", user3Schedule);
    user3Planner.addUserSchedule("user3", user3Schedule);

    Event event = new Event("Team Meeting", new Time(9, 0),
            new Time(10, 0),
            "ISEC Conference Room, 4th floor", false, true, new ArrayList<>(),
            Arrays.asList(user1, user2, user3), // Invite users 1, 2, and 3
            DaysOfTheWeek.MONDAY, DaysOfTheWeek.MONDAY);
    hostPlanner.addEvent("Host", event);

    assertTrue(user1Planner.getEventsForUser("user1").contains(event));
    assertTrue(user2Planner.getEventsForUser("user2").contains(event));
    assertTrue(user3Planner.getEventsForUser("user3").contains(event));

    hostPlanner.removeEvent("Host", event);

    assertFalse(user1Planner.getEventsForUser("user1").contains(event));
    assertFalse(user2Planner.getEventsForUser("user2").contains(event));
    assertFalse(user3Planner.getEventsForUser("user3").contains(event));
  }

}