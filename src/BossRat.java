import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.MouseEvent;

public class BossRat extends JPanel {
    int during; // 存在時間
    int hp; // 生命值
    int x; // x座標
    int y; // y座標
    int mode;   //攻擊方式
    Time time;
    Window window;
    boolean isAlive;
    Hole[] hole;

    Timer T = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            changeMode();
            window.repaint(hole[6].x, hole[6].y, 150, 150);
            if(during % 5 == 0 && during != 30){
                attack();
            }
            during--;
            if (time.sec <= 0) {
                T.cancel();
                isAlive = false;
            }
        }
    };

    BossRat(Hole []h, Time t, Window w) {
        this.during = 30;
        this.hp = 50;
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
            if(mode==0) g.setColor(new Color(255,0,0)); //紅
            else if(mode==1) g.setColor(new Color(0,0,255));//藍
            else if(mode==2) g.setColor(new Color(128,0,128));//紫
            else if(mode==3) g.setColor(new Color (255,215,0)); //黃
		    g.setFont(new Font("Verdana", Font.BOLD, 50)); //字型
		    g.drawString(String.valueOf(hp), hole[6].x+42, hole[6].y+88);
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
        time.sec -= (int)(Math.random() * 5);   //扣1~5sec
        hp += (int)(Math.random() * 5);         //加1~5hp
        window.repaint(hole[6].x, hole[6].y, 150, 150); //重畫boss的hp
    }

    public void changeMode(){
        int temp = mode;
        while(temp == mode){
            mode = (int)(Math.random() * 4);
        }
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1 && mode==0) {  // 左鍵
            if((hole[6].x - mx + 75) * (hole[6].x - mx + 75) + (hole[6].y - my + 75) * (hole[6].y - my + 75) <= 75*75 && hp > 0) {
                System.out.println("hit");
                this.reduceHp();
                window.repaint(hole[6].x, hole[6].y, 150, 150);//打擊後的重繪
                if(this.dead()){
                    time.gameOver();
                }
            }
        }
        if(e.getButton() == MouseEvent.BUTTON3 && mode==1) {  // 右鍵
            if((hole[6].x - mx + 75) * (hole[6].x - mx + 75) + (hole[6].y - my + 75) * (hole[6].y - my + 75) <= 75*75 && hp > 0) {
                System.out.println("hit");
                this.reduceHp();
                window.repaint(hole[6].x, hole[6].y, 150, 150);//打擊後的重繪
                if(this.dead()){
                    time.gameOver();
                }
            }
        }
    }

    boolean in=false;
    public void mouseMoved(MouseEvent e) {  
        int mx = e.getX();
        int my = e.getY();
        if(mode==2){
            if((hole[6].x - mx + 75) * (hole[6].x - mx + 75) + (hole[6].y - my + 75) * (hole[6].y - my + 75) <= 75*75 && in==false &&hp>0) {
                in=true; 
            }
            if((hole[6].x - mx + 75) * (hole[6].x - mx + 75) + (hole[6].y - my + 75) * (hole[6].y - my + 75) > 75*75 && in==true &&hp>0) {
                System.out.println("cut");
                in=false;
                this.reduceHp();
                window.repaint(hole[6].x, hole[6].y, 150, 150);//打擊後的重繪
                if(this.dead()){
                    time.gameOver();
                }
            }
        }
        
    }
}