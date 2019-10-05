import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class GOver_P extends JPanel{
   ImageIcon icon=new ImageIcon("Main.gif");
   Image bg=icon.getImage();
   JLabel rs=new JLabel("Score");
   JLabel bs=new JLabel("Best");
   JLabel score,bstsc;
   JTextArea rst = new JTextArea(10,5);
   JTextArea bst= new JTextArea(10,5);
   JButton bt;
   
	Connection con;
	Statement stmt;
	ResultSet res;
	int mn;
	int bstscore;
   GOver_P(GOver_F frame,int sc,int m){
	    this.setLayout(null);
	   
	   mn=m;
      JLabel go= new JLabel();
      go.setIcon(new ImageIcon("over.png"));
      go.setBounds(150,100,750,100);
      rs.setFont(new Font(rs.getText(),Font.BOLD,40));
      rs.setForeground(Color.WHITE);
      rs.setBounds(220,250,200,60);
      bs.setFont(new Font(bs.getText(),Font.BOLD,40));
      bs.setForeground(Color.WHITE);
      bs.setBounds(220,450,200,60);
      
      add(go);
      add(rs);
      add(bs);
      
      score = new JLabel("이번 점수는 "+sc+"점 입니다!!!");
      score.setFont(new Font( score.getText(),Font.BOLD,40));
      score.setBounds(250,330,600,60);
      score.setForeground(Color.yellow);
		add(score);
   
		try {
			con = makeConnection();
			stmt = con.createStatement();
			res = stmt.executeQuery("select * from score where num="+mn+";");
			if(res.next()){
				if(mn==res.getInt("num")){
				 bstscore =res.getInt("bestscore");
			}
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      bstsc= new JLabel("최고 점수는"+bstscore+"점 입니다!!");
      bstsc.setFont(new Font( bstsc.getText(),Font.BOLD,40));
      bstsc.setBounds(250,530,600,60);
      bstsc.setForeground(Color.yellow);
		add(bstsc);
    
     bt = new JButton("LogOut");
     bt.setBounds(400, 650,140,60);
     bt.setFont(new Font(bt.getText(), Font.BOLD, 30));
     bt.setForeground(Color.yellow);
     bt.setBorder(new LineBorder(Color.pink, 5));
     bt.setContentAreaFilled(false);
		add(bt);
		bt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new login_F();
				
			}
		});
  
   }
   public static Connection makeConnection(){
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
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), this); // 배경이미지 설정
                                                         
   }
   }

