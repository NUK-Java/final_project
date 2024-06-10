import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class Bomb extends JPanel{
    private BufferedImage image;
    private BufferedImage roundedImage; // 儲存裁剪後的圖片

    int hp = 1;         //hp
    int during = 3;     //持續時間
    int i;              //洞的index
    Hole[] hole;
    Time time;
    Window window;

    Timer T = new Timer();
    TimerTask task = new TimerTask() {
        public void run(){
            if(window.DuringTime % 4 == 0 && window.DuringTime != 12){ //每4秒重生
                born();
            }
            else if(timeout()){
                hp = 0;
                window.repaint(hole[i].x, hole[i].y, 100, 100);
            }
            during--;           
        }
    };

    Bomb(Hole[] h,Time t,Window w){
        this.hole = h;
        this.time = t;
        this.window = w;
        this.choosehole();
        
        T.scheduleAtFixedRate(task, 0, 1000);  // 在這裡啟動task Timer

        try {
            // 讀取圖片
            image = ImageIO.read(new File("./src/pic/trap.jpg"));
            // 調整圖片大小符合洞的大小
            int RatWidth = 100;
            int RatHeight = 100;
            BufferedImage resizedImage = new BufferedImage(RatWidth, RatHeight, BufferedImage.TYPE_INT_ARGB);  // 創建一個新的BufferedImage
            Graphics2D g2 = resizedImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);  // 設定圖片品質
            g2.drawImage(image, 0, 0, RatWidth, RatHeight, null);  // 繪製圖片
            g2.dispose();  // 釋放資源

            // 將圖片裁剪成圓形
            roundedImage = new BufferedImage(resizedImage.getWidth(), resizedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);  // 創建一個新的BufferedImage
            g2 = roundedImage.createGraphics();  
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  // 設定抗鋸齒
            g2.setClip(new Ellipse2D.Float(0, 0, resizedImage.getWidth(), resizedImage.getHeight()));  // 設定裁剪區域
            g2.drawImage(resizedImage, 0, 0, null);  // 繪製圖片
            g2.dispose();
        } catch (IOException e) {
            e.printStackTrace();  // 印出錯誤訊息
        }
        window.repaint(hole[i].x, hole[i].y, 100, 100);
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
        int holeX = hole[i].x; // 洞的x座標
        int holeY = hole[i].y; // 洞的y座標
        if(hp > 0) {
            g.drawImage(roundedImage, holeX, holeY, roundedImage.getWidth(), roundedImage.getHeight() , this);
        }
    }

    public void born(){
        this.during=3;
        hp = 1;
        hole[i].isRat = false;
        this.choosehole();
        window.repaint(hole[i].x, hole[i].y, 100, 100);//重繪新炸彈
    }

    public void boom(){
        time.sec -= 10;
        hp = 0;
        window.repaint(hole[i].x, hole[i].y, 100, 100);//清除爆炸完後的炸彈
        hole[i].isRat = false;
    }

    public boolean timeout(){
        return during == 0;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1){  // 左鍵
            if((hole[i].x - mx + 50) * (hole[i].x - mx + 50) + (hole[i].y - my + 50) * (hole[i].y - my + 50) <= 2500 && hp > 0) {
                System.out.println("hit");
                hp--;
                boom();
            }
        }
    }

}