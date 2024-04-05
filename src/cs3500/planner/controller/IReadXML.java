package cs3500.planner.controller;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import cs3500.planner.model.Schedule;

/**
 * An interface for reading schedules from XML files and creating Schedules from the input.
 */

public interface IReadXML {

  /**
   * Reads schedules from XML files.
   *
   * @param directoryPath the path to the directory containing XML files
   * @return a list of schedules read from the XML files
   */
  List<Schedule> readSchedulesFromDirectory(String directoryPath);

  /**
   * Reads a schedule from an XML file.
   *
   * @param file the XML file
   * @return the schedule parsed from the XML file
   * @throws IOException                  if an I/O error occurs
   * @throws ParserConfigurationException if a DocumentBuilder cannot be created
   * @throws SAXException                 if any parse errors occur
   */
  Schedule readScheduleFromFile(File file) throws IOException, ParserConfigurationException,
          SAXException;
}