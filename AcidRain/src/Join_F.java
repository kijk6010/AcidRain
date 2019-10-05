import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Join_F extends JFrame implements ActionListener {
	Color color= new Color(63, 83, 106);
	JRadioButton[] rb = new JRadioButton[2];
	JButton ccbt,joinbt;
	JTextField It,Pwt,Nmt;
	Statement stmt;
	ResultSet rs;
	String sv = new String();
	public Join_F() throws SQLException{
		setTitle("ȸ�� ����");
		setLocation(800,200);
		setSize(400,400);
		setResizable(false); 
		Connection con = makeConnection();
		stmt=con.createStatement();
		rs = stmt.executeQuery("select * from members");
		
		JPanel jp=new JPanel();
		jp.setBackground(color);
		
		JLabel IL = new JLabel("ID �Է�");
		IL.setFont(new Font(IL.getText(),Font.BOLD,20));
		IL.setBounds(30,40,100,50);
		IL.setForeground(Color.white);
		add(IL);
		
		It = new JTextField();
		It.setBounds(150, 50, 200, 30);
		It.setBackground(Color.white);
		It.setForeground(Color.black);
		add(It);
		
		JLabel PwL = new JLabel("PW �Է�");
		PwL.setFont(new Font(PwL.getText(),Font.BOLD,20));
		PwL.setBounds(30,92,100,50);
		PwL.setForeground(Color.white);
		add(PwL);
		
		Pwt = new JTextField();
		Pwt.setBounds(150, 100, 200, 30);
		Pwt.setBackground(Color.white);
		Pwt.setForeground(Color.black);
		add(Pwt);
		
		JLabel NmL = new JLabel("�̸�");
		NmL.setFont(new Font(NmL.getText(),Font.BOLD,20));
		NmL.setBounds(30,144,100,50);
		NmL.setForeground(Color.white);
		add(NmL);
		
		Nmt = new JTextField();
		Nmt.setBounds(150, 150, 200, 30);
		Nmt.setBackground(Color.white);
		Nmt.setForeground(Color.black);
		add(Nmt);
		
		JLabel SvL = new JLabel("����");
		SvL.setFont(new Font(SvL.getText(),Font.BOLD,20));
		SvL.setBounds(30,196,100,50);
		SvL.setForeground(Color.white);
		add(SvL);
	
		ButtonGroup bg = new ButtonGroup();
		rb[0] = new JRadioButton("Happy");
		rb[0].setFont(new Font(rb[0].getText(), Font.BOLD, 20));
		rb[0].setForeground(Color.white);
		rb[0].setBounds(150, 210, 100, 20);
		rb[0].setContentAreaFilled(false);
		rb[0].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sv="Happy";
			}
			
		});
		rb[1] = new JRadioButton("Dream");
		rb[1].setFont(new Font(rb[1].getText(), Font.BOLD, 20));
		rb[1].setForeground(Color.white);
		rb[1].setBounds(250, 210, 100, 20);
		rb[1].setContentAreaFilled(false);
		rb[1].addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sv="Dream";
			}});
		bg.add(rb[0]);
		bg.add(rb[1]);
		add(rb[0]);
		add(rb[1]);
		
		joinbt = new JButton("�����ϱ�");
		joinbt.setBounds(30,270,150,50);
		joinbt.setFont(new Font(joinbt.getText(),Font.BOLD,20));
		joinbt.setForeground(Color.white);
		joinbt.setBorder(new LineBorder(Color.white, 3));
		joinbt.setContentAreaFilled(false);
		joinbt.addActionListener(this);
		add(joinbt);
		
		
		ccbt = new JButton("�ٽþ���");
		ccbt.setBounds(220,270,150,50);
		ccbt.setFont(new Font(ccbt.getText(),Font.BOLD,20));
		ccbt.setForeground(Color.white);
		ccbt.setBorder(new LineBorder(Color.white, 3));
		ccbt.setContentAreaFilled(false);
		ccbt.addActionListener(this);
		add(ccbt);
			
		add(jp);
		setVisible(true);
	}

	private Connection makeConnection() {
		String url = "jdbc:mysql://localhost:3306/acidrain";
		String id = "root";
		String password = "onlyroot";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� ���� ����");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("�����ͺ��̽� ���� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
		} catch (SQLException e) {
			System.out.println("���ῡ �����Ͽ����ϴ�.");
		}
		return con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==joinbt){
			String s = "insert into members (name,id,pw,server) values";
			s += "('" + Nmt.getText() + "','" + It.getText() + "','"
					+ Pwt.getText() + "','" + sv + "');";
			int i;
			try {
				i = stmt.executeUpdate(s);
				if (i == 1){
					System.out.println("���� �߰� ����");
					JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.");
				}
				else
					System.out.println("���� �߰� ����");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			s = "insert into score(id,bestscore) VALUES ('"+It.getText()+"',0);";
			i=0;
					try {
						i=stmt.executeUpdate(s);
						if(i==1){
							System.out.println("���ھ �߰� ����");
							setVisible(false);
						}
						else
							System.out.println("�߰� ����");
							
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		}
		if(e.getSource()==ccbt){
			It.setText("");
			Pwt.setText("");
			Nmt.setText("");
			rb[0].setSelected(false);
			rb[1].setSelected(false);
		}
	}
}
