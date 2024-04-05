package cs3500.planner.model;

/**
 * Enums for the days of the week.
 */
public enum DaysOfTheWeek {
  SUNDAY,
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY;


  /**
   * Get the next day of the week.
   *
   * @return the next day of the week
   */
  public DaysOfTheWeek nextDay() {
    int nextOrdinal = (this.ordinal() + 1) % 7;
    return DaysOfTheWeek.values()[nextOrdinal];
  }
}