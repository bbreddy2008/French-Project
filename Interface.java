// HOW TO USE:
// .userAnswers();
//      returns the answers after inputed into the text fields and press submit
// .puzzleNumber();
//      returns the puzzle number submitted by the user

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Interface extends JPanel {
    private Font font; // the font

    private JFrame f;
    private JComponent glassPane;

    private BufferedImage logo; // logo
    private final JLabel logoPanel; // logo panel

    private final JTextField puzzleNumberField; // quiTextField
    private final JLabel puzzleLabel; // puzzle number label
    private final JLabel quiLabel; // puzzle number label
    private final JLabel quoiLabel; // puzzle number label
    private final JLabel ouLabel; // puzzle number label
    private final JLabel porquoiLabel; // puzzle number labels

    private final JButton submitButton; // submit button
    private boolean isHovering = false; // see if mouse is hovering over submit button
    private String[] answers = new String[4]; // the answers given from the text fields
    
    private final JButton helpButton; // help button!
    private boolean isHoveringHelp; // hovering the help button
    private boolean inHelpMenu = false; // if the help menu should be open

    private final JButton submitPuzzleButton; // the button for submitting the puzzle id
    private int puzzleNumber; // the actual puzzle id

    private boolean[] correctAnswers; // the correct answers returned from main

    private Main mainObject = new Main(); // a main object to send/recieve things between the itnerface

    private final JLabel inCorrect; // if your answers are in correct or correct

    private boolean helpInFrench = true; // default to French

    // the four drop downs (called comboboxes for sm reason)

    private final JComboBox<String> quiCombo;
    private final JComboBox<String> quoiCombo;
    private final JComboBox<String> ouCombo;
    private final JComboBox<String> pourquoiCombo;

    public Interface() {
        f = new JFrame();; // choose to add title, i prefer not but whatever

        try { // initalize the font we're using
        font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/ELEGANT TYPEWRITER Bold.ttf")).deriveFont(Font.BOLD,40f);
        } catch (FontFormatException | IOException exception) {
            font = new Font("Arial", Font.PLAIN, 12);
        }

        setLayout(null);
        try { 
            logo = ImageIO.read(this.getClass().getResource("logo.png")); // read in the logo
        } catch (IOException ex) {
            // do nothing
        }
    
        //add logo
        logoPanel = new JLabel(new ImageIcon(logo));
        logoPanel.setBounds(0, 0, 1500, 250);
        add(logoPanel);
        
        // puzzle number
        puzzleNumberField = new JTextField(); // new text field
        puzzleNumberField.setColumns(2);
        puzzleNumberField.setBounds(220, 205, 70, 50); // positioning and stuff
        puzzleNumberField.setFont(font);
        puzzleNumberField.setOpaque(false);
        puzzleNumberField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // hitbox/border
        add(puzzleNumberField);

        puzzleLabel = new JLabel("Puzzle:");
        puzzleLabel.setFont(font.deriveFont(Font.BOLD, 50f));
        puzzleLabel.setBounds(50,180,1000,100);
        add(puzzleLabel);

        // dropdowns

        quiCombo = styleDropdowns(); 
        quoiCombo = styleDropdowns();
        ouCombo = styleDropdowns();
        pourquoiCombo = styleDropdowns();
        createDropdowns();


        // respective labels // quiLabel, quoiLabel, ouLabel, porquoiLabel
        quiLabel = new JLabel("Qui ?");
        quoiLabel = new JLabel("Quoi ?");
        ouLabel = new JLabel("Où ? ");
        porquoiLabel = new JLabel("Pourquoi ?");

        createLabels();

        // submit button

        submitButton = new JButton("Soumettre ");
        createSubmitButton();
        

        // submit puzzle number button
        submitPuzzleButton = new JButton("Soumettre");
        createPuzzleButton();

        // help button

        helpButton = new JButton("?");
        createHelpButton();

        // correct/incorrect answer
        inCorrect = new JLabel("Essayez encore");
        inCorrect.setVisible(false);
        inCorrect.setFont(font.deriveFont(Font.BOLD, 50f));
        inCorrect.setBounds(250,180,1000,100);
        inCorrect.setHorizontalAlignment(SwingConstants.CENTER);
        inCorrect.setVerticalAlignment(SwingConstants.CENTER);
        add(inCorrect, BorderLayout.CENTER);

        // window stuff
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setPreferredSize(new Dimension(500, 500));
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(this,BorderLayout.CENTER);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // f.setUndecorated(true); // if we want no top or not
        f.pack();
        f.setVisible(true);

        createHelpMenu();
    }
    
    public void paintComponent(Graphics g) {
         java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();

         // background

         g2.setColor(new Color(255, 247, 232));
         g2.fillRect(0, 0, getWidth(), getHeight());

         // roundeded text boxes
         g.setColor(new Color(232, 232, 232));
         g2.setStroke(new BasicStroke(2));

         // draw the roudned box around the inptut puzzle number ting
         drawRoundedTextBox(g2, puzzleNumberField, new Color(232, 232, 232), 0);
         g2.fillRoundRect(submitPuzzleButton.getX(),submitPuzzleButton.getY(), submitPuzzleButton.getWidth(), submitPuzzleButton.getHeight(),50,50);
         
         // help button
         g2.setColor(new Color(0, 0, 0));
         g2.fillOval(helpButton.getX()-3,helpButton.getY()-13,106,106);
         if (!isHoveringHelp) {
            g2.setColor(new Color(232,232,232));
         } else {
            g2.setColor(new Color(210, 210, 210));
         }
         g2.fillOval(helpButton.getX(),helpButton.getY()-10,100,100);
         
         // submit button
         g2.setColor(Color.BLACK);
         g2.fillRoundRect(submitButton.getX()-2, submitButton.getY()-2, submitButton.getWidth()+4, submitButton.getHeight()+4, 50,50);
         if (!isHovering) { // if the mouse is hovering over it
            g2.setColor(new Color(232,232,232));
         } else {
            g2.setColor(new Color(210, 210, 210));
         }
         g2.fillRoundRect(submitButton.getX(), submitButton.getY(), submitButton.getWidth(), submitButton.getHeight(), 50,50); // submit button
    }

    private void drawRoundedTextBox(Graphics2D g, JTextField textField, Color color, int isAnswerField) {
        int degrees = 50;
        int x = textField.getX();
        int y = textField.getY()-3;
        int width = textField.getWidth();
        int height = textField.getHeight();
    
        g.setColor(new Color(0 , 0, 0));
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, degrees, degrees);
        g.setColor(Color.GRAY);            
        g.drawRoundRect(x, y, width, height, degrees, degrees);
    }

    public String[] userAnswers() { // get the selected choices
        String[] result = new String[4];
        result[0] = (String) quiCombo.getSelectedItem();
        result[1] = (String) quoiCombo.getSelectedItem();
        result[2] = (String) ouCombo.getSelectedItem();
        result[3] = (String) pourquoiCombo.getSelectedItem();
        return result;
    }

    public int getPuzzle() { // take another wild guess at what this does
        return puzzleNumber;
    }

    public void playSound(String filePath) { // to play audio
        try {
            InputStream audioSrc = getClass().getResourceAsStream("/" + filePath);
            BufferedInputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JComboBox<String> styleDropdowns() {
        JComboBox<String> box = new JComboBox<String>();
    
        box.setFont(font.deriveFont(40f));
        box.setRenderer(new DefaultListCellRenderer() { // set a custom renderer to style each item in the dropdown list
            @Override // super weird process for overriding the redering of the dropdown menu
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                // get the default rendering for this list cell
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setPreferredSize(new Dimension(600, 60)); // make each dropdown item taller and use a larger font
                label.setFont(font.deriveFont(40f));
                return label;
            }
        });
    
        // override the arrow button (on the right side of the combo box) to make it taller (idk why they make it thsi complicated)
        box.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = super.createArrowButton();
                button.setPreferredSize(new Dimension(40, 70)); // make it taller!!
                return button;
            }
        });
    
        box.setPreferredSize(new Dimension(600, 70));
        box.setMaximumRowCount(5); // limit dropdown height (just in case)
        box.setEnabled(false); // initially disable it until it is enabled by entering a puzzle number
        return box;
    } 

    private void shuffleOptions(JComboBox<String> comboBox, String[] options) { // shuffle the options in the dropdown so the correct answer isn't alwyas the first
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < options.length; i++) {
            list.add(options[i]);
        }
    
        Collections.shuffle(list); // shuffle the answer chocies
    
        comboBox.removeAllItems(); // remove all the items and now readd them

        comboBox.addItem(""); // add a dummy option

        for (int i = 0; i < list.size(); i++) {
            comboBox.addItem(list.get(i));
        }
    }
    
    private void createLabels() {
        quiLabel.setFont(font.deriveFont(Font.BOLD, 50f));
        quiLabel.setBounds(220,285,1000,100);
        add(quiLabel);

        quoiLabel.setFont(font.deriveFont(Font.BOLD, 50f));
        quoiLabel.setBounds(200,385,1000,100);
        add(quoiLabel);

        ouLabel.setFont(font.deriveFont(Font.BOLD, 50f));
        ouLabel.setBounds(220,485,1000,100);
        add(ouLabel);

        porquoiLabel.setFont(font.deriveFont(Font.BOLD, 50f));
        porquoiLabel.setBounds(150,585,1000,100);
        add(porquoiLabel);

        quiLabel.setForeground(Color.GRAY);
        quoiLabel.setForeground(Color.GRAY);
        ouLabel.setForeground(Color.GRAY);
        porquoiLabel.setForeground(Color.GRAY);
    }

    private void createDropdowns() {
        quiCombo.setBounds(450, 300, 600, 70); // set its position
        add(quiCombo); // add it to the panel

        quoiCombo.setBounds(450, 400, 600, 70);
        add(quoiCombo);

        ouCombo.setBounds(450, 500, 600, 70);
        add(ouCombo);

        pourquoiCombo.setBounds(450, 600, 600, 70);
        add(pourquoiCombo);
    }

    private void createSubmitButton() {
        submitButton.setOpaque(false);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);
        submitButton.setFont(font.deriveFont(Font.BOLD, 50f));
        submitButton.setBounds(1075,650,350,100);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> {
            quiCombo.setBorder(BorderFactory.createEmptyBorder()); // reset their borders
            quoiCombo.setBorder(BorderFactory.createEmptyBorder());
            ouCombo.setBorder(BorderFactory.createEmptyBorder());
            pourquoiCombo.setBorder(BorderFactory.createEmptyBorder());


            answers[0] = (String) quiCombo.getSelectedItem(); // get the selected inputs from the dropdowns
            answers[1] = (String) quoiCombo.getSelectedItem();
            answers[2] = (String) ouCombo.getSelectedItem();
            answers[3] = (String) pourquoiCombo.getSelectedItem();
        
            correctAnswers = mainObject.submitButtonClicked(); // get the correct answers

            // add border colors based on correct/incorrect
            if (correctAnswers.length > 0) {
                if (correctAnswers[0]) {
                    quiCombo.setBorder(BorderFactory.createLineBorder(new Color(0, 180, 0), 4));
                } else {
                    quiCombo.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0), 4));
                }
            }
            if (correctAnswers.length > 1) {
                if (correctAnswers[1]) {
                    quoiCombo.setBorder(BorderFactory.createLineBorder(new Color(0, 180, 0), 4));
                } else {
                    quoiCombo.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0), 4));
                }
            }
            if (correctAnswers.length > 2) {
                if (correctAnswers[2]) {
                    ouCombo.setBorder(BorderFactory.createLineBorder(new Color(0, 180, 0), 4));
                } else {
                    ouCombo.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0), 4));
                }
            }
            if (correctAnswers.length > 3) {
                if (correctAnswers[3]) {
                    pourquoiCombo.setBorder(BorderFactory.createLineBorder(new Color(0, 180, 0), 4));
                } else {
                    pourquoiCombo.setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0), 4));
                }
            }

            // set result message
            if (correctAnswers[0] && correctAnswers[1] && correctAnswers[2]) {
                if (correctAnswers.length == 3 || (correctAnswers.length == 4 && correctAnswers[3])) {
                    inCorrect.setText("Bravo !");
                    inCorrect.setBounds(240,180,1000,100);
                    inCorrect.setVisible(true);
                    playSound("correct.wav");
                }
            } else {
                inCorrect.setText("Essayez encore !");
                inCorrect.setBounds(250,180,1000,100);
                inCorrect.setVisible(true);
            }
        });        
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() { // add mouse listener to detect mouse hovering over button
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                isHovering = true;
                repaint();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                isHovering = false;
                repaint();
            }
        });
        add(submitButton);
    }

    private void createPuzzleButton() {
        submitPuzzleButton.setFont(font.deriveFont(Font.BOLD, 27f));
        submitPuzzleButton.setOpaque(false);
        submitPuzzleButton.setContentAreaFilled(false);
        submitPuzzleButton.setBorderPainted(false);
        submitPuzzleButton.setBounds(310,202,150,50);
        submitPuzzleButton.setFocusPainted(false);
        submitPuzzleButton.addActionListener(e -> {
            String text = puzzleNumberField.getText();
        
            try {
                puzzleNumber = Integer.parseInt(text);
            } catch (NumberFormatException ex) {
                // ignore the invalid input
                return;
            }
        
            // Clear previous borders
            quiCombo.setBorder(BorderFactory.createEmptyBorder());
            quoiCombo.setBorder(BorderFactory.createEmptyBorder());
            ouCombo.setBorder(BorderFactory.createEmptyBorder());
            pourquoiCombo.setBorder(BorderFactory.createEmptyBorder());
        
            // Disable everything by default
            quiCombo.setEnabled(false);
            quoiCombo.setEnabled(false);
            ouCombo.setEnabled(false);
            pourquoiCombo.setEnabled(false);
        
            quiLabel.setForeground(Color.GRAY);
            quoiLabel.setForeground(Color.GRAY);
            ouLabel.setForeground(Color.GRAY);
            porquoiLabel.setForeground(Color.GRAY);
        
            if (puzzleNumber <= 0 || puzzleNumber > 100) {
                return; // invalid puzzle number
            }
        
            Puzzle currentPuzzle = Main.givePuzzleData(); // get current puzzle data
        
            // shuffle the options
            shuffleOptions(quiCombo, currentPuzzle.getOptions("qui"));
            shuffleOptions(quoiCombo, currentPuzzle.getOptions("quoi"));
            shuffleOptions(ouCombo, currentPuzzle.getOptions("ou"));
        
            quiCombo.setEnabled(true);
            quoiCombo.setEnabled(true);
            ouCombo.setEnabled(true);
        
            quiLabel.setForeground(Color.BLACK);
            quoiLabel.setForeground(Color.BLACK);
            ouLabel.setForeground(Color.BLACK);
        
            if (puzzleNumber > 50) {
                shuffleOptions(pourquoiCombo, currentPuzzle.getOptions("pourquoi"));
                pourquoiCombo.setEnabled(true);
                porquoiLabel.setForeground(Color.BLACK);
            } else {
                pourquoiCombo.setEnabled(false);
                porquoiLabel.setForeground(Color.GRAY);
            }
        
            repaint();
        });        
        add(submitPuzzleButton);
    }

    private void createHelpButton() {
        helpButton.setOpaque(false);
        helpButton.setContentAreaFilled(false);
        helpButton.setBorderPainted(false);
        helpButton.setFont(font.deriveFont(Font.BOLD, 100f));
        helpButton.setBounds(1300,50,100,100);
        helpButton.setFocusPainted(false);

        helpButton.addActionListener(e -> {
            inHelpMenu = !inHelpMenu; // toggle between being in/out of the menu
            glassPane.setVisible(inHelpMenu); // toggle the visiblity of the help menu
            glassPane.repaint();
        });

        helpButton.addMouseListener(new java.awt.event.MouseAdapter() { // add mouse listener to detect mouse hovering over button
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                isHoveringHelp = true;
                repaint();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                isHoveringHelp = false;
                repaint();
            }
        });
        add(helpButton);
    }

    private void createHelpMenu() {
        glassPane = new JComponent() { // help menu
            @Override
            protected void paintComponent(Graphics g) {
                Font font;
                try { // initalize the font we're using
                font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/ELEGANT TYPEWRITER Bold.ttf")).deriveFont(Font.BOLD,40f);
                } catch (FontFormatException | IOException exception) {
                    font = new Font("Arial", Font.PLAIN, 12);
                }

                Graphics2D g2 = (Graphics2D) g; // initalize the graphics ting
                g2.setColor(new Color(0, 0, 0, 120)); 
                g2.fillRect(0, 0, getWidth(), getHeight());// make background out of focus

                g2.setColor(new Color(242, 243, 244));
                g2.fillRoundRect(400, 200, 700, 500, 20, 20); // make the actual panel itself

                g2.setColor(new Color(0,0,0,150));
                g2.setFont(font.deriveFont(Font.BOLD, 50f));
                g2.drawString("ANGLAIS | FRANÇAIS", 525, 650); // toggle between english and french help menu

                if (helpInFrench) { // chagen the language in the menu
                    g2.setColor(Color.BLACK);
                    g2.setFont(font.deriveFont(Font.BOLD, 50f));
                    g2.drawString("Comment jouer !", 475, 280);
                    g2.setFont(font.deriveFont(Font.BOLD, 23f));
                    g2.drawString("• Choisis des réponses dans les menus déroulants pour Qui, Quoi, Où", 470, 360);
                    g2.drawString("• Si disponible, choisis aussi une réponse pour Pourquoi", 470, 410);
                    g2.drawString("• Clique sur \"Soumettre\" pour vérifier tes réponses", 470, 460);
                    g2.drawString("• Le bouton \"?\" ouvre ou ferme ce menu d'aide", 470, 510);                    
                } else {
                    g2.setColor(Color.BLACK);
                    g2.setFont(font.deriveFont(Font.BOLD, 50f));
                    g2.drawString("Help!", 480, 280);
                    g2.setFont(font.deriveFont(Font.BOLD, 23f));
                    g2.drawString("• Select answers from the dropdowns for Qui, Quoi, Où", 470, 360);
                    g2.drawString("• If available, also select Pourquoi", 470, 410);
                    g2.drawString("• Click \"Soumettre\" to check your answers", 470, 460);
                    g2.drawString("• Use the \"?\" button to view or close this help menu", 470, 510);
                }
            }
        };

        glassPane.setOpaque(false);
        glassPane.setVisible(false); // start hidden
        f.setGlassPane(glassPane);

        glassPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { // detect the click on the change language button
                int x = e.getX();
                int y = e.getY();
                Rectangle toggleBounds = new Rectangle(525, 620, 500, 40);
                if (toggleBounds.contains(x, y)) {
                    helpInFrench = !helpInFrench;
                } else {
                    inHelpMenu = false;
                    glassPane.setVisible(false);
                }

                repaint();
            }
        });
    }
}
