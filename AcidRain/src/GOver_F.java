import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GOver_F extends JFrame{
   Color color= new Color(63, 83, 106);
   
   GOver_F(int sc,int m){    
      this.setTitle("산성비게이무!");
      this.setResizable(false);
      this.setSize(1000,800);
      this.setLocation(500,100);
      this.add(new GOver_P(this,sc,m));
      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   


}