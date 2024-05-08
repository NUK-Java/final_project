import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class Rat extends JPanel {
    private BufferedImage image;
    private BufferedImage roundedImage; // 儲存裁剪後的圖片
    int mode;
    int during; //存在時間
    int hp;//生命值1~5隨機
    int i;//洞的index
    Hole[] hole;
    Time time;
    Window window;  

    Timer T = new Timer();
    TimerTask task = new TimerTask() { 
        public void run() {
            if(during==0 && dead()) {
                born();
            }
            else if(during==0){
                attack();
            }
            during--;
            if(time.sec<=0) T.cancel();
        }
    };

    Rat(Hole[]h, Time t, Window w) {
        this.mode=(int)(Math.random() * 3);
        this.mode=(int)(Math.random() * 3);
        this.during = 3;
        this.hp = (int)(Math.random() * 5) + 1;
        this.hole = h;
        this.time = t;
        this.window = w;
        this.choosehole();
        T.scheduleAtFixedRate(task, 0, 1000);  // 在這裡啟動task Timer
        try {
            // 讀取圖片
            image = ImageIO.read(new File("C:/java project/final_project/src/mouse4.jpg"));
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
    
    public void paint(Graphics g) {
        if(hp > 0) {
            int holeX = hole[i].x; // 洞的x座標
            int holeY = hole[i].y; // 洞的y座標
            if(mode==0) {
                g.setColor(new Color(255,0,0)); //紅
                g.drawImage(roundedImage, holeX, holeY, roundedImage.getWidth(), roundedImage.getHeight() , this);
            } else if(mode==1) {
                g.setColor(new Color(0,0,255));//藍
                g.drawImage(roundedImage, holeX, holeY, roundedImage.getWidth(), roundedImage.getHeight() , this);
            } else if(mode==2) {
                g.setColor(new Color(128,0,128));//紫 
                g.drawImage(roundedImage, holeX, holeY, roundedImage.getWidth(), roundedImage.getHeight() , this);
            }
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
        if(hp <= 0) return true;
        else return false;
    }

    public void attack() {
        time.sec-=hp;
        hp = 0;
        //if(time.sec<=0) time.gameOver();
        hole[i].isRat = false;
        window.repaint(hole[i].x, hole[i].y, 100, 100);//清除攻擊完後的老鼠
        born();//攻擊後重生
    }

    public void born() {
        this.choosehole();
        this.mode=(int)(Math.random() * 3);
        this.during=3;
        hp = (int)(Math.random() * 5) + 1;
        window.repaint(hole[i].x, hole[i].y, 100, 100);//重繪新老鼠
    }
   
    public void reduceHp() {
        hp--;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1 && mode==0) {  // 左鍵
            if((hole[i].x - mx + 50) * (hole[i].x - mx + 50) + (hole[i].y - my + 50) * (hole[i].y - my + 50) <= 2500 && hp > 0) {
                System.out.println("hit");
                this.reduceHp();
                window.repaint(hole[i].x, hole[i].y, 100, 100);//打擊後的重繪
                if(this.dead()){
                    hole[i].isRat = false;
                    time.sec++;
                    window.finalScore++;
                }
            }
        }
        if(e.getButton() == MouseEvent.BUTTON3 && mode==1) {  // 右鍵
            if((hole[i].x - mx + 50) * (hole[i].x - mx + 50) + (hole[i].y - my + 50) * (hole[i].y - my + 50) <= 2500 && hp > 0) {
                System.out.println("hit");
                this.reduceHp();
                window.repaint(hole[i].x, hole[i].y, 100, 100);//打擊後的重繪
                if(this.dead()){
                    hole[i].isRat = false;
                    time.sec++;
                    window.finalScore+=3;
                }
            }
        }
    }

    boolean in=false;
    public void mouseMoved(MouseEvent e) {  
        int mx = e.getX();
        int my = e.getY();
        if(mode==2){
            if((hole[i].x - mx + 50) * (hole[i].x - mx + 50) + (hole[i].y - my + 50) * (hole[i].y - my + 50) <= 2500 && in==false &&hp>0) {
                in=true; 
            }
            if((hole[i].x - mx + 50) * (hole[i].x - mx + 50) + (hole[i].y - my + 50) * (hole[i].y - my + 50) > 2500 && in==true &&hp>0) {
                System.out.println("cut");
                in=false;
                this.reduceHp();
                window.repaint(hole[i].x, hole[i].y, 100, 100);//打擊後的重繪
                if(this.dead()){
                    hole[i].isRat = false;
                    time.sec++;
                    window.finalScore+=3;
                }
            }
        }
        
    }
}