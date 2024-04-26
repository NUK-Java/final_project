import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Time extends JLabel{
    JLabel lab1=new JLabel("60秒");
    window window;
    int sec=60;

    Time(window w){
        this.window=w;
    }

    public void paint(Graphics2D g){
        g.setColor(new Color(0,0,0)); //畫筆顏色
        g.setFont(new Font("Verdana", Font.BOLD, 20)); //字型
        g.drawString(String.valueOf(sec), 286, 70);
    }

}