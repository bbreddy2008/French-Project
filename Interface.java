import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// creates thermostat gui
public class Interface extends JFrame {

    // main method: intializes thermostat gui
    public static void main(String[] args) {
        new Interface();
    }

    private JPanel panel; // creates panel
    
    private JLabel tempLabel; // creates temp label
    private JLabel messageLabel; // creates message label

    private JButton changeUnit; // creates change unit button
    private JButton increaseTemp; // increases temp button
    private JButton decreaseTemp; // decreases temp button
    private JButton increaseIncrement; // increase increment button
    private JButton decreaseIncrement; // decrease increment button

    // thermostat gui method
    public Interface() {

        // panel w/ border layout
        panel = new JPanel(new BorderLayout(10, 10)); // creates a horizantel and vertical gap
        add(panel);

        // temperature label on top
        tempLabel = new JLabel("Temperature: ", SwingConstants.CENTER); // centers label
        tempLabel.setFont(new Font("Arial", Font.BOLD, 18)); // sets font + size + bold
        tempLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // creates a border around label
        panel.add(tempLabel, BorderLayout.NORTH); // adds label to the top of panel

        // button panel w/ border layout and border
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10)); // creates a 5 by 1 layout
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // creates a border around buttonpanel

        // adds buttons to button panel
        changeUnit = new JButton("Change Unit");
        increaseTemp = new JButton("Increase Temperature by ");
        decreaseTemp = new JButton("Decrease Temperature by ");
        increaseIncrement = new JButton("Increase Increment");
        decreaseIncrement = new JButton("Decrease Increment");

        buttonPanel.add(changeUnit);
        buttonPanel.add(increaseTemp);
        buttonPanel.add(decreaseTemp);
        buttonPanel.add(increaseIncrement);
        buttonPanel.add(decreaseIncrement);

        // adds button panel to center of panel
        panel.add(buttonPanel, BorderLayout.CENTER);

        // messages on the bottom
        messageLabel = new JLabel("Action taken listed here", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // creates a border around messagelabel
        panel.add(messageLabel, BorderLayout.SOUTH);

        // action listeners added
        changeUnit.addActionListener(new ChangeUnitListener());
        increaseTemp.addActionListener(new IncreaseTempListener());
        decreaseTemp.addActionListener(new DecreaseTempListener());
        increaseIncrement.addActionListener(new IncreaseIncrementListener());
        decreaseIncrement.addActionListener(new DecreaseIncrementListener());

        // frame settings
        setTitle("Thermostat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    // updates labels on temp and message
    private void updateLabels(String message) {
        tempLabel.setText("Temperature: ");
        messageLabel.setText(message);
    }

    // change-unit actionlistener
    private class ChangeUnitListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            updateLabels("The temperature is now changed to ");
        }
    }

    // increase-temp actionlistener
    private class IncreaseTempListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            updateLabels("The temperature is now increased to ");
        }
    }

    // decrease-temp actionlistener
    private class DecreaseTempListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            updateLabels("The temperature is now decreased to ");
        }
    }

    // increase-increment actionlistener
    private class IncreaseIncrementListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            updateLabels("The increment is now increased to ");
            increaseTemp.setText("Increase Temperature by "); // changes increments on buttons
            decreaseTemp.setText("Decrease Temperature by ");
        }
    }

    // decrease-increment action listener
    private class DecreaseIncrementListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            updateLabels("The increment is now decreased to ");
            increaseTemp.setText("Increase Temperature by "); // changes increments on buttons
            decreaseTemp.setText("Decrease Temperature by ");
        }
    }
}