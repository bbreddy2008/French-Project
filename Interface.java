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
        logoPanel = new JLabel(new ImageIcon(logo));
        logoPanel.setBounds(0, 0, 1500, 250);
        add(logoPanel);
        
        // puzzle number
        puzzleNumber = new JTextField();
        puzzleNumber.setColumns(2);
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


        // window stuff
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setPreferredSize(new Dimension(500, 500));
        final JFrame f = new JFrame("");
        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(this,BorderLayout.CENTER);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

         // roundeded text box puzzle input

         // g2.setStroke(new java.awt.BasicStroke(5)); // thickness of 3.0f
         // g2.setColor(Color.black);
         // RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(200, 100, 100, 100, 15, 15);
         // g2.draw(roundedRectangle);
    }
}