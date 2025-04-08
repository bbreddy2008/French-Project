import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;



public class Interface extends JPanel {

    private BufferedImage logo; // logo
    JLabel logoPanel; // logo panel
    JTextField quiTextField; // quiTextField
    
    public Interface() {
        setLayout(null);
        try {        
            logo = ImageIO.read(this.getClass().getResource("logo.png"));
        } catch (IOException ex) {
            // ¯\_(ツ)_/¯
        }

        //logo
        logoPanel = new JLabel(new ImageIcon(logo));
        logoPanel.setBounds(0, 0, 1500, 250);
        add(logoPanel);

        // puzzle number

        quiTextField = new JTextField();
        quiTextField.setColumns(2);
        Font bigFont = quiTextField.getFont().deriveFont(Font.PLAIN, 50f);
        quiTextField.setFont(bigFont);
        add(quiTextField);

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
        g.setColor(new Color(255, 247, 232));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}