package cs3500.planner.model;

/**
 * Represents a time in hours and minutes in 24-hour military format.
 */

public class Time implements Comparable<Time> {
  private int hour;
  private int minute;


/**
   * Constructs a time object with the given hour and minute.
   * @param hour   the hour component of the time (24-hour format)
   * @param minute the minute component of the time
   */

public Time(int hour, int minute) {
  this.hour = hour;
  this.minute = minute;
}


  /**
   * Retrieves the hour component of the time.
   *
   * @return the hour component of the time
   */

  public int getHour() {
    return hour;
  }


/**
   * Sets the hour component of the time.
   *
   * @param hour the hour component to set (24-hour format)
   */

  public void setHour(int hour) {
    this.hour = hour;
  }


/**
   * Retrieves the minute component of the time.
   *
   * @return the minute component of the time
   */

  public int getMinute() {
    return minute;
  }


/**
   * Sets the minute component of the time.
   *
   * @param minute the minute component to set
   */

  public void setMinute(int minute) {
    this.minute = minute;
  }


/**
   * Compares this time with another time.
   *
   * @param other the other time to compare to
   * @return a negative integer if this time is before the other time,
   *         zero if they are equal, or a positive integer if this time is after the other time
   */

  @Override
  public int compareTo(Time other) {
    if (this.hour != other.hour) {
      return Integer.compare(this.hour, other.hour);
    } else {
      return Integer.compare(this.minute, other.minute);
    }
  }


/**
   * Returns a string representation of the time in the format "HHMM".
   *
   * @return a string representation of the time
   */

  @Override
  public String toString() {
    return String.format("%02d%02d", hour, minute);
  }


  /**
   * Retrieves the day of the week for this time.
   * @return the day of the week
   */
  public DaysOfTheWeek getDayOfWeek() {
    int totalMinutes = hour * 60 + minute;
    int dayIndex = (totalMinutes / (24 * 60)) % 7; // Calculate the day index based on total minutes
    return DaysOfTheWeek.values()[dayIndex];
  }

  /**
   * Adds minutes to the current time and returns a new Time object.
   *
   * @param minutesToAdd the number of minutes to add
   * @return a new Time object with the added minutes
   */

  public Time addMinutes(int minutesToAdd) {
    int totalMinutes = this.hour * 60 + this.minute + minutesToAdd;
    int newHour = totalMinutes / 60;
    int newMinute = totalMinutes % 60;

    newHour = newHour % 24;

    return new Time(newHour, newMinute);
  }

  /**
   * Increments the current time by one minute.
   * @return a new Time object representing the time one minute ahead
   */
  public Time nextMinute() {
    int newHour = getHour();
    int newMinute = getMinute() + 1;
    if (newMinute >= 60) {
      newHour += 1;
      newMinute = 0;
      if (newHour >= 24) {
        newHour = 0;
      }
    }
    return new Time(newHour, newMinute);
  }
}


