package cs3500.planner.controller;

import java.util.ArrayList;

import cs3500.planner.model.DaysOfTheWeek;
import cs3500.planner.model.Event;
import cs3500.planner.model.Schedule;
import cs3500.planner.model.Time;
import cs3500.planner.model.User;

public class WorkHours implements Strategy {

  /**
   * Finds the first possible time from Monday to Friday (inclusive) between the hours of 0900
   * and 1700 (inclusive) where all invitees and the host can attend the event and returns an
   * event with that block of time.
   *
   * @param schedule       the schedule to check for availability
   * @param durationInMin  the duration of the event in minutes
   * @return an event representing the first possible time slot during work hours,
   *         or null if no slot is available
   */
  @Override
  public Event scheduleEvent(Schedule schedule, int durationInMin) {
    Time startTime = new Time(9, 0);
    Time endTime = new Time(17, 0);

    DaysOfTheWeek currentDay = DaysOfTheWeek.MONDAY;

    while (currentDay != DaysOfTheWeek.SATURDAY) {
      if (currentDay != DaysOfTheWeek.SATURDAY && currentDay != DaysOfTheWeek.SUNDAY) {
        Time currentTime = new Time(startTime.getHour(), startTime.getMinute());
        while (currentTime.compareTo(endTime) <= 0) {
          if (schedule.isAvailableForAll(durationInMin, currentTime)) {
            return new Event(null, currentTime, currentTime.addMinutes(durationInMin),
                    null, false, true, new ArrayList<User>(), new ArrayList<User>(),
                    currentDay, currentDay);
          }
          currentTime = currentTime.addMinutes(30);
        }
      }
      currentDay = currentDay.nextDay();
    }
    return null;
  }
}
