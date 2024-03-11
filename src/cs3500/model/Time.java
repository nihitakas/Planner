package cs3500.model;

public class Time implements Comparable<Time> {
  private int hour;
  private int minute;

  public Time(int hour, int minute) {
    this.hour = hour;
    this.minute = minute;
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  @Override
  public int compareTo(Time other) {
    if (this.hour != other.hour) {
      return Integer.compare(this.hour, other.hour);
    } else {
      return Integer.compare(this.minute, other.minute);
    }
  }

  @Override
  public String toString() {
    return String.format("%02d:%02d", hour, minute);
  }
}
