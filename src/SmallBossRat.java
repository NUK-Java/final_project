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
    Hole[] hole;

    int mode;

    Timer T = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            //System.out.println("SBR time run"+during);
            if((window.DuringTime-1)%30==0 && hp == 0 && window.bossRat == null){ //每五秒重生 測試用5秒
                born();
            }
            if(during==0){
                attack();
            }
            during--;
            if (time.sec <= 0) T.cancel();
        }
    };

    SmallBossRat(Hole []h, Time t, Window w) {
        this.during = 21;//持續20秒(含) 
        this.hp = 30;
        this.mode = (int)(Math.random() * 2);//0:紅(左鍵) 1:藍(右鍵)
        this.x = 335; // x座標設定為正中間
        this.y = 225; // y座標設定為正中間
        this.time = t;
        this.window = w;
        this.hole = h;
        T.scheduleAtFixedRate(task, 0, 1000); // 在這裡啟動task Timer
        window.repaint(hole[6].x, hole[6].y, 150, 150);
    }

    public void paint(Graphics g) {
        super.paint(g); // 畫出元件
        if (hp>0) {
            if(mode==0) g.setColor(new Color(255,0,0)); // 畫筆顏色
            else if (mode==1) g.setColor(new Color(0,0,255)); // 畫筆顏色
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
    }

    public void attack(){
        time.sec -= hp;
        hp = 0;
        hole[6].isRat = false;
        //System.out.println("attack");
        window.repaint(hole[6].x, hole[6].y, 150, 150);
    }

    public void born(){
        this.during = 21;
        this.hp = 30;
        window.repaint(hole[6].x, hole[6].y, 150, 150);
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1 && mode==0) {  // 左鍵
            if((hole[6].x - mx + 75) * (hole[6].x - mx + 75) + (hole[6].y - my + 75) * (hole[6].y - my + 75) <= 75*75 && hp > 0) {
                System.out.println("hit");
                this.reduceHp();
                window.repaint(hole[6].x, hole[6].y, 150, 150);
                mode = (int)(Math.random() * 2);
                if(this.dead()) {
                    window.finalScore += 20;
                }
            }
        }
        else if(e.getButton() == MouseEvent.BUTTON3 && mode==1) {  // 右鍵
            if((hole[6].x - mx + 75) * (hole[6].x - mx + 75) + (hole[6].y - my + 75) * (hole[6].y - my + 75) <= 75*75 && hp > 0) {
                System.out.println("hit");
                this.reduceHp();
                window.repaint(hole[6].x, hole[6].y, 150, 150);
                mode = (int)(Math.random() * 2);
                if(this.dead()) {
                    window.finalScore += 20;
                }
            }
        }
    }
}