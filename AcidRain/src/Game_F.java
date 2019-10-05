import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Game_F extends JFrame implements ActionListener, FocusListener, KeyListener {

   int score = 0;
   int life = 5;
   ArrayList<MyWord> data = new ArrayList<MyWord>();// 내려오는 단어를 저장 하는 변수
   String words[] = { "hello", "world", "some", "time", "make", "part", "people", "apple", "banana", "cookie", "dead",
         "mother", "peace", "passion", "blossom", "sunshine", "smile", "love", "eternity", "gorgeous", "cherish", "destiny", "good", "freedom", "root", "only", "grace", "liberty", "tranquility", "rainbow", "moon", "sun",
         "warm", "socks", "fashion", "order", "rain", "two", "four", "seventeen", "dragon", "egg", "chicken","happy", "rainbow", "fabric", "acid", "one", "three", "five", "thirteem", "dinoaursaur", "fried", "candy", "jelly", "meat", "sausage", "vegetable", "cow", "pig", "rabit", "dog", "cat", "EunJi","JiHyun","LeeHyungWooKoSuNim"};
   JTextField tf = new JTextField(30);
   JButton btn = new JButton("Enter");
   JButton bt = new JButton();
   JTextArea scorebar = new JTextArea(2, 10);
   JTextArea lifebar = new JTextArea(2, 10);
   boolean focusYn = false;
   Connection con;
   Statement stmt;
   int mn;
   ///////////////////////////////////////////////////////////////////////
 public  Game_F(int x) throws SQLException{
	 mn=x;
      JPanel panel = new JPanel() {
         public void paint(Graphics g) {
            Image img = createImage(getWidth(), getHeight());
            Graphics bg = img.getGraphics();
            for (int i = 0; i < data.size(); i++) {
               bg.drawString(data.get(i).word, data.get(i).position.x, data.get(i).position.y);
            }
            g.drawImage(img, 0, 0, this);
         }

      };

      JPanel bot = new JPanel();
      JPanel side = new JPanel();
      Timer loop = new Timer();
      loop.schedule(new TimerTask() {

         @Override
         public void run() {
            // TODO Auto-generated method stub
            for (int i = 0; i < data.size(); i++) {
               data.get(i).position.y += data.get(i).speed;
               if (data.get(i).position.y > getHeight()) {
                  data.remove(i);
                  life--;
                  lifebar.setText("생명: " + life);
                  if (life == 0) {
                	  int k;
                	  String s="update score set bestscore="+score+" where num="+mn+" and bestscore<"+score+";";
                	  
            			try {
            				con = makeConnection();
            				stmt = con.createStatement();
            				k = stmt.executeUpdate(s);
            				if (k == 1){
            					System.out.println("레코드 업데이트 성공");
            					new GOver_F(score,mn);
            				}
            			    else{
            			    	System.out.println("레코드 업데이트 실패");
            			    	new GOver_F(score,mn);
            			    }
            			   } catch (SQLException e1) {
            				// TODO Auto-generated catch block
            				e1.printStackTrace();
            			}
            			dispose();
                  }
               }
            }
            repaint();
         }

      }, 0, 40);
      Timer createWord = new Timer();
      createWord.schedule(new TimerTask() {
         @Override
         public void run() {
            MyWord myword = new MyWord();// 단어를 하나 만들고

            myword.position.x = (int) (Math.random() * (getWidth() - 300) + 50);// 화면안에 들어오게 x값 설정
            myword.position.y = 0;
            myword.speed = (int) (Math.random() * 2 + 1);// 내려오는 속도
            myword.word = words[(int) (Math.random() * words.length)];// 저장되어 있는 단어들 중에 추출
            data.add(myword);// 리스트에 저장
         }
      }, 0, 700);
      bot.add(tf);
      bot.add(btn);
      scorebar.setText(score + "점");
      lifebar.setText("생명: " + life);
      side.add(scorebar);
      side.add(lifebar);
      this.setLayout(new BorderLayout());
      this.add(panel, BorderLayout.CENTER);
      this.add(bot, BorderLayout.SOUTH);
      this.add(side, BorderLayout.EAST);
      this.setTitle("산성비게임!");
      this.setVisible(true);
      this.setSize(1000, 800);
      this.setLocation(500, 100);
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // tf.addActionListener(this);
      tf.addKeyListener(this);
      btn.addActionListener(this);
      tf.addFocusListener(this);
   }

   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      for (int i = 0; i < data.size(); i++) {
         if (data.get(i).word.equals(tf.getText())) {
            data.remove(i);
            score += 10;
            scorebar.setText(score + "점");
         }
      }
      tf.setText("");
     
   }

   public void focusGained(FocusEvent arg0) {
      // TODO Auto-generated method stub
      focusYn = true;
   }

   public void focusLost(FocusEvent arg0) {
      // TODO Auto-generated method stub
      focusYn = false;
   }

   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      if (focusYn == true) {
         if (e.getKeyCode() == KeyEvent.VK_ENTER)
            btn.doClick();
      }
   }

   public void keyReleased(KeyEvent arg0) {
      // TODO Auto-generated method stub
   }

   public void keyTyped(KeyEvent arg0) {
      // TODO Auto-generated method stub
   }

public static Connection makeConnection() {
	String url = "jdbc:mysql://localhost:3306/acidrain";
	String id = "root";
	String password = "onlyroot";
	Connection con = null;
	  try {
	   Class.forName("com.mysql.jdbc.Driver");
	   System.out.println("드라이버 적재 성공");
	   con = DriverManager.getConnection(url, id, password);
	   System.out.println("데이터베이스 연결 성공");
	  } catch (ClassNotFoundException e) {
	   System.out.println("드라이버를 찾을 수 없습니다.");
	  } catch (SQLException e) {
	   System.out.println("연결에 실패하였습니다.");
	  }
	  return con;
}
}
class MyWord {
   public Point position = new Point();// 단어의 위치
   public String word;// 단어
   public int speed;// 단어의 속도
}