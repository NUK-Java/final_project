import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // Import the ActionListener interface

public class Prop extends JPanel implements ActionListener {
    private Window window;
    private JButton button1, button2, button3;

    public Prop(Window window) {
        this.window = window;
        setLayout(null);
        setSize(800, 50);
        setLocation(0, 510);

        button1 = new JButton("Button 1");
        button1.setBounds(50, 50, 100, 30);
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("Button 2");
        button2.setBounds(50, 150, 100, 30);
        button2.addActionListener(this);
        add(button2);

        button3 = new JButton("Button 3");
        button3.setBounds(50, 250, 100, 30);
        button3.addActionListener(this);
        add(button3);
    }


    // 按下去的功能寫這
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            System.out.println("Button 1 clicked");
        } else if (e.getSource() == button2) {
            System.out.println("Button 2 clicked");
        } else if (e.getSource() == button3) {
            System.out.println("Button 3 clicked");
        }
    }
}