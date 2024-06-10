import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;

public class Window extends JFrame implements MouseListener, MouseMotionListener, ActionListener {

    Window window = this;
    Hole[] hole = new Hole[7];  // 宣告一個Hole的陣列
    Rat[] NormalRat = new Rat[3];
    Time time = new Time(this);
    Bomb bomb;
    BossRat bossRat;
    SmallBossRat smallBossRat;
    Music normalMusic = new Music("./src/music/normalMusic.mp3");
    
    int attack = 1;
    int DuringTime = 1; // 遊戲進行時間
    int finalScore = 0;
    int score = 0;
    Timer Duringtimer = new Timer();


    JPanel imagePanel1;
    JLabel bgLabel1;
    public Window() {
        super("打地鼠");
        System.setProperty("sun.java2d.opengl", "true");
        Prop prop = new Prop(this, this.time);
        add(prop);

        setSize(800, 560);  // 設定size，顯示出去
        setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // 讓視窗置中
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        imagePanel1 = (JPanel) this.getContentPane(); // 把内容視窗轉為JPanel，否則不能使用setOpaque()來使視窗變成透明
        imagePanel1.setOpaque(false); // 使視窗變成透明 才可以放背景圖 不然會被白色蓋掉
        bgLabel1 = new JLabel(); 
        bgLabel1.setIcon(new ImageIcon("./src/pic/bg.jpg")); //把背景圖顯示在Label中
        bgLabel1.setBounds(0, 0, 800, 560);
        this.getLayeredPane().add(bgLabel1, new Integer(Integer.MIN_VALUE)); // 把含有背景圖之Label加到視窗的最底層以顯示背景圖
      

        addMouseListener(this);  // 在這個視窗上加入滑鼠監聽器
        addMouseMotionListener(this);
        for (int i = 0; i < 7; i++) {
            hole[i] = new Hole(this);  // 初始化hole
        }
        int[][] coordinates = {{360, 100},{233, 175},{488, 175},{233, 325}, {488, 325}, {360, 395}};  // 洞的座標
        int[][] bossCoordinate = {{335, 225}};
        for (int i = 0; i < 6; i++) {
            hole[i].setCoordinates(coordinates[i][0], coordinates[i][1]);
        }
        hole[6].setCoordinates(bossCoordinate[0][0], bossCoordinate[0][1]);

        TimerTask task = new TimerTask() { //這個跑完才換其他timer跑
            public void run() {

                generateNormalRat();

                if (DuringTime == 10 && bomb == null) {
                    bomb = new Bomb(hole, time, window);
                }

                if (DuringTime == 90 && bossRat == null) {      // 遊戲時間到90秒時，出現BossRat，測試先用5秒，
                    bossRat = new BossRat(hole, time, window);
                    normalMusic.stop();

                } else if (DuringTime == 30 && smallBossRat == null && bossRat == null) { //遊戲時間到30秒時，出現SmallBossRat，測試用5秒
                    smallBossRat = new SmallBossRat(hole, time, window);
                }
           
                System.out.println(DuringTime);
                DuringTime++;

                if (time.sec <= 0) {
                    Duringtimer.cancel();
                }
            }
        };
        Duringtimer.scheduleAtFixedRate(task, 0, 1000);  // 在這裡啟動task Timer
        normalMusic.play();
    }

    public void paint(Graphics g) {
        super.paint(g);  // 畫出元件
        time.paint(g);
        g.drawString("Score: " + score, 255, 70);  // 顯示分數

        for (int i = 0; i < 3; i++) {
            if (NormalRat[i] != null) {
                NormalRat[i].paint(g);  // 畫出3隻NormalRat
            }
        }

        if (bomb != null) {
            bomb.paint(g);
        }

        if (bossRat != null) {
            bossRat.paint(g);
        }

        if (smallBossRat != null) {
            smallBossRat.paint(g);
        }

    }

    public void generateNormalRat() {
        for (int i = 0; i < 3; i++) {
            if (NormalRat[i] == null) {
                NormalRat[i] = new Rat(hole, time, window);
                break; //生成一隻就跳出迴圈 
            }
        }
    }

    public void resetgame() {
        for (int i = 0; i < 3; i++) {
            if (NormalRat[i] != null) {
                NormalRat[i].T.cancel();
                NormalRat[i] = null;
            }
        }

        if (bomb != null) {
            bomb.T.cancel();
            bomb = null;
        }

        if (bossRat != null) {
            bossRat.T.cancel();
            bossRat = null;
        }
        if (smallBossRat != null) {
            smallBossRat.T.cancel();
            smallBossRat = null;
        }
        time.Sectimer.cancel();
        Duringtimer.cancel();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        for (int i = 0; i < 3; i++) {
            if (NormalRat[i] != null) {
                NormalRat[i].mousePressed(e);
            }
        }
        if (bomb != null) {
            bomb.mousePressed(e);
        }
        if (bossRat != null) {
            bossRat.mousePressed(e);
        }
        if (smallBossRat != null) {
            smallBossRat.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (int i = 0; i < 3; i++) {
            if (NormalRat[i] != null) {
                NormalRat[i].mouseMoved(e);
            }
        }
        if (bossRat != null) {
            bossRat.mouseMoved(e);
        }
    }

    /***主程式***/
    public static void main(String args[]) {
        UI ui = new UI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    public Image offScreenImage = null;
    public Graphics offScreenGraphics = null;

    public void update(Graphics g) {
        offScreenImage = createImage(800,560);
        offScreenGraphics = offScreenImage.getGraphics();
        offScreenGraphics.clearRect(0, 0, getWidth(), getHeight());
        paint(offScreenGraphics);
        g.drawImage(offScreenImage, 0, 0, this);
    }
}
