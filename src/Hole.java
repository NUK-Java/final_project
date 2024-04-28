import javax.swing.JPanel;
import java.awt.Graphics2D;

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
    
    public void paint(Graphics2D g) {
        g.drawOval(this.x, this.y, 100,100);  // 畫外緣
    }
}
