package cs3500.planner.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;


/**
 * Represents a frame for adding and removing events in the NUPlanner system.
 * The user is able to make an event by adding a name, location, starting and ending day + time,
 * and invited users. All fields are required to be filled out in order to create an event.
 */
public class EventFrame extends JFrame {

  private JTextField eventNameField;
  private JTextField locationTextField;
  private JComboBox<String> startingDayDropdown;
  private JTextField startingTimeField;
  private JComboBox<String> endingDayDropdown;
  private JTextField endingTimeField;
  private JCheckBox hostCheckBox;

  /**
   * Constructs a new EventFrame for adding and removing events.
   */
  public EventFrame() {
    setTitle("Event Scheduler");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, 10, 10));

    fieldsPanel.add(new JLabel("Event name:"));
    eventNameField = new JTextField();
    fieldsPanel.add(eventNameField);

    // are you the host?
    fieldsPanel.add(new JLabel("HOST:"));
    hostCheckBox = new JCheckBox("I am the host");
    fieldsPanel.add(hostCheckBox);

    // location
    fieldsPanel.add(new JLabel("LOCATION:"));
    JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JComboBox<String> locationDropdown;
    locationDropdown = new JComboBox<>(new String[]{"IRL", "ONLINE"});
    locationTextField = new JTextField(10);
    locationPanel.add(locationDropdown);
    locationPanel.add(locationTextField);
    fieldsPanel.add(locationPanel);

    // start day
    fieldsPanel.add(new JLabel("STARTING DAY:"));
    //days are letters because we were having issues with Java Style!!!!!
    startingDayDropdown = new JComboBox<>(new String[]{"Su", "M", "T", "W", "Th", "F", "S"});
    fieldsPanel.add(startingDayDropdown);

    // start time
    fieldsPanel.add(new JLabel("STARTING TIME:"));
    startingTimeField = new JTextField();
    fieldsPanel.add(startingTimeField);

    // end day
    fieldsPanel.add(new JLabel("ENDING DAY:"));
    //days are letters because we were having issues with Java Style!!!!!
    endingDayDropdown = new JComboBox<>(new String[]{"Su", "M", "T", "W", "Th", "F", "S"});
    fieldsPanel.add(endingDayDropdown);

    // end time
    fieldsPanel.add(new JLabel("ENDING TIME:"));
    endingTimeField = new JTextField();
    fieldsPanel.add(endingTimeField);

    // users
    JPanel userPanel = new JPanel(new BorderLayout());
    userPanel.add(new JLabel("AVAILABLE USERS:"), BorderLayout.NORTH);
    String[] usersArray = {"Cee", "Nihita"};
    JList<String> userList = new JList<>(usersArray);
    userList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    userPanel.add(new JScrollPane(userList), BorderLayout.CENTER);

    JPanel buttonsPanel = new JPanel();
    JButton addButton = new JButton("Add Event");
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (validateFields()) {
          createEvent();
        } else {
          JOptionPane.showMessageDialog(EventFrame.this,
                  "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    buttonsPanel.add(addButton);

    JButton removeButton = new JButton("Remove Event");
    removeButton.addActionListener(new ActionListener() {

      /**
       * The Action performed when a button is clicked.
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        if (validateFields()) {
          int option = JOptionPane.showConfirmDialog(EventFrame.this,
                  "Are you sure you want to remove this event?", "Confirm Removal",
                  JOptionPane.YES_NO_OPTION);
          if (option == JOptionPane.YES_OPTION) {
            removeEvent();
          }
        } else {
          JOptionPane.showMessageDialog(EventFrame.this,
                  "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    buttonsPanel.add(removeButton);

    mainPanel.add(fieldsPanel);
    mainPanel.add(userPanel);
    mainPanel.add(buttonsPanel);

    getContentPane().add(mainPanel);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private boolean validateFields() {
    return true;
  }

  //creates the event for the console to print out
  private void createEvent() {
    String eventName = eventNameField.getText();
    String location = locationTextField.getText();
    String startingDay = (String) startingDayDropdown.getSelectedItem();
    String startingTime = startingTimeField.getText();
    String endingDay = (String) endingDayDropdown.getSelectedItem();
    String endingTime = endingTimeField.getText();
    String host = hostCheckBox.isSelected() ? "Yes" : "No";

    System.out.println("Event Name: " + eventName);
    System.out.println("Location: " + location);
    System.out.println("Starting Day: " + startingDay);
    System.out.println("Starting Time: " + startingTime);
    System.out.println("Ending Day: " + endingDay);
    System.out.println("Ending Time: " + endingTime);
    System.out.println("Host: " + host);
  }


  private void removeEvent() {
    String eventIdToRemove = JOptionPane.showInputDialog(this,
            "Enter Event ID to Remove:");
    if (eventIdToRemove != null && !eventIdToRemove.isEmpty()) {
      System.out.println("Removing event with ID: " + eventIdToRemove);

      JOptionPane.showMessageDialog(this, "Event removed successfully.");
    } else {
      JOptionPane.showMessageDialog(this, "Please enter a valid Event ID.",
              "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

}