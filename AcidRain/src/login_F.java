import javax.swing.JFrame;

public class login_F extends JFrame {
	public login_F(){
		setTitle("�꼺��");
		setLocation(600,100);//ȭ�� ���� ��ġ ����
		setSize(800,600);//ȭ�� ũ�� ����
		
		add(new login_P(this));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); //ȭ�� ũ�⸦ ����� ���Ǵ�� �������� ���ϰ���
		setVisible(true);
		
	}
}
