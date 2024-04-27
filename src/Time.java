import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

public class Time extends JLabel{
    Window window;
    int sec = 5;
    int survive_time = sec;

    Timer timer = new Timer();
    TimerTask task = new TimerTask(){
        public void run(){
            sec--;
            if(sec > 0) window.repaint(286,50,50,30);
            else {
                gameOver();
                timer.cancel();
            }
        }
    };

    Time(Window w) {
        this.window = w;
        timer.scheduleAtFixedRate(task, 0, 1000);  // 在這裡啟動Timer
    }

    public void paint(Graphics2D g) {
        g.setColor(new Color(0,0,0)); //畫筆顏色
        g.setFont(new Font("Verdana", Font.BOLD, 20)); //字型
        g.drawString(String.valueOf(sec), 286, 70);
    }

    public void plusTime() {
        sec+=1;
        survive_time+=1;
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(window, "Your total survive time：" + survive_time, "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
}