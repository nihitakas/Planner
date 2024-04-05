package cs3500.planner.view;

/**
 * Represents a view for the NUPlanner System, allowing users to visually see the events made with
 * a GUI.
 */
public interface PlannerView {

  /**
   * Displays the frame of the planner application.
   */
  void showFrame();

  /**
   * Adds an event to the planner.
   */
  void addEvent();

  /**
   * Removes an event from the planner.
   */
  void removeEvent();
}
