import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Rat extends JPanel {
    int during=3; //存在時間
    int hp = (int)(Math.random() * 5) + 1;//生命值1~5隨機
    int i;//洞的index
    Hole[] hole;
    Time time;
    Window window;  
    Rat(Hole[]h, Time t, Window w) {
        this.hole = h;
        this.time = t;
        this.window = w;
        this.choosehole();
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

    public void born() {
        hp = (int)(Math.random() * 5) + 1;
        this.choosehole();
        window.repaint(hole[i].x+25, hole[i].y+25, 50, 50);//重繪新老鼠
    }
    
    public boolean dead() { 
        if(hp == 0) {
            hole[i].isRat = false;
            time.plusTime();
            return true;
        }
        else return false;
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
            }
            if(this.dead()){
                this.born();
            }
        }
    }
}