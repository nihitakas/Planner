package cs3500.planner.view;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import cs3500.planner.controller.XMLReader;
import cs3500.planner.model.Event;
import cs3500.planner.model.Schedule;


/**
 * Represents a frame for displaying and interacting with a weekly scheduler.
 * This is formatted to a weekly view in a calendar, the user is able to see 7 days with 24 hours,
 * every 4 hours are bolded for additional readability. The client is also able to load or save
 * schedules.
 */
public class ScheduleFrame extends JFrame implements PlannerView {

  /**
   * Constructs a new ScheduleFrame with default settings.
   */
  public ScheduleFrame() {
    XMLReader xmlReader = new XMLReader();
    setTitle("NU Planner");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 800);

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem loadItem = new JMenuItem("Load schedule");
    JMenuItem saveItem = new JMenuItem("Save schedule");
    fileMenu.add(loadItem);
    fileMenu.add(saveItem);
    menuBar.add(fileMenu);
    setJMenuBar(menuBar);

    loadItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(ScheduleFrame.this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          try {
            Schedule schedule = xmlReader.readScheduleFromFile(selectedFile);
          } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(ScheduleFrame.this,
                    "Error loading schedule from file: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });
    JPanel calendar = new JPanel() {

      private void drawEvent(Graphics2D g2d, Event event) {
        int startX = event.getStartDay().ordinal() * getWidth() / 7;
        int endX = startX + getWidth() / 7; // Each column represents a day
        int startY = event.getStartTime().getHour() * getHeight() / 24;
        int endY = event.getEndTime().getHour() * getHeight() / 24;
        g2d.setColor(Color.RED);
        g2d.fillRect(startX, startY, endX - startX, endY - startY);
      }


      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;



        g2d.setColor(Color.black);
        for (int i = 1; i < 23; i++) {
          int y = i * getHeight() / 23;
          g2d.setStroke(new BasicStroke(i % 4 == 0 ? 3 : 1));
          g2d.drawLine(0, y, getWidth(), y);
        }
        for (int i = 1; i < 7; i++) {
          int x = i * getWidth() / 7;
          g2d.drawLine(x, 0, x, getHeight());
        }

      }
    };
    add(calendar, BorderLayout.CENTER);

    JPanel buttonsPanel = new JPanel();

    String[] users = {"Cee", "Nihita"};
    JComboBox<String> userSelectionComboBox = new JComboBox<>(users);
    userSelectionComboBox.setSelectedIndex(-1);
    userSelectionComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String selectedUser = (String) userSelectionComboBox.getSelectedItem();
        System.out.println("Selected user: " + selectedUser);
      }
    });
    buttonsPanel.add(userSelectionComboBox);
    buttonsPanel.add(new JButton("Create event"));
    buttonsPanel.add(new JButton("Schedule event"));
    add(buttonsPanel, BorderLayout.SOUTH);

    setVisible(true);
  }

  /**
   * Displays the ScheduleFrame.
   */
  @Override
  public void showFrame() {
    setVisible(true);
  }

  /**
   * Adds an event to the schedule.
   */
  @Override
  public void addEvent() {
    System.out.println("Add event:");
  }

  /**
   * Removes an event from the schedule.
   */
  @Override
  public void removeEvent() {
    System.out.println("Remove event:");
  }
}
