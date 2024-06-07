import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class Hole extends JPanel {
    protected int x;
    protected int y;
    boolean isRat = false;

    public Hole(Window w) {
        this.x = 0;
        this.y = 0;
    }
    
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void paint(Graphics g) {
        //g.drawOval(this.x, this.y, 100,100);  // 畫外緣
        // g.drawRect(x, y, 100, 100);
    }

    public void bossPaint(Graphics g) {
        //g.drawOval(this.x, this.y, 150,150);  // 畫外緣
        // g.drawRect(x, y, 150, 150);
    }
}