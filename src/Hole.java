import javax.swing.JPanel;

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
}