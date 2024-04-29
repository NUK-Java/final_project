import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Timer;
import java.util.TimerTask;

public class Window extends JFrame implements MouseListener {
    Window window = this;
    Hole[] hole = new Hole[7];  // 宣告一個Hole的陣列
    Rat[] NormalRat= new Rat[3];
    Time time = new Time(this);
    BossRat bossRat;
    int DuringTime = 1;//遊戲進行時間
    Timer Duringtimer = new Timer();
    
    public Window() {
        super("打地鼠");
        setSize(800,560);  // 設定size，顯示出去
        setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // 讓視窗置中
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addMouseListener(this);  // 在這個視窗上加入滑鼠監聽器
        
        for(int i = 0; i < 7; i++){
            hole[i] = new Hole(this);  // 初始化hole
        }
        int[][] coordinates = {{360, 100},{233, 175},{488, 175},{233, 325}, {488, 325}, {360, 395}};  // 洞的座標
        int[][] bossCoordinate = {{335,225}};
        for(int i = 0; i < 6; i++){
            hole[i].setCoordinates(coordinates[i][0], coordinates[i][1]);
        }
        hole[6].setCoordinates(bossCoordinate[0][0], bossCoordinate[0][1]);

        TimerTask task = new TimerTask() {
            public void run() {
                
                generateNormalRat();
                if (DuringTime >= 5 && bossRat == null) {            // 遊戲時間到50秒時，出現BossRat，測試先用5秒，
                    bossRat = new BossRat(hole, time, window);
                    window.repaint(hole[6].x, hole[6].y, 150, 150);
                }

                System.out.println(DuringTime);
                DuringTime++;

                if (time.sec <= 0) {
                    Duringtimer.cancel();
                }     

            }
        };
        Duringtimer.scheduleAtFixedRate(task, 0, 1000);  // 在這裡啟動task Timer
    }
   
    public void paint(Graphics g) {
        super.paint(g);  // 畫出元件
        Graphics2D g2d = (Graphics2D) g;
        time.paint(g2d);
        for(int i = 0; i < 6; i++){
            hole[i].paint(g2d);  // 畫出6個hole
        }
        hole[6].bossPaint(g2d);
        
        for(int i = 0; i < 3; i++){
            if(NormalRat[i] != null){
                NormalRat[i].paint(g2d);  // 畫出3隻NormalRat
            }
        }

        if (bossRat != null) {
            bossRat.paint(g2d);
        }
    }

    public void generateNormalRat() {
        for (int i = 0; i < 3; i++) {
            if (NormalRat[i] == null) {
                NormalRat[i] = new Rat(hole, time, window);
                break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        for(int i=0;i<3;i++){
            if(NormalRat[i] != null){
                NormalRat[i].mousePressed(e);
            }
        }
        if (bossRat != null) {
            bossRat.mousePressed(e);
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

 
    
    