// HOW TO USE:
// .userAnswers();
//      returns the answers after inputed into the text fields and press submit
// .puzzleNumber();
//      returns the puzzle number submitted by the user

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Interface extends JPanel {
    private JFrame f;
    private JComponent glassPane;

    private BufferedImage logo; // logo
    private final JLabel logoPanel; // logo panel

    private JTextField puzzleNumberField; // quiTextField
    private final JLabel puzzleLabel; // puzzle number label

    private JTextField quiInputField; // quiTextField
    private final JLabel quiLabel; // puzzle number label

    private JTextField quoiInputField; // quiTextField
    private final JLabel quoiLabel; // puzzle number label

    private JTextField ouInputField; // quiTextField
    private final JLabel ouLabel; // puzzle number label

    private JTextField pourquoiInputField; // quiTextField
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

    private Main myMain = new Main(); // a main object to send/recieve things between the itnerface

    private boolean resetFields; // to reset the fields

    private JLabel inCorrect; // if your answers are in correct or correct

    private boolean helpInFrench = true; // default to French


    public Interface() {
        f = new JFrame();; // choose to add title, i prefer not but whatever

        Font font;
        try { // initalize the font we're using
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Gluten-VariableFont_slnt,wght.ttf")).deriveFont(Font.BOLD,40f);
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

        // quiInputField, quoiInputField, ouInputField, pourquoiInputField
        // all text fields
        quiInputField = new JTextField(); // new textfield
        quiInputField.setColumns(2); // set its column
        quiInputField.setFont(font); // set the font to the font we chose
        quiInputField.setBounds(450, 300, 600, 60); // position and size it
        quiInputField.setOpaque(false); // make the background opaque (needed for the rounded corners)
        quiInputField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // needed for the rounded corners
        add(quiInputField); // finally, add it to the panel
        
        quoiInputField = new JTextField();
        quoiInputField.setColumns(2);
        quoiInputField.setFont(font);
        quoiInputField.setBounds(450, 400, 600, 60);
        quoiInputField.setOpaque(false);
        quoiInputField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(quoiInputField);

        ouInputField = new JTextField();
        ouInputField.setColumns(2);
        ouInputField.setFont(font);
        ouInputField.setBounds(450, 500, 600, 60);
        ouInputField.setOpaque(false);
        ouInputField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(ouInputField);

        pourquoiInputField = new JTextField();
        pourquoiInputField.setColumns(2);
        pourquoiInputField.setFont(font);
        pourquoiInputField.setBounds(450, 600, 600, 60);
        pourquoiInputField.setOpaque(false);
        pourquoiInputField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pourquoiInputField.setEditable(false);
        pourquoiInputField.setFocusable(false);
        add(pourquoiInputField);


        // respective labels // quiLabel, quoiLabel, ouLabel, porquoiLabel
        quiLabel = new JLabel("Qui?");
        quiLabel.setFont(font.deriveFont(Font.BOLD, 50f));
        quiLabel.setBounds(220,285,1000,100);
        add(quiLabel);

        quoiLabel = new JLabel("Quoi?");
        quoiLabel.setFont(font.deriveFont(Font.BOLD, 50f));
        quoiLabel.setBounds(200,385,1000,100);
        add(quoiLabel);

        ouLabel = new JLabel("Oú?");
        ouLabel.setFont(font.deriveFont(Font.BOLD, 50f));
        ouLabel.setBounds(220,485,1000,100);
        add(ouLabel);

        porquoiLabel = new JLabel("Pourquoi?");
        porquoiLabel.setFont(font.deriveFont(Font.BOLD, 50f));
        porquoiLabel.setBounds(150,585,1000,100);
        add(porquoiLabel);
        porquoiLabel.setForeground(new Color(porquoiLabel.getForeground().getRed(), porquoiLabel.getForeground().getGreen(), porquoiLabel.getForeground().getBlue(), (int)(0.5f * 255)));

        // submit button

        submitButton = new JButton("Soumettre ");
        submitButton.setOpaque(false);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);
        submitButton.setFont(font.deriveFont(Font.BOLD, 50f));
        submitButton.setBounds(1100,650,350,100);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> { // action listerner for the submit button
            resetFields = false;
           answers[0] = quiInputField.getText(); // set the answers to the answers in the text boxes
           answers[1] = quoiInputField.getText();
           answers[2] = ouInputField.getText();
           answers[3] = pourquoiInputField.getText();
           correctAnswers = myMain.submitButtonClicked(); // get the correct answers from main
            repaint(); // repaint the gren and red if right or wrong
            if (correctAnswers[0] && correctAnswers[1] && correctAnswers[2]) {
                if (correctAnswers.length == 3) {
                    inCorrect.setText("Bravo!");
                    inCorrect.setBounds(240,180,1000,100);
                    inCorrect.setVisible(true);
                    playSound("correct.wav");   
                } else if (correctAnswers.length == 4) {
                    if (correctAnswers[3]) {
                        inCorrect.setText("Bravo!");
                        inCorrect.setBounds(240,180,1000,100);
                        inCorrect.setVisible(true);
                        playSound("correct.wav");   
                    }
                }
            } else { // if the answers are not correct...
                inCorrect.setText("Essayez encore!");
                inCorrect.setBounds(250,180,1000,100);
                inCorrect.setVisible(true);
            }
        });
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() { // add muouse listener to detect mouse hovering over button
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

        // submit puzzle number button
        submitPuzzleButton = new JButton("Soumettre");
        submitPuzzleButton.setFont(font.deriveFont(Font.BOLD, 21f));
        submitPuzzleButton.setOpaque(false);
        submitPuzzleButton.setContentAreaFilled(false);
        submitPuzzleButton.setBorderPainted(false);
        submitPuzzleButton.setBounds(310,202,150,50);
        submitPuzzleButton.setFocusPainted(false);
        submitPuzzleButton.addActionListener(e -> { // actiona listerner for submitting a puzzle
            puzzleNumber = Integer.valueOf(puzzleNumberField.getText());
            quiInputField.setText("");
            quoiInputField.setText("");
            ouInputField.setText("");
            pourquoiInputField.setText("");
            resetFields = true;
            System.out.println("id: "+puzzleNumber);
            if (puzzleNumber < 50) { // if the puzzle number is less than 50 disable the porquoi option
                porquoiLabel.setForeground(new Color(porquoiLabel.getForeground().getRed(), porquoiLabel.getForeground().getGreen(), porquoiLabel.getForeground().getBlue(), (int)(0.5f * 255)));
                pourquoiInputField.setEditable(false);
                pourquoiInputField.setFocusable(false);
                pourquoiInputField.setText("");
            } else {
                porquoiLabel.setForeground(new Color(porquoiLabel.getForeground().getRed(), porquoiLabel.getForeground().getGreen(), porquoiLabel.getForeground().getBlue(), (int)(1f * 255)));
                pourquoiInputField.setEditable(true);
                pourquoiInputField.setFocusable(true);
            }
            repaint();
        });
        add(submitPuzzleButton);

        // help button

        helpButton = new JButton("?");
        helpButton.setOpaque(false);
        helpButton.setContentAreaFilled(false);
        helpButton.setBorderPainted(false);
        helpButton.setFont(font.deriveFont(Font.BOLD, 100f));
        helpButton.setBounds(1350,50,100,100);
        helpButton.setFocusPainted(false);

        helpButton.addActionListener(e -> {
            inHelpMenu = !inHelpMenu; // toggle between being in/out of the menu
            glassPane.setVisible(inHelpMenu); // toggle the visiblity of the help menu
            glassPane.repaint();
        });

        helpButton.addMouseListener(new java.awt.event.MouseAdapter() { // add muouse listener to detect mouse hovering over button
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

        glassPane = new JComponent() { // help menu
            @Override
            protected void paintComponent(Graphics g) {
                Font font;
                try { // initalize the font we're using
                    font = Font.createFont(Font.TRUETYPE_FONT, new File("Gluten-VariableFont_slnt,wght.ttf")).deriveFont(Font.BOLD,40f);
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
                    g2.drawString("Comment jouer!", 475, 280);
                    g2.setFont(font.deriveFont(Font.BOLD, 23f));
                    g2.drawString("• Remplis Qui, Quoi, Où, et peut-être Pourquoi", 470, 360);
                    g2.drawString("• Clique \"Soumettre\" pour valider tes réponses", 470, 430);
                    g2.drawString("• Le bouton \"?\" affiche ce menu d'aide", 470, 500);
                } else {
                    g2.setColor(Color.BLACK);
                    g2.setFont(font.deriveFont(Font.BOLD, 50f));
                    g2.drawString("Help!", 480, 280);
                    g2.setFont(font.deriveFont(Font.BOLD, 23f));
                    g2.drawString("• Fill out Qui, Quoi, Où, and maybe Pourquoi", 470, 360);
                    g2.drawString("• Click \"Soumettre\" to submit your answers", 470, 430);
                    g2.drawString("• The \"?\" button opens this help menu", 470, 500);
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
    
    public void paintComponent(Graphics g) {
         java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
         Graphics2D g2d = (Graphics2D) g;

         // background

         g2.setColor(new Color(255, 247, 232));
         g2.fillRect(0, 0, getWidth(), getHeight());

         // roundeded text boxes
         g.setColor(new Color(232, 232, 232));
         g2.setStroke(new BasicStroke(2));

         // draw the roudned box around the inptut puzzle number ting
         drawRoundedBox(g2, puzzleNumberField, new Color(232, 232, 232), 0);
         g2.fillRoundRect(submitPuzzleButton.getX(),submitPuzzleButton.getY(), submitPuzzleButton.getWidth(), submitPuzzleButton.getHeight(),50,50);
         
         // draw the rounided boxes around the input fields
         drawRoundedBox(g2, quiInputField, new Color(232, 232, 232), 1);
         drawRoundedBox(g2, quoiInputField, new Color(232, 232, 232), 2);
         drawRoundedBox(g2, ouInputField, new Color(232, 232, 232), 3);
         drawRoundedBox(g2, pourquoiInputField, new Color(232, 232, 232), 4);    

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

         if (resetFields) {
            answers = new String[4];
            inCorrect.setVisible(false);
            drawRoundedBox(g2, quiInputField, new Color(232, 232, 232), 1);
            drawRoundedBox(g2, quoiInputField, new Color(232, 232, 232), 2);
            drawRoundedBox(g2, ouInputField, new Color(232, 232, 232), 3);
            drawRoundedBox(g2, pourquoiInputField, new Color(232, 232, 232), 4);
            // resetFields = false;
         }
    }

    private void drawRoundedBox(Graphics2D g, JTextField textField, Color color, int isAnswerField) {
        int degrees = 50;
        int x = textField.getX();
        int y = textField.getY()-3;
        int width = textField.getWidth();
        int height = textField.getHeight();
    
        g.setColor(new Color(0 , 0, 0));
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, degrees, degrees);

        if (isAnswerField > 0 && !resetFields) { // for coloring the boxes based on answers, isAnswerField detects 1. if it's an answer field that's haivng a draw Rounded box 2. which answer field it is depending on its number
            if (correctAnswers != null && isAnswerField - 1 < correctAnswers.length) { // for porqoui stuff yk
                if (correctAnswers[isAnswerField - 1]) { // if correct green
                    g.setColor(new Color(147,197,114));
                } else { // if not, red!
                    g.setColor(new Color(205,92,92));
                }
            } else { // and if it's null (not checked yet) jus tgray
                g.setColor(Color.GRAY);
            }
        } else {
            g.setColor(Color.GRAY);
        }                
        g.drawRoundRect(x, y, width, height, degrees, degrees);
    }

    public String[] userAnswers() { // take a wild guess at what this does
        return answers;
    }

    public int getPuzzle() { // take another wild guess at what this does
        return puzzleNumber;
    }

    public void playSound(String filePath) { // to play audio
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
