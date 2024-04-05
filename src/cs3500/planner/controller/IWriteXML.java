package cs3500.planner.controller;

import java.util.List;

import cs3500.planner.model.Schedule;


/**
 * An interface for writing schedules to XML files.
 * Implementing classes should provide functionality to write a list of schedules
 * to XML files in a specified directory.
 */
public interface IWriteXML {

  /**
   * Writes schedules to XML files
   * @param schedules      the list of schedules to be written
   * @param directoryPath  the directory path where XML files will be written
   */
  void writeSchedulesToXML(List<Schedule> schedules, String directoryPath);

}