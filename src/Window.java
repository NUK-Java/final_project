import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

import java.awt.Container;
import java.awt.FlowLayout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Math; 

public class Window extends JFrame implements MouseListener {
    Time time = new Time(this);
    Hole[] hole = new Hole[6];  // 宣告一個Hole的陣列
    Rat rat,rat1;
    public Window() {
        super("打地鼠");
        setSize(600,420);  // 設定size，顯示出去
        setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // 讓視窗置中
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addMouseListener(this);  // 在這個視窗上加入滑鼠監聽器
        
        for(int i = 0; i < 6; i++){
            hole[i] = new Hole(this);  // 初始化hole
        }
        int[][] coordinates = {{100, 100}, {250, 100}, {400, 100}, {100, 250}, {250, 250}, {400, 250}};  // 洞的座標
        for(int i = 0; i < 6; i++){
            hole[i].setCoordinates(coordinates[i][0], coordinates[i][1]);
        }

        rat = new Rat(hole, this);  // 初始化rat
        rat1 = new Rat(hole, this);  // 初始化rat
    }
   
    public void paint(Graphics g) {
        super.paint(g);  // 畫出元件
        Graphics2D g2d = (Graphics2D) g;
        time.paint(g2d);
        for(int i = 0; i < 6; i++){
            hole[i].paint(g2d);  // 畫出6個hole
        }
        rat.paint(g2d);  // 畫出rat
        rat1.paint(g2d);  // 畫出rat
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        rat.mousePressed(e);
        rat1.mousePressed(e);
        if(rat.dead()) {
            time.plusTime();
            rat.born();
        }
        if(rat1.dead()) {
            time.plusTime();
            rat1.born();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    /***主程式***/
    public static void main(String args[]) {
        Window game = new Window(); 
    }
}

 
    
    
