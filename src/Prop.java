import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Prop {
    private Window window;
    private int x, y;
    private int buttonWidth = 80;
    private int buttonHeight = 40;

    public Prop(Window window) {
        this.window = window;
        this.x = 50;
        this.y = 50;
    }

    public void paint(Graphics2D g2d) {
        g2d.drawRect(x, y, buttonWidth, buttonHeight);
        g2d.drawRect(x, y + 100, buttonWidth, buttonHeight);
        g2d.drawRect(x, y + 200, buttonWidth, buttonHeight);
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // 檢查按鈕，功能寫這裡
        if (mx >= x && mx <= x + buttonWidth && my >= y && my <= y + buttonHeight) {
            System.out.println("Button 1 clicked!");
        } else if (mx >= x + 100 && mx <= x + 100 + buttonWidth && my >= y && my <= y + buttonHeight) {
            System.out.println("Button 2 clicked!");
        } else if (mx >= x + 200 && mx <= x + 200 + buttonWidth && my >= y && my <= y + buttonHeight) {
            System.out.println("Button 3 clicked!");
        }
    }
}