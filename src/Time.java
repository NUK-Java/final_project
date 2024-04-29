import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

public class Time extends JLabel {
    Window window;
    int sec = 5;
    int surviveTime = 0;

    Timer timer = new Timer();
    TimerTask task = new TimerTask() { // Add opening curly brace here
        public void run() {
            sec--;
            surviveTime++;
            if(sec > 0) window.repaint(400,50,50,30);
            else {
                gameOver();
                timer.cancel();
            }
        }
    }; // Add closing curly brace here
    
    Time(Window w) {
        this.window = w;
        timer.scheduleAtFixedRate(task, 0, 1000);  // 在這裡啟動task Timer
    }

    public void paint(Graphics2D g) {
        g.setColor(new Color(0,0,0)); //畫筆顏色
        g.setFont(new Font("Verdana", Font.BOLD, 20)); //字型
        g.drawString(String.valueOf(sec), 400, 70);
    }

    public void plusTime() {
        sec += 1;
    }
    
    public void gameOver() {
        JOptionPane.showMessageDialog(window, "Your total survive time：" + surviveTime, "Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }
}