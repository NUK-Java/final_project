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
    //Container c;
    //JButton bot=new JButton("START");
    Time time = new Time(this);
    Hole[] hole = new Hole[6];  // 宣告一個Hole的陣列
    Rat rat;
    public Window() {
        super("打地鼠");
        setSize(600,420);  // 設定size，顯示出去
        setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // 讓視窗置中
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addMouseListener(this);  // 在這個視窗上加入滑鼠監聽器

        //c=getContentPane();//取得ContentPane
        //c.setLayout(new FlowLayout());
        //c.add(bot);
        
        for(int i = 0; i < 6; i++){
            hole[i] = new Hole(this);  // 初始化hole
        }
        int[][] coordinates = {{100, 100}, {250, 100}, {400, 100}, {100, 250}, {250, 250}, {400, 250}};  // 洞的座標
        for(int i = 0; i < 6; i++){
            hole[i].setHole(coordinates[i][0], coordinates[i][1]);
        }

        int i = (int)(Math.random() * 6);  // 隨機選一個洞0~5
        rat = new Rat(hole[i], this);  // 初始化rat
    }
   
    public void paint(Graphics g) {
        super.paint(g);  // 畫出元件
        Graphics2D g2d = (Graphics2D) g;
        time.paint(g2d);
        for(int i = 0; i < 6; i++){
            hole[i].paint(g2d);  // 畫出6個hole
        }
        rat.paint(g2d);  // 畫出rat
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        rat.mousePressed(e);
        if(rat.dead()) {
            int i=(int)(Math.random() * 6);
            rat = new Rat(hole[i],this);
            time.plusTime();
            this.repaint(hole[i].x+25, hole[i].y+25, 50, 50);
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

 
    
    