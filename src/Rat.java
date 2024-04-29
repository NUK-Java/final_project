import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Rat extends JPanel {
    int during; //存在時間
    int hp;//生命值1~5隨機
    int i;//洞的index
    Hole[] hole;
    Time time;
    Window window;  

    Timer T = new Timer();
    TimerTask task = new TimerTask() { 
        public void run() {
            during--;
            if(during==0){
                attack();
            }
            if(time.sec<=0) T.cancel();
        }
    };

    Rat(Hole[]h, Time t, Window w) {
        this.during = 3;
        this.hp = (int)(Math.random() * 5) + 1;
        this.hole = h;
        this.time = t;
        this.window = w;
        this.choosehole();
        window.repaint(hole[i].x+25, hole[i].y+25, 50, 50);
        T.scheduleAtFixedRate(task, 0, 1000);  // 在這裡啟動task Timer
    }
    
    public void paint(Graphics g) {
        if(hp > 0) {
            g.setColor(new Color(237,40,0)); //畫筆顏色
		    g.setFont(new Font("Verdana", Font.BOLD, 50)); //字型
		    g.drawString(String.valueOf(hp), hole[i].x+32, hole[i].y+68);
        }
    }
    
    public void choosehole() {
        i = (int)(Math.random() * 6);
        if (hole[i].isRat == true){      
            this.choosehole();
        }
        else{
            hole[i].isRat = true;
        }
    }

    public boolean dead() { 
        if(hp == 0) {
            hole[i].isRat = false;
            return true;
        }
        else return false;
    }

    public void attack() {
        time.sec-=hp;
        hp = 0;
        //if(time.sec<=0) time.gameOver();
        hole[i].isRat = false;
        window.repaint(hole[i].x+25, hole[i].y+25, 50, 50);
        born();//攻擊後重生
    }

    public void born() {
        this.during=3;
        hp = (int)(Math.random() * 5) + 1;
        this.choosehole();
        window.repaint(hole[i].x+25, hole[i].y+25, 50, 50);//重繪新老鼠
    }
   
    public void reduceHp() {
        hp--;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1) {  // 左鍵
            if((hole[i].x - mx + 50) * (hole[i].x - mx + 50) + (hole[i].y - my + 50) * (hole[i].y - my + 50) <= 2500 && hp > 0) {
                System.out.println("hit");
                this.reduceHp();
                window.repaint(hole[i].x+25, hole[i].y+25, 50, 50);//打擊後的重繪
                if(this.dead()){
                    time.sec++;
                    this.born();
                }
            }
        }
    }
}