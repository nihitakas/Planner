package cs3500.planner.controller;

import cs3500.planner.model.Event;
import cs3500.planner.model.Schedule;

public interface Strategy {

  /**
   * Finds the first possible time slot that allows all invitees and the host to be present
   * and returns an event with that block of time.
   *
   * @param schedule       the schedule to check for availability
   * @param durationInMin  the duration of the event in minutes
   * @return an event representing the first possible time slot, or null if no slot is available
   */
  Event scheduleEvent(Schedule schedule, int durationInMin);

}