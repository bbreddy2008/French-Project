import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;


public class Interface extends JPanel {
    private BufferedImage logo; // logo
    JLabel logoPanel; // logo panel
    JTextField puzzleNumber; // quiTextField
    JLabel puzzleLabel; // puzzle number label
    JTextField quiInputField; // quiTextField
    JLabel quiLabel; // puzzle number label
    JTextField quoiInputField; // quiTextField
    JLabel quoiLabel; // puzzle number label
    JTextField ouInputField; // quiTextField
    JLabel ouLabel; // puzzle number label
    JTextField pourquoiInputField; // quiTextField
    JLabel porquoiLabel; // puzzle number labels
    
    public Interface() {
<<<<<<< HEAD
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
=======
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("Gluten-VariableFont_slnt,wght.ttf"));
        } catch (FontFormatException | IOException exception) {
            font = new Font("Arial", Font.PLAIN, 12);
        }

        setLayout(null);
        try {                
            logo = ImageIO.read(this.getClass().getResource("logo.png"));
        } catch (IOException ex) {
            // do nothing
        }

    
        //logo
>>>>>>> refs/remotes/origin/main
        logoPanel = new JLabel(new ImageIcon(logo));
        logoPanel.setBounds(0, 0, 1500, 250);
        add(logoPanel);
        
        // puzzle number
        puzzleNumber = new JTextField();
        puzzleNumber.setColumns(2);
<<<<<<< HEAD
        puzzleNumber.setBounds(220, 205, 70, 50);
        puzzleNumber.setFont(font);
        puzzleNumber.setOpaque(false);
        puzzleNumber.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(puzzleNumber);

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
=======
        puzzleNumber.setBounds(220, 200, 80, 60);
        puzzleNumber.setFont(font);
        add(puzzleNumber);

        puzzleLabel = new JLabel("Puzzle:");
        puzzleLabel.setFont(font.deriveFont(Font.BOLD, 50f));
        puzzleLabel.setBounds(50,180,1000,100);
        add(puzzleLabel);

        // all text fields
        quiInputField = new JTextField();
        quiInputField.setColumns(2);
        quiInputField.setFont(font);
        quiInputField.setBounds(450, 300, 600, 60);
        add(quiInputField);

        quoiInputField = new JTextField();
        quoiInputField.setColumns(2);
        quoiInputField.setFont(font);
        quoiInputField.setBounds(450, 400, 600, 60);
        add(quoiInputField);

        ouInputField = new JTextField();
        ouInputField.setColumns(2);
        ouInputField.setFont(font);
        ouInputField.setBounds(450, 400, 600, 60);
        add(ouInputField);

        pourquoiInputField = new JTextField();
        pourquoiInputField.setColumns(2);
        pourquoiInputField.setFont(font);
        pourquoiInputField.setBounds(450, 400, 600, 60);
        add(pourquoiInputField);

>>>>>>> refs/remotes/origin/main

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

<<<<<<< HEAD
         // roundeded text boxes
         g2.setStroke(new BasicStroke(2));
         drawRoundedBox(g2, puzzleNumber);
         drawRoundedBox(g2, quiInputField);
         drawRoundedBox(g2, quoiInputField);
         drawRoundedBox(g2, ouInputField);
         drawRoundedBox(g2, pourquoiInputField);
         
    }

    private void drawRoundedBox(Graphics2D g, JTextField textField) {
        int degrees = 30;
        int x = textField.getX();
        int y = textField.getY();
        int width = textField.getWidth();
        int height = textField.getHeight();
    
        g.setColor(new Color(232, 232, 232));
        g.fillRoundRect(x, y, width, height, degrees, degrees);
    
        g.setColor(Color.GRAY);
        g.drawRoundRect(x, y, width, height, degrees, degrees);
=======
         // roundeded text box puzzle input

         // g2.setStroke(new java.awt.BasicStroke(5)); // thickness of 3.0f
         // g2.setColor(Color.black);
         // RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(200, 100, 100, 100, 15, 15);
         // g2.draw(roundedRectangle);
>>>>>>> refs/remotes/origin/main
    }
}