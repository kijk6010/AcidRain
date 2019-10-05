import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Find_F extends JFrame implements ActionListener{
	Color color = new Color(63,83,106);

	JButton FIDbt,FPWbt;
	JTextField IdNmt,PwNmt,PwIdt;
	JLabel result1, result2;
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	public Find_F() throws SQLException{
		setTitle("ID/PW 찾기");
		setLocation(700,200);
		setSize(600,500);
		setResizable(false);
		
		JPanel jp = new JPanel();
		jp.setBackground(color);
		
		JLabel FIDL = new JLabel("ID 찾기");
		FIDL.setFont(new Font(FIDL.getText(),Font.BOLD,30));
		FIDL.setBounds(30,40,120,50);
		FIDL.setForeground(Color.white);
		add(FIDL);
		
		JLabel FIL = new JLabel("이름 입력 :");
		FIL.setFont(new Font(FIL.getText(),Font.BOLD,20));
		FIL.setBounds(30,90,120,50);
		FIL.setForeground(Color.white);
		add(FIL);
		
		IdNmt = new JTextField();
		IdNmt.setBounds(150, 100, 200, 30);
		IdNmt.setBackground(Color.white);
		IdNmt.setForeground(Color.black);
		add(IdNmt);
		
		FIDbt = new JButton("ID찾기");
		FIDbt.setBounds(400, 90, 150, 50);
		FIDbt.setFont(new Font(FIDbt.getText(), Font.BOLD, 20));
		FIDbt.setForeground(Color.yellow);
		FIDbt.setBorder(new LineBorder(Color.white, 5));
		FIDbt.setContentAreaFilled(false);
		add(FIDbt);
		FIDbt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					con = makeConnection();
					stmt = con.createStatement();
					rs = stmt.executeQuery("select * from members where name='"+IdNmt.getText()+"';");
					if(rs.next()){
						String name = IdNmt.getText();
						if(name.equals(rs.getString("name"))){
							result1.setText("ID : "+rs.getString("id"));
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "정보가 없습니다.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		result1 = new JLabel();
		result1.setFont(new Font(result1.getText(),Font.BOLD,30));
		result1.setBounds(180,150,300,40);
		result1.setForeground(Color.yellow);
		add(result1);
		JLabel A = new JLabel("--------------------------------------------------");
		A.setFont(new Font(A.getText(),Font.BOLD,30));
		A.setBounds(30,180,600,50);
		A.setForeground(Color.white);
		add(A);
		
		JLabel FPWL = new JLabel("PW 찾기");
		FPWL.setFont(new Font(FIDL.getText(),Font.BOLD,30));
		FPWL.setBounds(30,230,130,50);
		FPWL.setForeground(Color.white);
		add(FPWL);
		
	
		
		JLabel PwL = new JLabel("ID 입력 :");
		PwL.setFont(new Font(PwL.getText(),Font.BOLD,20));
		PwL.setBounds(30,280,120,50);
		PwL.setForeground(Color.white);
		add(PwL);
		
		PwIdt = new JTextField();
		PwIdt.setBounds(150, 290, 200, 30);
		PwIdt.setBackground(Color.white);
		PwIdt.setForeground(Color.black);
		add(PwIdt);
		
		JLabel PwL2 = new JLabel("이름 입력 :");
		PwL2.setFont(new Font(PwL2.getText(),Font.BOLD,20));
		PwL2.setBounds(30,320,120,50);
		PwL2.setForeground(Color.white);
		add(PwL2);
		
		PwNmt = new JTextField();
		PwNmt.setBounds(150, 330, 200, 30);
		PwNmt.setBackground(Color.white);
		PwNmt.setForeground(Color.black);
		add(PwNmt);
		
		FPWbt = new JButton("PW찾기");
		FPWbt.setBounds(400, 300, 150, 50);
		FPWbt.setFont(new Font(FPWbt.getText(), Font.BOLD, 20));
		FPWbt.setForeground(Color.yellow);
		FPWbt.setBorder(new LineBorder(Color.white, 5));
		FPWbt.setContentAreaFilled(false);
		add(FPWbt);
		FPWbt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					con = makeConnection();
					stmt = con.createStatement();
					rs = stmt.executeQuery("select * from members where name='"+PwNmt.getText()+"' and id='"+PwIdt.getText()+"';");
					if(rs.next()){
						String name = PwNmt.getText();
						String id = PwIdt.getText();
						if(name.equals(rs.getString("name"))&&id.equals(rs.getString("id"))){
							result2.setText("PW : "+rs.getString("pw"));
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "정보가 없습니다.");
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		result2 = new JLabel();
		result2.setFont(new Font(result2.getText(),Font.BOLD,30));
		result2.setBounds(180,380,300,50);
		result2.setForeground(color.yellow);
		add(result2);
		
		add(jp);
		setVisible(true);
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
