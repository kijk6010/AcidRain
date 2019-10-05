import javax.swing.JFrame;

public class login_F extends JFrame {
	public login_F(){
		setTitle("산성비");
		setLocation(600,100);//화면 열릴 위치 지정
		setSize(800,600);//화면 크기 지정
		
		add(new login_P(this));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); //화면 크기를 사용자 임의대로 지정하지 못하게함
		setVisible(true);
		
	}
}
