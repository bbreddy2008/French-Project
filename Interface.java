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
    //font 
    private Font font; 

    //JFrame
    private JFrame f;

    //JComponent 
    private JComponent glassPane;

    private BufferedImage logo; // logo
    private final JLabel logoPanel; // logo panel

    private final JTextField puzzleNumberField; // quiTextField
    private final JLabel puzzleLabel, quiLabel, quoiLabel, ouLabel, porquoiLabel; // all the labels being used

    private final JButton submitButton; // submit button
    private boolean isHovering = false; // see if mouse is hovering over submit button
    private String[] answers = new String[4]; // the answers given from the text fields
    
    private final JButton helpButton; // help button!
    private boolean isHoveringHelp; // hovering the help button
    private boolean inHelpMenu = false; // if the help menu should be open

    //submit puzzle id number
    private final JButton submitPuzzleButton; 
    private int puzzleNumber; //int puzzle id 
    private boolean isHoveringPuzzle = false;

    //storing correct answers as boolean
    private boolean[] correctAnswers; 

    //creating main as an object to send/recieve
    private Main mainObject = new Main(); 

    //checker for answers
    private final JLabel inCorrect; 

    //french default
    private boolean helpInFrench = true; 

    // the four drop downs (called comboboxes for sm reason)

    private final JComboBox<String> quiCombo, quoiCombo, ouCombo, pourquoiCombo; // all the dropdowns (combo boxes)

    private Dimension screenSize;
    private double scaleFactorW;
    private double scaleFactorH;

    //interface class
    public Interface() {
        f = new JFrame(); // choose to add title, i prefer not but whatever
        initializeScaling();

        try { //intializes font 
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/ELEGANT TYPEWRITER Bold.ttf")).deriveFont(Font.BOLD,40f);
        } catch (FontFormatException | IOException exception) { 
            font = new Font("Arial", Font.PLAIN, 12); //defult font/size
        }

        setLayout(null);
        try { 
            logo = ImageIO.read(this.getClass().getResource("logo.png")); // read in the logo
        } catch (IOException ex) {
            
        }
    
        //add logo to panel
        logoPanel = new JLabel(new ImageIcon(logo));
        logoPanel.setBounds(scaleWidth(200), scaleWidth(0), scaleWidth(1500), scaleHeight(250));
        add(logoPanel);
        
        //puzzle number 
        puzzleNumberField = new JTextField(); //new text field
        puzzleNumberField.setColumns(2);
        puzzleNumberField.setBounds(scaleWidth(235), scaleHeight(195), scaleWidth(100), scaleHeight(70)); //positioning and stuff
        puzzleNumberField.setFont(scaleFontSize(40));
        puzzleNumberField.setOpaque(false);
        puzzleNumberField.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); //hitbox/border
        add(puzzleNumberField);
        
        //creates puzzle label 
        puzzleLabel = new JLabel("Puzzle:");
        puzzleLabel.setFont(scaleFontSize(50));
        puzzleLabel.setBounds(scaleWidth(50),scaleHeight(180),scaleWidth(1000),scaleHeight(100));
        //adds to frame
        add(puzzleLabel);

        //Dropdown menu 
        quiCombo = styleDropdowns(); 
        quoiCombo = styleDropdowns();
        ouCombo = styleDropdowns();
        pourquoiCombo = styleDropdowns();
        createDropdowns();


        //labels for each question 
        quiLabel = new JLabel("Qui ?");
        quoiLabel = new JLabel("Quoi ?");
        ouLabel = new JLabel("Où ? ");
        porquoiLabel = new JLabel("Pourquoi ?");
        createLabels();

        //submit button
        submitButton = new JButton("Soumettre ");
        createSubmitButton();
        

        // submit puzzle number button
        submitPuzzleButton = new JButton("Soumettre");
        createPuzzleButton();

        //help button
        helpButton = new JButton("?");
        createHelpButton();

        //correct/incorrect answer
        inCorrect = new JLabel("Essayez encore");
        inCorrect.setVisible(false);
        inCorrect.setFont(font.deriveFont(Font.BOLD, 50f));
        inCorrect.setBounds(scaleWidth(250),scaleHeight(180),scaleWidth(1000),scaleHeight(100));
        inCorrect.setHorizontalAlignment(SwingConstants.CENTER);
        inCorrect.setVerticalAlignment(SwingConstants.CENTER);
        add(inCorrect, BorderLayout.CENTER);

        //window
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setPreferredSize(new Dimension(scaleWidth(500), scaleHeight(500)));
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

         //background
         g2.setColor(new Color(255, 247, 232));
         g2.fillRect(0, 0, getWidth(), getHeight());

         //roundeded text boxes
         g.setColor(new Color(232, 232, 232));
         g2.setStroke(new BasicStroke(2));

         //draw the rounded box around the inptut puzzle number 
         drawRoundedTextBox(g2, puzzleNumberField, new Color(232, 232, 232), 0);
         g2.fillRoundRect(submitPuzzleButton.getX(),submitPuzzleButton.getY(), submitPuzzleButton.getWidth(), submitPuzzleButton.getHeight(),50,50);
         
         //help button
         g2.setColor(new Color(0, 0, 0));
         g2.fillOval(helpButton.getX()-3,helpButton.getY()-13,106,106);
         if (!isHoveringHelp) {
            g2.setColor(new Color(232,232,232));
         } else {
            g2.setColor(new Color(210, 210, 210));
         }
         g2.fillOval(helpButton.getX(),helpButton.getY()-10,100,100);
         
         //submit button
         g2.setColor(Color.BLACK);
         g2.fillRoundRect(submitButton.getX()-2, submitButton.getY()-2, submitButton.getWidth()+4, submitButton.getHeight()+4, 50,50);
         if (!isHovering) { // if the mouse is hovering over it
            g2.setColor(new Color(232,232,232));
         } else {
            g2.setColor(new Color(210, 210, 210));
         }
         g2.fillRoundRect(submitButton.getX(), submitButton.getY(), submitButton.getWidth(), submitButton.getHeight(), 50,50); // submit button

         //Submit Puzzle Button
         g2.setColor(Color.BLACK);
         g2.fillRoundRect(submitPuzzleButton.getX()-2, submitPuzzleButton.getY()-2, submitPuzzleButton.getWidth()+4, submitPuzzleButton.getHeight()+4, 50,50);
         if (!isHoveringPuzzle) { // if the mouse is hovering over it
            g2.setColor(new Color(232,232,232));
         } else {
            g2.setColor(new Color(210, 210, 210));
         }
         g2.fillRoundRect(submitPuzzleButton.getX(), submitPuzzleButton.getY(), submitPuzzleButton.getWidth(), submitPuzzleButton.getHeight(), 50,50); // submit button
    }

    //method of designing text box
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

    //returns array of user answers
    public String[] userAnswers() { //get the selected choices
        String[] result = new String[4];
        result[0] = (String) quiCombo.getSelectedItem();
        result[1] = (String) quoiCombo.getSelectedItem();
        result[2] = (String) ouCombo.getSelectedItem();
        result[3] = (String) pourquoiCombo.getSelectedItem();
        return result;
    }

    //returns puzzle id number
    public int getPuzzle() { 
        return puzzleNumber;
    }

    //plays audio
    public void playSound(String filePath) { 
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

    //designs style of drop down menu 
    private JComboBox<String> styleDropdowns() {
        JComboBox<String> box = new JComboBox<String>();
    
        box.setFont(scaleFontSize(40f));
        box.setRenderer(new DefaultListCellRenderer() { //set a custom renderer to style each item in the dropdown list
            @Override //overriding the redering of the dropdown menu
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                //get the default rendering for this list cell
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setPreferredSize(new Dimension(scaleWidth(600), scaleHeight(60))); //make each dropdown item taller and use a larger font
                label.setFont(scaleFontSize(40f));
                return label;
            }
        });
    
        //override the arrow button (on the right side of the combo box) to make it taller
        box.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = super.createArrowButton();
                button.setPreferredSize(new Dimension(scaleWidth(40), scaleHeight(70))); 
                return button;
            }
        });
    
        box.setPreferredSize(new Dimension(scaleWidth(600), scaleHeight(70)));
        box.setMaximumRowCount(5); //limit dropdown height 
        box.setEnabled(false); //initially disable it until it is enabled by entering a puzzle number
        return box;
    } 

    //shuffles answer options 
    private void shuffleOptions(JComboBox<String> comboBox, String[] options) { 
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < options.length; i++) {
            list.add(options[i]);
        }
    
        Collections.shuffle(list); 
        //removes and readds all items 
        comboBox.removeAllItems(); 
        //adds dummy option 
        comboBox.addItem(""); 

        //fills answers
        for (int i = 0; i < list.size(); i++) {
            comboBox.addItem(list.get(i));
        }
    }
    
    //creating labels
    private void createLabels() {
        JLabel[] labels = {quiLabel, quoiLabel, ouLabel, porquoiLabel};
        int[] yPos = {scaleHeight(285), scaleHeight(385), scaleHeight(485), scaleHeight(585)};
        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(font.deriveFont(Font.BOLD, 50f));
            labels[i].setBounds(scaleHeight(120),yPos[i],scaleWidth(1000),scaleHeight(100));
            labels[i].setForeground(Color.GRAY);
            add(labels[i]);
        }
    }

    //creates downdowns
    private void createDropdowns() {
        JComboBox[] boxes = {quiCombo, quoiCombo, ouCombo, pourquoiCombo};
        int[] yPos = {scaleHeight(300), scaleHeight(400), scaleHeight(500), scaleHeight(600)};
        for (int i = 0; i<4; i++) {
            boxes[i].setBounds(scaleWidth(550), yPos[i], scaleWidth(1200), scaleHeight(70));
            add(boxes[i]);
        }
    }

    //creates submit button
    private void createSubmitButton() {
        submitButton.setOpaque(false);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);
        submitButton.setFont(scaleFontSize(50f));
        submitButton.setBounds(scaleWidth(1400),scaleHeight(700),scaleWidth(350),scaleHeight(100));
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> {
            //resets borders
            quiCombo.setBorder(BorderFactory.createEmptyBorder()); 
            quoiCombo.setBorder(BorderFactory.createEmptyBorder());
            ouCombo.setBorder(BorderFactory.createEmptyBorder());
            pourquoiCombo.setBorder(BorderFactory.createEmptyBorder());

            //gets selected input from dropdowns
            answers[0] = (String) quiCombo.getSelectedItem(); 
            answers[1] = (String) quoiCombo.getSelectedItem();
            answers[2] = (String) ouCombo.getSelectedItem();
            answers[3] = (String) pourquoiCombo.getSelectedItem();
        
            //gets correct answer
            correctAnswers = mainObject.submitButtonClicked(); 

            // add border colors based on correct/incorrect
            JComboBox[] boxes = {quiCombo, quoiCombo, ouCombo, pourquoiCombo};
            for (int i = 0; i<4; i++) {
                if (correctAnswers.length > i) {
                    if (correctAnswers[i]) {
                        boxes[i].setBorder(BorderFactory.createLineBorder(new Color(0, 180, 0), 6));
                    } else {
                        boxes[i].setBorder(BorderFactory.createLineBorder(new Color(200, 0, 0),6));
                    }
                }
            }

            //sends result message 
            if (correctAnswers[0] && correctAnswers[1] && correctAnswers[2]) {
                if (correctAnswers.length == 3 || (correctAnswers.length == 4 && correctAnswers[3])) {
                    //correct
                    inCorrect.setText("Bravo !");
                    inCorrect.setBounds(scaleWidth(450),scaleHeight(180),scaleWidth(1000),scaleHeight(100));
                    inCorrect.setVisible(true);
                    playSound("correct.wav");
                }
            } else {
                //incorrect
                inCorrect.setText("Essayez encore !");
                inCorrect.setBounds(scaleWidth(450),scaleHeight(180),scaleWidth(1000),scaleHeight(100));
                inCorrect.setVisible(true);
            }
        }); 
        //add mouse listener       
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() { 
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

    //creates puzzle button
    private void createPuzzleButton() {
        submitPuzzleButton.setFont(scaleFontSize(27f));
        submitPuzzleButton.setOpaque(false);
        submitPuzzleButton.setContentAreaFilled(false);
        submitPuzzleButton.setBorderPainted(false);
        submitPuzzleButton.setBounds(scaleWidth(350),scaleHeight(202),scaleWidth(230),scaleHeight(50));
        submitPuzzleButton.setFocusPainted(false);
        submitPuzzleButton.addActionListener(e -> {
            String text = puzzleNumberField.getText();
        
            try {
                puzzleNumber = Integer.parseInt(text);
            } catch (NumberFormatException ex) {
                // ignore the invalid input
                return;
            }
        
            // reset everything
            JComboBox[] boxes = {quiCombo, quoiCombo, ouCombo, pourquoiCombo};
            JLabel[] reColorLabels = {quiLabel, quoiLabel, ouLabel, porquoiLabel};
            for (int i = 0; i<4; i++) {
                boxes[i].setBorder(BorderFactory.createEmptyBorder());
                boxes[i].setEnabled(false);
                boxes[i].setForeground(Color.GRAY);
                reColorLabels[i].setForeground(Color.GRAY);
            }
        
            if (puzzleNumber <= 0 || puzzleNumber > 100) {
                return; //invalid puzzle number
            }
        
            //gets current puzzle data
            Puzzle currentPuzzle = Main.givePuzzleData(); 
        
            //shuffle the options
            shuffleOptions(quiCombo, currentPuzzle.getOptions("qui"));
            shuffleOptions(quoiCombo, currentPuzzle.getOptions("quoi"));
            shuffleOptions(ouCombo, currentPuzzle.getOptions("ou"));
        
            for (int i = 0; i<3; i++) {
                boxes[i].setEnabled(true);
                boxes[i].setForeground(Color.BLACK);
                reColorLabels[i].setForeground(Color.BLACK);
            }

            //if puzzle greater than 50, print porquoi
            if (puzzleNumber > 50) {
                shuffleOptions(pourquoiCombo, currentPuzzle.getOptions("pourquoi"));
                pourquoiCombo.setEnabled(true);
                pourquoiCombo.setForeground(Color.BLACK);
                porquoiLabel.setForeground(Color.BLACK);
            } else {
                //less than 50, dont
                pourquoiCombo.setEnabled(false);
                porquoiLabel.setForeground(Color.GRAY);
            }
        
            repaint();
        });   
        submitPuzzleButton.addMouseListener(new java.awt.event.MouseAdapter() { 
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                isHoveringPuzzle = true;
                repaint();
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                isHoveringPuzzle = false;
                repaint();
            }
        });     
        add(submitPuzzleButton);
    }

    //creates help button
    private void createHelpButton() {
        helpButton.setOpaque(false);
        helpButton.setContentAreaFilled(false);
        helpButton.setBorderPainted(false);
        helpButton.setFont(font.deriveFont(Font.BOLD, 100f));
        helpButton.setBounds(scaleWidth(1600),scaleHeight(100),scaleWidth(130),scaleHeight(95));
        helpButton.setFocusPainted(false);

        //action listener
        helpButton.addActionListener(e -> {
            inHelpMenu = !inHelpMenu; //toggle between being in/out of the menu
            glassPane.setVisible(inHelpMenu); 
            glassPane.repaint();
        });

        //add mouser listner 
        helpButton.addMouseListener(new java.awt.event.MouseAdapter() { 
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

    //create help menu
    private void createHelpMenu() {
        //help menu
        glassPane = new JComponent() { 
            @Override
            protected void paintComponent(Graphics g) {
                //initalize font
                Font font;
                try { 
                font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/ELEGANT TYPEWRITER Bold.ttf")).deriveFont(Font.BOLD,40f);
                } catch (FontFormatException | IOException exception) {
                    font = new Font("Arial", Font.PLAIN, 12);
                }

                //initalize graphics
                Graphics2D g2 = (Graphics2D) g; 
                g2.setColor(new Color(0, 0, 0, 120)); 

                //makes background out of focus
                g2.fillRect(0, 0, getWidth(), getHeight());

                //make the actual panel itself
                g2.setColor(new Color(242, 243, 244));
                g2.fillRoundRect(scaleWidth(400), scaleHeight(200), scaleWidth(1100), scaleHeight(600), 20, 20); 

                g2.setColor(new Color(0,0,0,150));
                g2.setFont(scaleFontSize(50f));
                //toggle between english and french help menu
                g2.drawString("ANGLAIS | FRANÇAIS", scaleWidth(690), scaleHeight(740)); 

                //changes langauge in menu 
                if (helpInFrench) { 
                    g2.setColor(Color.BLACK);
                    g2.setFont(scaleFontSize(50f));
                    g2.drawString("Comment jouer !", scaleWidth(750), scaleHeight(280));
                    g2.setFont(scaleFontSize(30f));
                    g2.drawString("• Choisis des réponses dans les menus déroulants\n pour Qui, Quoi, Où", scaleWidth(410), scaleHeight(360));
                    g2.drawString("• Si disponible, choisis aussi une réponse pour Pourquoi", scaleWidth(410), scaleHeight(440));
                    g2.drawString("• Clique sur \"Soumettre\" pour vérifier tes réponses", scaleWidth(410), scaleHeight(520));
                    g2.drawString("• Le bouton \"?\" ouvre ou ferme ce menu d'aide", scaleWidth(410), scaleHeight(600));                    
                } else {
                    g2.setColor(Color.BLACK);
                    g2.setFont(scaleFontSize(50f));
                    g2.drawString("Help!", scaleWidth(860), scaleHeight(280));
                    g2.setFont(scaleFontSize(30f));
                    g2.drawString("• Select answers from the dropdowns for Qui, Quoi, Où", scaleWidth(410), scaleHeight(360));
                    g2.drawString("• If available, also select Pourquoi", scaleWidth(410), scaleHeight(440));
                    g2.drawString("• Click \"Soumettre\" to check your answers", scaleWidth(410), scaleHeight(520));
                    g2.drawString("• Use the \"?\" button to view or close this help menu", scaleWidth(410), scaleHeight(600));
                }
            }
        };

        glassPane.setOpaque(false);
        glassPane.setVisible(false);
        f.setGlassPane(glassPane);

        glassPane.addMouseListener(new MouseAdapter() {
            @Override
            //detects the click on langauge button
            public void mouseClicked(MouseEvent e) { 
                int x = e.getX();
                int y = e.getY();
                Rectangle toggleBounds = new Rectangle(scaleWidth(690), scaleHeight(640), scaleWidth(690), scaleHeight(150));
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

    private void initializeScaling() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        scaleFactorW = screenSize.getWidth() / 1920.0; // Base width
        scaleFactorH = screenSize.getHeight() / 1080.0; // Base height
    }
    
    private int scaleWidth(int original) {
        return (int)(original * scaleFactorW);
    }
    
    private int scaleHeight(int original) {
        return (int)(original * scaleFactorH);
    }
    
    private Font scaleFontSize(float originalSize) {
        return font.deriveFont(Font.BOLD, (float)(originalSize * Math.min(scaleFactorW, scaleFactorH)));
    }
}
