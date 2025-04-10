// HOW TO USE:
// .userAnswers();
//      returns the answers after inputed into the text fields and press submit
// .puzzleNumber();
//      returns the puzzle number submitted by the user

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;


public class Interface extends JPanel {
    private BufferedImage logo; // logo
    private JLabel logoPanel; // logo panel

    private JTextField puzzleNumberField; // quiTextField
    private JLabel puzzleLabel; // puzzle number label

    private JTextField quiInputField; // quiTextField
    private JLabel quiLabel; // puzzle number label

    private JTextField quoiInputField; // quiTextField
    private JLabel quoiLabel; // puzzle number label

    private JTextField ouInputField; // quiTextField
    private JLabel ouLabel; // puzzle number label

    private JTextField pourquoiInputField; // quiTextField
    private JLabel porquoiLabel; // puzzle number labels

    private JButton submitButton; // submit button
    private boolean isHovering = false; // see if mouse is hovering over submit button
    String[] answers = new String[4];
    
    private JButton helpButton; // help button!
    private boolean isHoveringHelp; // hovering the help button
    private boolean inHelpMenu = false;

    private JButton submitPuzzleButton;
    private int puzzleNumber;


    //TO DO: Change name of file from Interface to something else
    public Interface() {
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
        puzzleNumberField = new JTextField();
        puzzleNumberField.setColumns(2);
        puzzleNumberField.setBounds(220, 205, 70, 50);
        puzzleNumberField.setFont(font);
        puzzleNumberField.setOpaque(false);
        puzzleNumberField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

        // submit button

        submitButton = new JButton("Soumettre ");
        submitButton.setOpaque(false);
        submitButton.setContentAreaFilled(false);
        submitButton.setBorderPainted(false);
        submitButton.setFont(font.deriveFont(Font.BOLD, 50f));
        submitButton.setBounds(1100,650,350,100);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> {
           answers[0] = quiInputField.getText();
           answers[1] = quoiInputField.getText();
           answers[2] = ouInputField.getText();
           answers[3] = pourquoiInputField.getText();
           Main myMain = new Main();
           myMain.submitButtonClicked();
           System.out.println("Answers submitted!" + answers[0]);
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
        submitPuzzleButton.addActionListener(e -> {
            puzzleNumber = Integer.valueOf(puzzleNumberField.getText());
            System.out.println("id: "+puzzleNumber);
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
            inHelpMenu = !inHelpMenu;
            repaint();
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

        // window stuff
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setPreferredSize(new Dimension(500, 500));
        final JFrame f = new JFrame(); // choose to add title, i prefer not but whatever
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(this,BorderLayout.CENTER);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // f.setUndecorated(true); // if we want no top or not
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new Interface();
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
         drawRoundedBox(g2, puzzleNumberField);
         g2.fillRoundRect(submitPuzzleButton.getX(),submitPuzzleButton.getY(), submitPuzzleButton.getWidth(), submitPuzzleButton.getHeight(),50,50);
         drawRoundedBox(g2, quiInputField);
         drawRoundedBox(g2, quoiInputField);
         drawRoundedBox(g2, ouInputField);
         drawRoundedBox(g2, pourquoiInputField);    

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
         
         // help menu
         if (inHelpMenu) {
            helpMenu(g2);
         }
    }

    private void drawRoundedBox(Graphics2D g, JTextField textField) {
        int degrees = 50;
        int x = textField.getX();
        int y = textField.getY()-3;
        int width = textField.getWidth();
        int height = textField.getHeight();
    
        // g.setColor(new Color(232, 232, 232));
        g.fillRoundRect(x, y, width, height, degrees, degrees);
    
        g.setColor(Color.GRAY);
        g.drawRoundRect(x, y, width, height, degrees, degrees);
    }

    private void helpMenu(Graphics2D g) {
        g.setColor(Color.BLACK);
        float alpha = 0.5f;
        AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g.setComposite(alcom);
        g.fillRoundRect(400+3,200+3,710,510, 20, 20);
        alpha = 1f;
        alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g.setComposite(alcom);
        g.setColor(new Color(150, 150, 150));
        g.fillRoundRect(400,200,700,500, 20, 20);
    }

    public String[] userAnswers() {
        return answers;
    }

    public int getPuzzle() {
        return puzzleNumber;
    }
}