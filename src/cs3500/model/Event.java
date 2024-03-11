package cs3500.model;

import java.util.List;

public interface Event {
  String getName();
  Time getStartTime();
  Time getEndTime();
  String getLocation();
  boolean isOnline();
  User getHost();
  List<User> getInvitees();
  String toXMLString();
}
