package cs3500.planner.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
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
public class UpdatedEventScheduler extends JFrame {

  private JTextField eventNameField;
  private JTextField locationTextField;

  private JTextField durationField;


  /**
   * Constructs a new EventFrame for adding and removing events.
   */
  public UpdatedEventScheduler() {
    setTitle("Event Scheduler");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, 10, 10));

    //event name
    fieldsPanel.add(new JLabel("Event name:"));
    eventNameField = new JTextField();
    fieldsPanel.add(eventNameField);


    // location
    fieldsPanel.add(new JLabel("LOCATION:"));
    JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JComboBox<String> locationDropdown;
    locationDropdown = new JComboBox<>(new String[]{"NOT ONLINE", "ONLINE"});
    locationTextField = new JTextField(10);
    locationPanel.add(locationDropdown);
    locationPanel.add(locationTextField);
    fieldsPanel.add(locationPanel);


    // duration
    fieldsPanel.add(new JLabel("DURATION IN MINUTES:"));
    durationField = new JTextField();
    fieldsPanel.add(durationField);


    // users
    JPanel userPanel = new JPanel(new BorderLayout());
    userPanel.add(new JLabel("AVAILABLE USERS:"), BorderLayout.NORTH);
    String[] usersArray = {"Cee", "Nihita"};
    JList<String> userList = new JList<>(usersArray);
    userList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    userPanel.add(new JScrollPane(userList), BorderLayout.CENTER);

    JPanel buttonsPanel = new JPanel();
    JButton addButton = new JButton("Schedule Event");
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (validateFields()) {
          ScheduleFrame schedulerFrame = new ScheduleFrame();
          schedulerFrame.setVisible(true);
          createEvent();
        } else {
          JOptionPane.showMessageDialog(UpdatedEventScheduler.this,
                  "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    buttonsPanel.add(addButton);


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
    String duration = (String) durationField.getText();

    System.out.println("Event Name: " + eventName);
    System.out.println("Location: " + location);
    System.out.println("Duration: " +  duration);
    System.out.println("Available Users: " );


  }

  public static void main(String[] args) {
    new UpdatedEventScheduler();
  }
}