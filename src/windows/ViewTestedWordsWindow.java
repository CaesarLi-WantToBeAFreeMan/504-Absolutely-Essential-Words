package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import data.User;
import data.DatabaseConnector;

public class ViewTestedWordsWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private User user = new User();
	private DatabaseConnector dbc = new DatabaseConnector();
	
	ViewTestedWordsWindow(){
		this.setSize(500, 500);
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("View Tested Words Status");
		ImageIcon learnIcon = new ImageIcon("icons/logo.png");
		this.setIconImage(learnIcon.getImage());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		user.load();
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(Color.BLACK);
		for(int i = 0; i < 42; i++) {
			//title
			JPanel TitleContainer = new JPanel();
			TitleContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
			TitleContainer.setBackground(Color.BLACK);
			JLabel title = new JLabel("lesson #" + (i + 1));
			title.setForeground(Color.WHITE);
			title.setFont(new Font("Cooper BLACK", Font.BOLD, 32));
			TitleContainer.add(title);
			container.add(TitleContainer);
			
			//test status container
			JPanel testStatusContainer = new JPanel();
			testStatusContainer.setBackground(Color.BLACK);
			testStatusContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
			testStatusContainer.setLayout(new GridLayout(3, 4, 10, 10));
			container.add(testStatusContainer);
			
			//red representatives haven't learned yet, and green representatives learned
			for(int j = 0; j < 12; j++) {
				JLabel word = new JLabel(dbc.getWord(i, j));
				word.setFont(new Font("Arial", Font.PLAIN, 15));
				word.setForeground(user.getLessonWordTestStatus(i, j) ? Color.green : Color.RED);
				testStatusContainer.add(word);
			}
		}
		
		JScrollPane showArea = new JScrollPane(container);
		this.add(showArea);
		
		this.setVisible(true);
	}
}