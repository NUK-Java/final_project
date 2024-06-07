import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame implements ActionListener{
    Container c;
    JPanel imagePanel;
    JButton start,rule;
    JLabel bgLabel;
    ImageIcon background;

    UI(){
        super("選單");
        setSize(800,560);  // 設定size，顯示出去
        setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // 讓視窗置中
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        imagePanel = (JPanel) this.getContentPane(); // 把内容視窗轉為JPanel，否則不能使用setOpaque()來使視窗變成透明
        imagePanel.setOpaque(false); // 使視窗變成透明 才可以放背景圖 不然會被白色蓋掉

        c=getContentPane();//取得ContentPane
        //設定版面設定
        c.setLayout(new FlowLayout());//設定版面設定

        //初始化UI元件
        start = new JButton("開始");
        start.setBounds(280, 460, 100,30); // 設定按鈕的位置和大小
        start.addActionListener(this);

        rule = new JButton("遊戲規則");
        rule.setBounds(420, 460, 100,30); // 設定按鈕的位置和大小
        rule.addActionListener(this);

        bgLabel = new JLabel(); 
        bgLabel.setIcon(new ImageIcon("./src/UI_bg.jpg")); //把背景圖顯示在Label中
        bgLabel.setBounds(0, 0, 800, 560);

        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); // 把含有背景圖之Label加到視窗的最底層以顯示背景圖

        c.add(start);
        c.add(rule);
        c.setLayout(null); // 設定佈局管理器為 null
    }
    
    JFrame rule_page;
    public void showRule(){
        rule_page = new JFrame("遊戲規則");
        rule_page.setSize(800,560);  // 設定size，顯示出去
        rule_page.setVisible(true);
        rule_page.setResizable(false);
        rule_page.setLocationRelativeTo(null); // 讓視窗置中
        rule_page.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JLabel wordLabel = new JLabel("以下是遊戲規則：");
        wordLabel.setBounds(10, 10, 100, 20); 

        Container rc = rule_page.getContentPane();
        rc.setLayout(new FlowLayout());
        rc.add(wordLabel);
        rc.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==start){
            Window game = new Window();
            //關掉選單
            this.dispose();
            //rule_page.dispose();
        }
        if (e.getSource()==rule){
            this.showRule();
        }
    }
}
