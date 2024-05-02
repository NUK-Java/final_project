import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends JPanel{
    
    int hp = 1;         //hp
    int during = 3;     //持續時間
    int i;              //洞的index
    Hole[] hole;
    Time time;
    Window window;

    Timer T = new Timer();
    TimerTask task = new TimerTask() {
        public void run(){
            if(window.DuringTime % 4 == 0 && window.DuringTime != 10){ //每4秒重生
                born();
            }
            if(dead()){
                hp = 0;
                window.repaint(hole[i].x+25, hole[i].y+25, 50, 50);
            }     
            during--;
        }
    };

    Bomb(Hole[] h,Time t,Window w){
        this.hole = h;
        this.time = t;
        this.window = w;
        this.choosehole();
        window.repaint(hole[i].x+25, hole[i].y+25, 50, 50);
        T.scheduleAtFixedRate(task, 0, 1000);  // 在這裡啟動task Timer
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

    public void paint(Graphics g) {
        if(hp > 0) {
            g.setColor(new Color(0,0,0)); //黑
		    g.setFont(new Font("Verdana", Font.BOLD, 50)); //字型
		    g.drawString(String.valueOf('*'), hole[i].x+32, hole[i].y+68);
        }
    }

    public void born(){
        hp = 0;
        window.repaint(hole[i].x+25, hole[i].y+25, 50, 50);
        this.during=3;
        hp = 1;
        hole[i].isRat = false;
        this.choosehole();
        window.repaint(hole[i].x+25, hole[i].y+25, 50, 50);//重繪新炸彈
    }

    public boolean dead(){
        return during == 0 ? true : false; 
    }

    public void attack(){
        time.sec -= 10;
        hp = 0;
        window.repaint(hole[i].x+25, hole[i].y+25, 50, 50);//清除爆炸完後的炸彈
        hole[i].isRat = false;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1){  // 左鍵
            if((hole[i].x - mx + 50) * (hole[i].x - mx + 50) + (hole[i].y - my + 50) * (hole[i].y - my + 50) <= 2500 && hp > 0) {
                System.out.println("hit");
                hp--;
                attack();
                window.repaint(hole[i].x+25, hole[i].y+25, 50, 50);//打擊後的重繪
            }
        }
    }

}
