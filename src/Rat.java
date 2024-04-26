import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Rat extends JPanel{
    Hole hole;
    int hp=(int)(Math.random()*5)+1;//生命值1~5隨機
    
    window w;

    Rat(Hole h, window w){
        this.hole = h;
        this.w = w;
    }
    
    public void paint(Graphics2D g){
        if(hp>0){
            g.setColor(new Color(237,40,0)); //畫筆顏色
		    g.setFont(new Font("Verdana", Font.BOLD, 50)); //字型
		    g.drawString(String.valueOf(hp), hole.x+32, hole.y+68);
        }
    }

    public boolean dead(){ 
        if(hp==0){
            //System.out.println("Rat dead");
            return true;
        }
        else return false;
    }
    
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(e.getButton()==MouseEvent.BUTTON1){//左鍵
            if((hole.x-mx+50)*(hole.x-mx+50)+(hole.y-my+50)*(hole.y-my+50)<=2500 && hp>0){
                hp--;
                w.repaint();
            }
        }
    }
}