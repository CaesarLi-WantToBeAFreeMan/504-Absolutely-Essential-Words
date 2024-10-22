package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import data.User;
import data.DatabaseConnector;

public class LessonsWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton [] pages = new JButton [7];
	private JButton prevButton = new JButton("<");
	private JButton nextButton = new JButton(">");
	private JPanel [] lessons = new JPanel [42];
	private JPanel lessonContainer = new JPanel();
	private int page = 0;
	private User user = new User();
	private DatabaseConnector dbc = new DatabaseConnector();
	
	LessonsWindow(){
		this.setSize(1250, 750);
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Lessons");
		ImageIcon learnIcon = new ImageIcon("icons/logo.png");
		this.setIconImage(learnIcon.getImage());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		new Menus(this);
		user.load();
		
		//title label
		JLabel titleLabel = new JLabel("Lessons");
		Image learnImage = learnIcon.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		learnIcon = new ImageIcon(learnImage);
		titleLabel.setIcon(learnIcon);
		titleLabel.setHorizontalTextPosition(JLabel.RIGHT);
		titleLabel.setVerticalTextPosition(JLabel.CENTER);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setVerticalAlignment(JLabel.CENTER);
		titleLabel.setIconTextGap(20);
		titleLabel.setFont(new Font("Cooper Black", Font.BOLD, 50));
		titleLabel.setForeground(Color.CYAN);
		
		//tip container
		JPanel tipContainer = new JPanel();
		tipContainer.setLayout(new GridLayout(3, 1, 5, 5));
		tipContainer.setOpaque(false);
		
		//navigation bar
		JPanel navigationBar = new JPanel();
		navigationBar.setOpaque(false);
		navigationBar.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		tipContainer.add(navigationBar);
		
		//previous button
		prevButton.setBackground(Color.BLACK);
		prevButton.setForeground(new Color(135, 206, 235));
		prevButton.setFont(new Font("Cooper Black", Font.BOLD, 39));
		prevButton.setFocusable(false);
		prevButton.setBorder(null);
		prevButton.setEnabled(false);
		prevButton.addActionListener(e -> {
			if(page > 0) {
				prevButton.setEnabled(true);
				pages [page--].setForeground(new Color(135, 206, 235));
				
				//remove all components of lessonContainer
				lessonContainer.removeAll();
				lessonContainer.revalidate();
				
				pages [page].setForeground(new Color(88, 57, 39));
				
				//show components of lessonContainer in current page
				for(int i = page * 6; i < (page + 1) * 6; i++)
					fullLesson(lessonContainer, i);
				lessonContainer.repaint();
			}
			
			//settings for prevButton and nextButton
			prevButton.setEnabled(page > 0 ? true : false);
			nextButton.setEnabled(page < 6 ? true : false);
		});
		navigationBar.add(prevButton);
		
		for(int i = 0; i < 7; i++)
			fullPages(i + 1, navigationBar);
		
		//next button
		nextButton.setBackground(Color.BLACK);
		nextButton.setForeground(new Color(135, 206, 235));
		nextButton.setFont(new Font("Cooper Black", Font.BOLD, 39));
		nextButton.setFocusable(false);
		nextButton.setBorder(null);
		nextButton.addActionListener(e -> {
			if(page < 6) {
				nextButton.setEnabled(true);
				pages [page++].setForeground(new Color(135, 206, 235));
				
				//remove all components of lessonContainer
				lessonContainer.removeAll();
				lessonContainer.revalidate();
				
				pages [page].setForeground(new Color(88, 57, 39));
				
				//show components of lessonContainer in current page
				for(int i = page * 6; i < (page + 1) * 6; i++)
					fullLesson(lessonContainer, i);
				lessonContainer.repaint();
			}
			
			//settings for prevButton and nextButton
			prevButton.setEnabled(page > 0 ? true : false);
			nextButton.setEnabled(page < 6 ? true : false);
		});
		navigationBar.add(nextButton);
		
		//group rule tip
		JLabel groupRuleLabel = new JLabel("we grouped 12 words into a lesson, and we have 42 lessons totally");
		groupRuleLabel.setHorizontalAlignment(JLabel.CENTER);
		groupRuleLabel.setVerticalAlignment(JLabel.CENTER);
		groupRuleLabel.setFont(new Font("Cooper Black", Font.BOLD, 30));
		groupRuleLabel.setForeground(Color.GRAY);
		tipContainer.add(groupRuleLabel);
		
		//change color rule container
		JPanel changeColorRuleContainer = new JPanel();
		changeColorRuleContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
		changeColorRuleContainer.setOpaque(false);
		tipContainer.add(changeColorRuleContainer);
		
		//red text meaning tip
		JLabel redMeaningLabel = new JLabel("you have not learned yet");
		redMeaningLabel.setHorizontalAlignment(JLabel.CENTER);
		redMeaningLabel.setVerticalAlignment(JLabel.CENTER);
		redMeaningLabel.setFont(new Font("Cooper Black", Font.BOLD, 30));
		redMeaningLabel.setForeground(Color.RED);
		changeColorRuleContainer.add(redMeaningLabel);
		
		//blue text meaning tip
		JLabel blueMeaningLabel = new JLabel("you are learning");
		blueMeaningLabel.setHorizontalAlignment(JLabel.CENTER);
		blueMeaningLabel.setVerticalAlignment(JLabel.CENTER);
		blueMeaningLabel.setFont(new Font("Cooper Black", Font.BOLD, 30));
		blueMeaningLabel.setForeground(Color.BLUE);
		changeColorRuleContainer.add(blueMeaningLabel);
		
		//green text meaning tip
		JLabel greenMeaningLabel = new JLabel("you learned");
		greenMeaningLabel.setHorizontalAlignment(JLabel.CENTER);
		greenMeaningLabel.setVerticalAlignment(JLabel.CENTER);
		greenMeaningLabel.setFont(new Font("Cooper Black", Font.BOLD, 30));
		greenMeaningLabel.setForeground(Color.GREEN);
		changeColorRuleContainer.add(greenMeaningLabel);
		
		//lesson container
		lessonContainer.setLayout(new GridLayout(2, 3, 30, 20));
		lessonContainer.setOpaque(false);
		
		for(int i = page * 6; i < (page + 1) * 6; i++)
			fullLesson(lessonContainer, i);
		
		this.add(titleLabel, BorderLayout.NORTH);
		this.add(tipContainer, BorderLayout.SOUTH);
		this.add(lessonContainer, BorderLayout.CENTER);
		this.setVisible(true);
		
		//don't disconnect the connection
		//dbc.close();
	}
	
	private void fullLesson(JPanel container, int lesson) {
		//lesson component
		lessons [lesson] = new JPanel();
		lessons [lesson].setVisible(true);
		lessons [lesson].setBackground(isLearned(lesson) ? Color.GREEN : isLearning(lesson) ? Color.BLUE : Color.RED);
		lessons [lesson].setLayout(new GridBagLayout());
		container.add(lessons [lesson]);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(3, 1, 10, 10);
		
		//lesson icon
		JLabel lessonIcon = new JLabel(lesson < 9 ? "0" + Integer.toString(lesson + 1) : Integer.toString(lesson + 1));
		lessonIcon.setForeground(new Color(14, 184, 227));
		lessonIcon.setFont(new Font("Algerian", Font.BOLD, 80));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		lessons [lesson].add(lessonIcon, gbc);
		
		//lesson contents container
		JPanel lessonContentContainer = new JPanel();
		lessonContentContainer.setOpaque(false);
		lessonContentContainer.setLayout(new BoxLayout(lessonContentContainer, BoxLayout.Y_AXIS));
		
		//update GridBagConstraints
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		lessons [lesson].add(lessonContentContainer, gbc);
		
		//lesson words container
		JPanel lessonWordContainer = new JPanel(new GridLayout(6, 2, 10, 5));
		lessonWordContainer.setBackground(new Color(34, 139, 34));
		lessonContentContainer.add(lessonWordContainer);
		
		//full all words
		for(int i = 0; i < 12; i++) {
			JLabel word = new JLabel(dbc.getWord(lesson, i));
			word.setForeground(Color.WHITE);
			word.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
			word.setHorizontalAlignment(JLabel.CENTER);
			lessonWordContainer.add(word);
		}
		
		//lesson buttons container
		JPanel lessonButtonContainer = new JPanel();
		lessonButtonContainer.setOpaque(false);
		lessonButtonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 15));
		lessonContentContainer.add(lessonButtonContainer);
		
		//lesson learn button
		JButton lessonLearnButton = new JButton("LEARN");
		lessonLearnButton.setBackground(new Color(210, 180, 140));
		lessonLearnButton.setForeground(new Color(128, 0, 128));
		lessonLearnButton.setFont(new Font("Bradley Hand ITC", Font.ITALIC, 18));
		lessonLearnButton.setFocusable(false);
		lessonLearnButton.addActionListener(e -> {
			lessonLearnButton.setEnabled(false);
			LearnWindow learnWindow = new LearnWindow(lesson);
			learnWindow.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e1) {
					lessonLearnButton.setEnabled(true);
					user.load();
					
					//repaint lessonContainer
					lessonContainer.removeAll();
					for(int i = page * 6; i < (page + 1) * 6; i++)
						fullLesson(lessonContainer, i);
					lessonContainer.revalidate();
					lessonContainer.repaint();
				}
			});
		});
		lessonButtonContainer.add(lessonLearnButton);
		
		//lesson test button
		JButton lessonTestButton = new JButton("TEST");
		lessonTestButton.setBackground(new Color(210, 180, 140));
		lessonTestButton.setForeground(new Color(128, 0, 128));
		lessonTestButton.setFont(new Font("Bradley Hand ITC", Font.ITALIC, 18));
		lessonTestButton.setFocusable(false);
		lessonTestButton.addActionListener(e -> {
			lessonTestButton.setEnabled(false);
			TestWindow testWindow = new TestWindow(lesson);
			testWindow.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e1) {
					lessonTestButton.setEnabled(true);
					user.load();
					
					//repaint lessonContainer
					lessonContainer.removeAll();
					for(int i = page * 6; i < (page + 1) * 6; i++)
						fullLesson(lessonContainer, i);
					lessonContainer.revalidate();
					lessonContainer.repaint();
				}
			});
		});
		lessonButtonContainer.add(lessonTestButton);
	}
	
	private void fullPages(int number, JPanel container) {
		pages [number - 1] = new JButton();
		pages [number - 1].setText(Integer.toString(number));
		pages [number - 1].setBackground(Color.BLACK);
		pages [number - 1].setForeground(page == number - 1 ? new Color(88, 57, 39) : new Color(135, 206, 235));
		pages [number - 1].setFont(new Font("Cooper Black", Font.BOLD, 39));
		pages [number - 1].setFocusable(false);
		pages [number - 1].setBorder(null);
		pages [number - 1].addActionListener(e -> {
			pages [page].setForeground(new Color(135, 206, 235));
			
			//remove all components of lessonContainer
			lessonContainer.removeAll();
			lessonContainer.revalidate();
			
			page = number - 1;
			pages [page].setForeground(new Color(88, 57, 39));
			
			//show components of lessonContainer in current page
			for(int i = page * 6; i < (page + 1) * 6; i++)
				fullLesson(lessonContainer, i);
			lessonContainer.repaint();
			
			//settings for prevButton and nextButton
			prevButton.setEnabled(page > 0 ? true : false);
			nextButton.setEnabled(page < 6 ? true : false);
			
		});
		container.add(pages [number - 1]);
	}
	
	private boolean isLearned(int lesson) {
		boolean [] 	learnArray = user.getLessonWordLearnStatus(lesson),
					testArray = user.getLessonWordTestStatus(lesson);
		
		//if find one word you haven't learned yet, return false immediately
		for(boolean i : learnArray)
			if(!i)
				return false;
		for(boolean i : testArray)
			if(!i)
				return false;
		
		return true;
	}
	
	private boolean isLearning(int lesson) {
		boolean [] 	learnArray = user.getLessonWordLearnStatus(lesson),
					testArray = user.getLessonWordTestStatus(lesson);
		
		//if find one word you learned, return true immediately
		for(boolean i : learnArray)
			if(i)
				return true;
		for(boolean i : testArray)
			if(i)
				return true;
		
		return false;
	}
}