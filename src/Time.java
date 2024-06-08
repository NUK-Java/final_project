import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;

public class Time extends JLabel {
    Window window;
    int sec = 1000;
    
    Timer Sectimer = new Timer();
    TimerTask task = new TimerTask() { // Add opening curly brace here
        public void run() {
            window.repaint(355,50,70,50);  // 重畫分數
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            window.repaint(500,50,50,50);  // 重畫時間
            sec--;
            if(sec > 0) {
                window.repaint(355,50,70,50);  // 重畫分數
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                window.repaint(500,50,50,50);  // 重畫時間
            }
            else {
                gameOver();
            }
        }
    }; // Add closing curly brace here
    
    Time(Window w) {
        this.window = w;
        Sectimer.scheduleAtFixedRate(task, 0, 1000);  // 在這裡啟動task Timer
    }

    public void paint(Graphics g) {
        g.setColor(new Color(250,100,0)); //畫筆顏色
        g.setFont(new Font("Verdana", Font.BOLD, 20)); //字型
        g.drawString("Time: " + String.valueOf(sec), 430, 70);
    }
    
    public void gameOver() {
        window.resetgame();
        window.finalScore += window.DuringTime;
        JOptionPane.showMessageDialog(this, "Your total score：" + window.finalScore, "Game Over", JOptionPane.YES_NO_OPTION);
        UI ui = new UI();
        window.dispose();
    }
}