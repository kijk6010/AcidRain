import javax.swing.JFrame;
import javax.swing.JLabel;

public class Inform_F extends JFrame {
	public Inform_F(int x){
		setTitle("�꼺�� ���� ����");
		setLocation(500,100);
		setSize(1000,800);
	
		add(new Inform_P(this,x));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false); 
		setVisible(true);
	}
}
