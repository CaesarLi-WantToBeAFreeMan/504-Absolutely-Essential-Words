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

import data.User;

public class ViewOutlineWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private User user = new User();
	ViewOutlineWindow(){
		this.setSize(500, 500);
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("View Lessons Outline");
		ImageIcon learnIcon = new ImageIcon("icons/logo.png");
		this.setIconImage(learnIcon.getImage());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		user.load();
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setBackground(Color.BLACK);
		for(int i = 0, learnedNumber, notLearnedNumber, testedNumber, notTestedNumber; i < 42; i++) {
			//title
			JPanel TitleContainer = new JPanel();
			TitleContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
			TitleContainer.setBackground(Color.BLACK);
			JLabel title = new JLabel("lesson #" + (i + 1));
			title.setForeground(Color.WHITE);
			title.setFont(new Font("Cooper BLACK", Font.BOLD, 32));
			TitleContainer.add(title);
			container.add(TitleContainer);
			
			//learn status container
			JPanel learnStatusContainer = new JPanel();
			learnStatusContainer.setBackground(Color.BLACK);
			learnStatusContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
			container.add(learnStatusContainer);
			
			//view learned
			learnedNumber = countLearned(i);
			JLabel learned = new JLabel("learned: " + learnedNumber);
			learned.setForeground(Color.GREEN);
			learned.setFont(new Font("Arial", Font.PLAIN, 18));
			learnStatusContainer.add(learned);
			
			//view not learned
			notLearnedNumber = 12 - learnedNumber;
			JLabel notLearned = new JLabel("haven't learned yet: " + notLearnedNumber);
			notLearned.setForeground(Color.RED);
			notLearned.setFont(new Font("Arial", Font.PLAIN, 18));
			learnStatusContainer.add(notLearned);
			
			//test status container
			JPanel testStatusContainer = new JPanel();
			testStatusContainer.setBackground(Color.BLACK);
			testStatusContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
			container.add(testStatusContainer);
			
			//view tested
			testedNumber = countTested(i);
			JLabel tested = new JLabel("tested: " + testedNumber);
			tested.setForeground(Color.GREEN);
			tested.setFont(new Font("Arial", Font.PLAIN, 18));
			testStatusContainer.add(tested);
			
			//view not tested
			notTestedNumber = 12 - testedNumber;
			JLabel notTested = new JLabel("haven't tested yet: " + notTestedNumber);
			notTested.setForeground(Color.RED);
			notTested.setFont(new Font("Arial", Font.PLAIN, 18));
			testStatusContainer.add(notTested);
		}
		
		JScrollPane showArea = new JScrollPane(container);
		this.add(showArea);
		
		this.setVisible(true);
	}
	
	//count number of learned words
	private int countLearned(int lesson) {
		int count = 0;
		for(int i = 0; i < 12; i++)
			if(user.getLessonWordLearnStatus(lesson, i))
				count++;
		return count;
	}
	
	//count number of tested words
	private int countTested(int lesson) {
		int count = 0;
		for(int i = 0; i < 12; i++)
			if(user.getLessonWordTestStatus(lesson, i))
				count++;
		return count;
	}
}