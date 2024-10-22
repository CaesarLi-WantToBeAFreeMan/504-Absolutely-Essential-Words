package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import data.User;

public class HomeWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private static User user = new User();
	private Menus menu = new Menus(this);
	
	public HomeWindow() {
		this.setSize(1200, 700);
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("504-Absolutely Essential Words");
		ImageIcon TOEFLIcon = new ImageIcon("icons/logo.png");
		this.setIconImage(TOEFLIcon.getImage());
		this.setLayout(null);
		this.setResizable(false);
		
		//center the window
		this.setLocationRelativeTo(null);
		
		//learn container
		JPanel learnContainer = new JPanel();
		learnContainer.setOpaque(true);
		learnContainer.setBackground(new Color(88, 57, 39));
		learnContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		learnContainer.setBounds(475, 400, 250, 50);
		
		//learn icon
		JLabel learnLabel = new JLabel();
		ImageIcon learnIcon = new ImageIcon("icons/logo.png");
		Image learnImage = learnIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		learnIcon = new ImageIcon(learnImage);
		learnLabel.setIcon(learnIcon);
		learnLabel.setVerticalAlignment(JLabel.CENTER);
		learnLabel.setHorizontalAlignment(JLabel.LEFT);
		
		//learn button
		JButton learnButton = new JButton("learn");
		learnButton.setPreferredSize(new Dimension(180, 50));
		learnButton.setFocusable(false);
		learnButton.setForeground(new Color(108, 184, 17));
		learnButton.setBackground(null);
		learnButton.setFont(new Font("Cooper Black", Font.BOLD, 50));
		learnButton.addActionListener(e -> {
			new LessonsWindow();
			this.dispose();
		});
		
		learnContainer.add(learnLabel);
		learnContainer.add(learnButton);
		
		//settings container
		JPanel settingsContainer = new JPanel();
		settingsContainer.setOpaque(true);
		settingsContainer.setBackground(new Color(88, 57, 39));
		settingsContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		settingsContainer.setBounds(475, 500, 250, 50);
		
		//settings icon
		JLabel settingsLabel = new JLabel();
		ImageIcon settingsIcon = new ImageIcon("icons/settings.png");
		Image settingsImage = settingsIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		settingsIcon = new ImageIcon(settingsImage);
		settingsLabel.setIcon(settingsIcon);
		settingsLabel.setVerticalAlignment(JLabel.CENTER);
		settingsLabel.setHorizontalAlignment(JLabel.LEFT);
		
		//settings button
		JButton settingsButton = new JButton("settings");
		settingsButton.setPreferredSize(new Dimension(180, 50));
		settingsButton.setFocusable(false);
		settingsButton.setForeground(new Color(108, 184, 17));
		settingsButton.setBackground(null);
		settingsButton.setFont(new Font("Cooper Black", Font.BOLD, 32));
		settingsButton.addActionListener(e -> {
			new SettingsWindow(user, this, menu);
			repaint();
		});
		
		settingsContainer.add(settingsLabel);
		settingsContainer.add(settingsButton);
		
		this.add(learnContainer);
		this.add(settingsContainer);
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		//draw background
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//draw 504-Absolutely Essential Words
		g2d.setColor(new Color(10, 204, 178));
		g2d.setFont(new Font("Cooper Black", Font.BOLD, 100));
		g2d.drawString("504-Absolutely", 200, 180);
		g2d.drawString("Essential Words", 165, 300);
		
		//draw 504 words
		g2d.setColor(new Color(108, 184, 17));
		g2d.setFont(new Font("Arial", Font.ITALIC, 70));
		g2d.drawString("504 words", 440, 400);
	}
}