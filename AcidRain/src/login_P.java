import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class login_P extends JPanel {
	ImageIcon icon = new ImageIcon("Main.gif");
	Image Mimg = icon.getImage();;
	Color color= new Color(0,0,102);
	JLabel title;
	JLabel ID,PW,Server;
	JPasswordField PWt;
	JTextField IDt;
	JButton Loginbt,Gbt,ERbt,Findbt;
	JRadioButton[] server = new JRadioButton[2];
	Connection con;
	Statement stmt;
	ResultSet rs;
	String sv=new String();
	public login_P(login_F mainFrame) {
		
		setLayout(null);
	
	title = new JLabel();
	title.setIcon(new ImageIcon("Mname.png"));
	title.setBounds(20,30,this.getWidth()+1800,this.getHeight()+150);
	add(title,BorderLayout.CENTER);
	 
	ID = new JLabel("ID");
	ID.setFont(new Font(ID.getText(),Font.BOLD,25));
	ID.setBounds(50,230,this.getWidth()+310,this.getHeight()+50);
	ID.setForeground(Color.white);
	add(ID);
	
	IDt = new JTextField();
	IDt.setBounds(50, 280, this.getWidth() + 350, this.getHeight() + 30);
	IDt.setBackground(Color.white);
	IDt.setForeground(Color.black);
	add(IDt);
	
	PW = new JLabel("PassWord");
	PW.setFont(new Font(ID.getText(), Font.BOLD, 25));
	PW.setBounds(50, 310, this.getWidth() + 310,
			this.getHeight() + 50);
	PW.setForeground(Color.white);
	add(PW);

	PWt = new JPasswordField();
	PWt.setBounds(50, 360, this.getWidth() + 350,
			this.getHeight() + 30);
	PWt.setBackground(Color.white);
	PWt.setForeground(Color.black);
	add(PWt);
	
	Findbt = new JButton("ID/PW 찾기");
	Findbt.setBounds(400, 470, this.getWidth()+310,this.getHeight()+40);
	Findbt.setFont(new Font(Findbt.getText(), Font.BOLD, 20));
	Findbt.setForeground(Color.yellow);
	Findbt.setBorder(new LineBorder(Color.white, 5));
	//Loginbt.setBackground(color);
	Findbt.setContentAreaFilled(false);
	add(Findbt);
	Findbt.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Find_F fp= new Find_F();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	Server = new JLabel("Server");
	Server.setFont(new Font(ID.getText(), Font.BOLD, 25));
	Server.setBounds(50, 390, this.getWidth() + 310,
			this.getHeight() + 50);
	Server.setForeground(Color.white);
	add(Server);
	
	ButtonGroup bg = new ButtonGroup();
	server[0] = new JRadioButton("Happy");
	server[0].setFont(new Font(server[0].getText(), Font.BOLD, 20));
	server[0].setForeground(Color.white);
	server[0].setBounds(70,435,100,20);
	server[0].setContentAreaFilled(false);
	server[0].addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			sv="Happy";
		}
		
	});
	server[1] = new JRadioButton("Dream");
	server[1].setFont(new Font(server[1].getText(), Font.BOLD, 20));
	server[1].setForeground(Color.white);
	server[1].setBounds(200,435,100,20);
	server[1].setContentAreaFilled(false);
	server[1].addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			sv="Dream";
		}});
	
	bg.add(server[0]);
	bg.add(server[1]);
	add(server[0]);
	add(server[1]);
	Loginbt = new JButton("Game Start");
	Loginbt.setBounds(450, 260, this.getWidth() + 260,
			this.getHeight() + 180);
	Loginbt.setFont(new Font(Loginbt.getText(), Font.BOLD, 30));
	Loginbt.setForeground(Color.white);
	Loginbt.setBorder(new LineBorder(Color.white, 5));
	//Loginbt.setBackground(color);
	Loginbt.setContentAreaFilled(false);
	add(Loginbt);
	Loginbt.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			con = makeConnection();
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("select * from members where id='"+IDt.getText()+"' and pw='"+String.valueOf(PWt.getPassword())+"' and server='"+sv+"';");
				if(rs.next()){
					String id = IDt.getText();
					String pw= String.valueOf(PWt.getPassword());
					String serv = sv;
				if(id.equals(rs.getString("id"))&&pw.equals(rs.getString("pw"))&&sv.equals(rs.getString("server"))){
						mainFrame.setVisible(false);
						new Inform_F(rs.getInt("num"));
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "로그인 실패");
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
	
	ERbt = new JButton("회원가입");
	ERbt.setBounds(50,470,this.getWidth()+310,this.getHeight()+40);
	ERbt.setFont(new Font(ERbt.getText(),Font.BOLD,20));
	ERbt.setForeground(Color.yellow);
	ERbt.setBorder(new LineBorder(Color.white, 5));
	ERbt.setContentAreaFilled(false);
	add(ERbt);
	ERbt.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				Join_F a = new Join_F();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	
	}
	
	public static Connection makeConnection() {
		// TODO Auto-generated method stub

		String url = "jdbc:mysql://localhost:3306/acidrain";
		String id = "root";
		String password = "onlyroot";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("데이터베이스 연결 성공");
		} catch (ClassNotFoundException exception) {
			JOptionPane.showMessageDialog(null, "로그인 실패");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"로그인 실패");
		}
		return con;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(Mimg, 0, 0, this.getWidth(), this.getHeight(), this); // 전경이미지 설정
																			
	}
}
