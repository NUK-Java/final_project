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
    JLabel bgLabel,bgLabel1,titlelabel;
    ImageIcon background;
    Music music = new Music("./src/music/UI.mp3");

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
        start.setBounds(280, 430, 100,30); // 設定按鈕的位置和大小
        start.addActionListener(this);

        rule = new JButton("遊戲規則");
        rule.setBounds(420, 430, 100,30); // 設定按鈕的位置和大小
        rule.addActionListener(this);

        titlelabel = new JLabel(); 
        titlelabel.setIcon(new ImageIcon("./src/pic/title.png")); //把背景圖顯示在Label中
        titlelabel.setBounds(100, 100, 800, 100);
        this.getLayeredPane().add(titlelabel); // 把含有背景圖之Label加到視窗的最底層以顯示背景圖

        bgLabel = new JLabel(); 
        bgLabel.setIcon(new ImageIcon("./src/pic/UI_bg.jpg")); //把背景圖顯示在Label中
        bgLabel.setBounds(0, 0, 800, 560);
        this.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE)); // 把含有背景圖之Label加到視窗的最底層以顯示背景圖

        JLabel wordLabel = new JLabel
        ("<html><br><font color='white' style='font-weight:bold; font-size:14px; font-family:Microsoft JhengHei;'>"+
        "你昔日生活的城市被老鼠大舉入侵，街道、城鎮、皇宮被老鼠們佔領<br>"+
        "，到處散布著有毒的煙霧，你能感受到生命因為毒氣漸漸流逝，但你<br>"+
        "別無選擇，你必須擊殺鼠王，拯救這個城市，奪回曾經的家，你毅然<br>"+
        "決然的向皇宮走去......</font></html>");
        wordLabel.setBounds(120, 0, 800, 560); 
        this.getLayeredPane().add(wordLabel);

        c.add(start);
        c.add(rule);
        c.setLayout(null); // 設定佈局管理器為 null

        music.play();
    
    }
    
    JFrame rule_page;
    public void showRule(){
        rule_page = new JFrame("遊戲規則");
        rule_page.setSize(800,560);  // 設定size，顯示出去
        rule_page.setVisible(true);
        rule_page.setResizable(false);
        rule_page.setLocationRelativeTo(null); // 讓視窗置中
        rule_page.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        bgLabel1 = new JLabel(); 
        bgLabel1.setIcon(new ImageIcon("./src/pic/rule.jpg")); //把背景圖顯示在Label中
        bgLabel1.setBounds(-50, 0, 850, 560);
        
        JLabel wordLabel1 = new JLabel
        ("<html><br><font color='black' style='font-weight:bold; font-size:14px; font-family:Microsoft JhengHei;'>"+
        "規則說明:<br>"+

        "1.上方為你的生命值，會隨著時間一秒一秒流逝，每秒生命值-1，被老鼠攻擊時<br>也會扣血，擊殺小老鼠可以回復+1點生命。<br>"+

        "2.道具有三種:<br>"+

        "(1)火力加倍:10sec內，攻擊力翻倍。<br> (2)得分加倍:20sec內，殺死老鼠的得分翻倍。<br> (3)時間暫停:10sec內，你的生命值不會隨著時間流逝。<br>"+

        "3.擊打模式有四種:<br>"+

        "(1)紅色為左鍵打擊。     (2)藍色為右鍵打擊。<br> (3)黃色為滑過切砍。     (4)白色為無敵，所有攻擊無效。<br>"+

        "4.小老鼠與老鼠頭目會在一定時間後攻擊你並逃走，傷害值為老鼠剩下的生命值。<br>"+

        "5.小老鼠每1秒出現1隻，擊殺後生命值+1與總分+1，過了3秒未擊殺會攻擊你並逃走。<br>"+

        "6.老鼠頭目每30秒出現，有2種隨機擊打模式，擊殺後總分+20，過了20秒未擊殺<br>會攻擊你並逃走。<br>"+

        "7.老鼠魔王第90秒出現，有4種隨機擊打模式，並且每5秒會攻擊10生命、回復<br>自身1~5生命，擊殺後總分+50。<br>"+

        "8.捕獸夾每4秒出現，左鍵觸發後會使生命值-10，持續3秒後會消失。<br>"+

        "9.當你的生命值歸零，或老鼠魔王死後，遊戲結束，跳出總分，<br>總分計算方式為：打死的老鼠分數 + 存活時間 + 剩餘生命值<br>");
        wordLabel1.setBounds(40, -30, 800, 560); 

        Container rc = rule_page.getContentPane();
        rc.setLayout(new FlowLayout());
        rc.add(wordLabel1);
        rc.add(bgLabel1, new Integer(Integer.MIN_VALUE)); // 把含有背景圖之Label加到視窗的最底層以顯示背景圖
        rc.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==start){
            Window game = new Window();
            music.stop();  //關掉音樂
            this.dispose(); //關掉選單
        }
        if (e.getSource()==rule){
            this.showRule();
        }
    }
}
