package cs3500.planner.controller;

import java.util.ArrayList;
import cs3500.planner.model.Event;
import cs3500.planner.model.Schedule;
import cs3500.planner.model.Time;
import cs3500.planner.model.User;

public class AnyTime implements Strategy {

  /**
   * Finds the first possible time (starting Sunday at 00:00) that allows all invitees
   * and the host to be present and returns an event with that block of time.
   *
   * @param schedule       the schedule to check for availability
   * @param durationInMin  the duration of the event in minutes
   * @return an event representing the first possible time slot, or null if no slot is available
   */
  @Override
  public Event scheduleEvent(Schedule schedule, int durationInMin) {
    Time currentTime = new Time(0, 0); // Sunday, 00:00
    Time endTime = new Time(23, 59); // Saturday, 23:59

    while (currentTime.compareTo(endTime) <= 0) {
      if (schedule.isAvailableForAll(durationInMin, currentTime)) {
        return new Event(null, currentTime, currentTime.addMinutes(durationInMin),
                null, false, true, new ArrayList<User>(), new ArrayList<User>(),
                currentTime.getDayOfWeek(), currentTime.getDayOfWeek());
      }
      currentTime = currentTime.nextMinute();
    }
    return null;
  }
}
