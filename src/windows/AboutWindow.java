package windows;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class AboutWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	AboutWindow(){
		this.setSize(900, 650);
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("About");
		ImageIcon learnIcon = new ImageIcon("icons/logo.png");
		this.setIconImage(learnIcon.getImage());
		this.setLayout(null);
		this.setResizable(false);
		//center the window
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		//draw background
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//draw programmer
		g2d.setColor(new Color(74, 138, 240));
		g2d.setFont(new Font("Berlin Sans FB", Font.PLAIN, 50));
		g2d.drawString("PROGRAMMER", 50, 100);
		
		//draw Caesar James LEE
		g2d.setColor(new Color(80, 242, 212));
		g2d.setFont(new Font("Algerian", Font.BOLD, 39));
		g2d.drawString("Caesar James LEE", 450, 150);
		
		//draw UI designer
		g2d.setColor(new Color(74, 138, 240));
		g2d.setFont(new Font("Berlin Sans FB", Font.PLAIN, 50));
		g2d.drawString("UI DESIGNER", 50, 200);
		
		//draw Caesar James LEE
		g2d.setColor(new Color(80, 242, 212));
		g2d.setFont(new Font("Algerian", Font.BOLD, 39));
		g2d.drawString("Caesar James LEE", 450, 250);
		
		//draw icon drawer
		g2d.setColor(new Color(74, 138, 240));
		g2d.setFont(new Font("Berlin Sans FB", Font.PLAIN, 50));
		g2d.drawString("ICON DRAWER", 50, 300);
		
		//draw Caesar James LEE
		g2d.setColor(new Color(80, 242, 212));
		g2d.setFont(new Font("Algerian", Font.BOLD, 39));
		g2d.drawString("caesar james lee", 450, 350);
		
		//draw data source
		g2d.setColor(new Color(74, 138, 240));
		g2d.setFont(new Font("Berlin Sans FB", Font.PLAIN, 50));
		g2d.drawString("DATA SOURCE", 50, 400);
		
		//draw "504-Absolutely Essential Words"
		g2d.setColor(new Color(80, 242, 212));
		g2d.setFont(new Font("Algerian", Font.ITALIC, 39));
		g2d.drawString("\"504-Absolutely Essential Words\"", 140, 450);
		
		//draw Dr.eye
		g2d.setColor(new Color(80, 242, 212));
		g2d.setFont(new Font("Algerian", Font.ITALIC, 39));
		g2d.drawString("Dr.eye", 690, 500);
		
		//draw code review
		g2d.setColor(new Color(74, 138, 240));
		g2d.setFont(new Font("Berlin Sans FB", Font.PLAIN, 50));
		g2d.drawString("CODE REVIEW", 50, 550);
		
		//draw URL
		g2d.setColor(new Color(80, 242, 212));
		g2d.setFont(new Font("Arial", Font.BOLD, 21));
		g2d.drawString("https://github.com/CaesarLi-WantToBeAFreeMan/504-Absolutely-Essential-Words", 50, 600);
		
	}
}