package cs3500.planner.view;

import cs3500.planner.model.NUPlanner;

/**
 * This class runs the Planner System. It is complete with the view of an event maker and the view
 * of a calendar.
 */
public final class PlannerRunner {
  /**
   * Main method for running the NU Planner system.
   * @param args any argyment needed to run the system
   */
  public static void main(String[] args) {
    NUPlanner model = new NUPlanner();

    EventFrame eventView = new EventFrame();
    eventView.setVisible(true);
    ScheduleFrame scheduleView = new ScheduleFrame();
    scheduleView.setVisible(true);
  }
}
