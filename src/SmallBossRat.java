import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseEvent;

public class SmallBossRat extends JPanel {

    int during; // 存在時間
    int hp; // 生命值
    int x; // x座標
    int y; // y座標

    Time time;
    Window window;
    boolean isAlive;
    Hole[] hole;

    boolean hit = false;

    Timer T = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            if(during==0){
                attack();
            }
            during--;
            if (time.sec <= 0) {
                T.cancel();
                isAlive = false;
            }
        }
    };

    SmallBossRat(Hole []h, Time t, Window w) {
        this.during = 10;
        this.hp = 30;
        this.x = 335; // x座標設定為正中間
        this.y = 225; // y座標設定為正中間
        this.time = t;
        this.window = w;
        this.isAlive = true;
        this.hole = h;
        T.scheduleAtFixedRate(task, 0, 1000); // 在這裡啟動task Timer
        window.repaint(hole[6].x, hole[6].y, 150, 150);
    }

    public void paint(Graphics g) {
        super.paint(g); // 畫出元件
        if (isAlive) {
            g.setColor(new Color(255,165,0)); // 畫筆顏色
            g.setFont(new Font("Verdana", Font.BOLD, 50)); // 字型
            g.drawString(String.valueOf(hp), x + 42, y + 88);
        }
    }

    public boolean dead() { 
        if(hp == 0) {
            hole[6].isRat = false;
            return true;
        }
        else return false;
    }

    public void reduceHp() {
        hp--;
        repaint();
    }

    public void attack(){
        time.sec -= hp;
        hp = 0;
        hole[6].isRat = false;
        window.repaint(hole[6].x, hole[6].y, 150, 150);
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1 && !hit) {  // 左鍵
            if((hole[6].x - mx + 75) * (hole[6].x - mx + 75) + (hole[6].y - my + 75) * (hole[6].y - my + 75) <= 75*75 && hp > 0) {
                System.out.println("hit");
                this.reduceHp();
                if (this.dead()) {
                    T.cancel();
                    isAlive = false;
                }
                window.repaint(hole[6].x, hole[6].y, 150, 150);
                hit = true;
            }
        }
        else if(e.getButton() == MouseEvent.BUTTON3 && hit) {  // 右鍵
            if((hole[6].x - mx + 75) * (hole[6].x - mx + 75) + (hole[6].y - my + 75) * (hole[6].y - my + 75) <= 75*75 && hp > 0) {
                System.out.println("hit");
                this.reduceHp();
                if (this.dead()) {
                    T.cancel();
                    isAlive = false;
                }
                window.repaint(hole[6].x, hole[6].y, 150, 150);
                hit = false;
            }
        }
    }
}