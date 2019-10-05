import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Inform_P extends JPanel{
	JLabel one,two,three,four,five,six,tip;
	JButton skipbt;
	ImageIcon icon = new ImageIcon("Main.gif");
	Image Mimg = icon.getImage();
	Inform_P(Inform_F frame,int x){
		setLayout(null);
		
		JLabel title = new JLabel();
		title.setIcon(new ImageIcon("info.png"));
		title.setBounds(350,20,this.getWidth()+1800,this.getHeight()+150);
		add(title,BorderLayout.CENTER);
	
		one = new JLabel("1.화면 위쪽에서 글자들이 떨어집니다");
		one.setFont(new Font(one.getText(),Font.BOLD,25));
		one.setBounds(110,200,1000,40);
		one.setForeground(Color.white);
		add(one);
		
		two = new JLabel("2.글자 입력란에 떨어지는 글자를 입력합니다");
		two.setFont(new Font(two.getText(),Font.BOLD,25));
		two.setBounds(110,260,1000,40);
		two.setForeground(Color.white);
		add(two);
		
		three = new JLabel("3.엔터를 누르면 입력한 글자와 함께 떨어지는 글자가 사라집니다");
		three.setFont(new Font(two.getText(),Font.BOLD,25));
		three.setBounds(110,320,1000,40);
		three.setForeground(Color.white);
		add(three);
		
		four = new JLabel("4.글자가 사라지면 10점이 증가하게 됩니다");
		four.setFont(new Font(two.getText(),Font.BOLD,25));
		four.setBounds(110,380,1000,40);
		four.setForeground(Color.white);
		add(four);
		
		five = new JLabel("5.3만큼의 생명력이 주어지고 글자를 끝내 쓰지 못했을 때 감소합니다");
		five.setFont(new Font(two.getText(),Font.BOLD,25));
		five.setBounds(110,440,1000,40);
		five.setForeground(Color.white);
		add(five);
		
		six = new JLabel("6.생명력이 0이 되었을 때 게임이 종료 됩니다");
		six.setFont(new Font(two.getText(),Font.BOLD,25));
		six.setBounds(110,500,1000,40);
		six.setForeground(Color.white);
		add(six);
		
		tip = new JLabel("Tip.중복되는 글자를 입력시 중복된 개수만큼 점수가 곱하여 올라감");
		tip.setFont(new Font(two.getText(),Font.BOLD,25));
		tip.setBounds(110,600,1000,40);
		tip.setForeground(Color.cyan);
		add(tip);
		
		skipbt = new JButton("SKIP");
		skipbt.setBounds(850, 700,100,60);
		skipbt.setFont(new Font(skipbt.getText(), Font.BOLD, 20));
		skipbt.setForeground(Color.yellow);
		skipbt.setBorder(new LineBorder(Color.pink, 5));
		skipbt.setContentAreaFilled(false);
		add(skipbt);
		skipbt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Game_F(x);
					frame.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Mimg, 0, 0, this.getWidth(), this.getHeight(), this); // 배경이미지 설정
																			
	}

}
