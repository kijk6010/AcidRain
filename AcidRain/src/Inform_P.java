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
	
		one = new JLabel("1.ȭ�� ���ʿ��� ���ڵ��� �������ϴ�");
		one.setFont(new Font(one.getText(),Font.BOLD,25));
		one.setBounds(110,200,1000,40);
		one.setForeground(Color.white);
		add(one);
		
		two = new JLabel("2.���� �Է¶��� �������� ���ڸ� �Է��մϴ�");
		two.setFont(new Font(two.getText(),Font.BOLD,25));
		two.setBounds(110,260,1000,40);
		two.setForeground(Color.white);
		add(two);
		
		three = new JLabel("3.���͸� ������ �Է��� ���ڿ� �Բ� �������� ���ڰ� ������ϴ�");
		three.setFont(new Font(two.getText(),Font.BOLD,25));
		three.setBounds(110,320,1000,40);
		three.setForeground(Color.white);
		add(three);
		
		four = new JLabel("4.���ڰ� ������� 10���� �����ϰ� �˴ϴ�");
		four.setFont(new Font(two.getText(),Font.BOLD,25));
		four.setBounds(110,380,1000,40);
		four.setForeground(Color.white);
		add(four);
		
		five = new JLabel("5.3��ŭ�� ������� �־����� ���ڸ� ���� ���� ������ �� �����մϴ�");
		five.setFont(new Font(two.getText(),Font.BOLD,25));
		five.setBounds(110,440,1000,40);
		five.setForeground(Color.white);
		add(five);
		
		six = new JLabel("6.������� 0�� �Ǿ��� �� ������ ���� �˴ϴ�");
		six.setFont(new Font(two.getText(),Font.BOLD,25));
		six.setBounds(110,500,1000,40);
		six.setForeground(Color.white);
		add(six);
		
		tip = new JLabel("Tip.�ߺ��Ǵ� ���ڸ� �Է½� �ߺ��� ������ŭ ������ ���Ͽ� �ö�");
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
		g.drawImage(Mimg, 0, 0, this.getWidth(), this.getHeight(), this); // ����̹��� ����
																			
	}

}
