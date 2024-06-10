import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Font;

public class Prop extends JPanel implements ActionListener {

    private Window window;
    private Time time;
    private JButton button1, button2, button3;

    private int during1,during2,during3;

    public Prop(Window window,Time time) {
        this.window = window;
        this.time = time;
        setLayout(null);
        this.setOpaque(false);
        
        Font buttonFont = new Font("Microsoft YaHei", Font.PLAIN, 14);

        ImageIcon icon1 = new ImageIcon("./src/pic/sword.png");
        Image image = icon1.getImage();
        Image scaledImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage);
        icon1 = scaledIcon1;
        button1 = new JButton("火力加倍", icon1);
        button1.setBounds(25, 125, 150, 60);
        button1.addActionListener(this);
        button1.setFont(buttonFont);
        add(button1);

        ImageIcon icon2 = new ImageIcon("./src/pic/doublescore.png");
        image = icon2.getImage();
        scaledImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage);
        icon2 = scaledIcon2;
        button2 = new JButton("得分加倍",icon2);
        button2.setBounds(25, 225, 150, 60);
        button2.addActionListener(this);
        button2.setFont(buttonFont);
        
        add(button2);

        ImageIcon icon3 = new ImageIcon("./src/pic/clockstop.png");
        image = icon3.getImage();
        scaledImage = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon3 = new ImageIcon(scaledImage);
        icon3 = scaledIcon3;
        button3 = new JButton("時間暫停",icon3);
        button3.setBounds(25, 325, 150, 60);
        button3.addActionListener(this);
        button3.setFont(buttonFont);
        add(button3);
    }


    // 按下去的功能寫這
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            button1.setVisible(false);
            System.out.println("火力加倍開啟");
            window.attack = 2;
            during1 = 10;
            Timer T = new Timer();
            TimerTask task = new TimerTask() { 
                public void run() {
                    if(during1 == 0){
                        T.cancel();
                        window.attack = 1;
                        System.out.println("火力加倍結束");
                        button1.setEnabled(false);
                        button1.setVisible(false);
                    }
                    if(time.sec<=0) T.cancel();
                    during1--;
                }
            };
            T.scheduleAtFixedRate(task, 0, 1000);

        } else if (e.getSource() == button2) {
            button2.setVisible(false);
            System.out.println("得分加倍開啟");
            for(int i = 0; i < 3; i++){
                if(window.NormalRat[i] != null){
                    window.NormalRat[i].normal_score = 2;
                }
            }
            if(window.bossRat != null){
                window.bossRat.score = 100;
            }
            if(window.smallBossRat != null){
                window.smallBossRat.score = 40;
            }
            during2 = 20;
            Timer T = new Timer(
            );
            TimerTask task = new TimerTask() { 
                public void run() {
                    if(during2 == 0){
                        T.cancel();
                        System.out.println("得分加倍結束");
                        button2.setEnabled(false);
                        button2.setVisible(false);
                        for(int i = 0; i < 3; i++){
                            if(window.NormalRat[i] != null){
                                window.NormalRat[i].normal_score = 1;
                            }
                        }
                        if(window.bossRat != null){
                            window.bossRat.score = 50;
                        }
                        if(window.smallBossRat != null){
                            window.smallBossRat.score = 20;
                        }
                    }
                    if(time.sec<=0) T.cancel();
                    during2--;
                }
            };
            T.scheduleAtFixedRate(task, 0, 1000);
        } else if (e.getSource() == button3) {
            button3.setVisible(false);
            System.out.println("時間暫停開啟");
            during3 = 10;
            Timer T = new Timer();
            TimerTask task = new TimerTask() { 
                public void run() {
                    time.sec++;
                    if(during3 == 0){
                        T.cancel();
                        System.out.println("時間暫停結束");
                        button3.setEnabled(false);
                        button3.setVisible(false);
                    }
                    if(time.sec<=0) T.cancel();
                    during3--;
                }
            };
            T.scheduleAtFixedRate(task, 0, 1000);
        }
    }
}