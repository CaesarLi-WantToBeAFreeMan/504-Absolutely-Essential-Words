package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import data.User;
import data.WordInfo;

public class LearnWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private WordInfo info = new WordInfo();
	private JPanel showArea = new JPanel();
	private JButton basicButton = new JButton("basic"),
					meaningButton = new JButton("meaning"),
					exampleSentenceButton = new JButton("example sentences"),
					wordFamilyButton = new JButton("word family"),
					understandButton = new JButton("understand");
	private int lesson = 0, position = 0, learned = 0;
	private boolean learnStatus [] = new boolean [12];
	private User user = new User();
	
	LearnWindow(int lesson){
		//initialize data
		this.lesson = lesson;
		user.load();
		learnStatus = user.getLessonWordLearnStatus(lesson);
		for(boolean b : learnStatus)
			if(b)
				learned++;
		
		this.setSize(1000, 700);
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Learn Lesson #" + Integer.toString(lesson + 1));
		ImageIcon learnIcon = new ImageIcon("icons/logo.png");
		this.setIconImage(learnIcon.getImage());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		new Menus(this);
		
		//title container
		JPanel titleContainer = new JPanel();
		titleContainer.setOpaque(false);
		titleContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
		this.add(titleContainer, BorderLayout.NORTH);
		
		//title icon
		JLabel titleIcon = new JLabel(Integer.toString(lesson + 1));
		titleIcon.setForeground(new Color(0, 255, 255));
		titleIcon.setFont(new Font("Algerian", Font.BOLD, 70));
		titleContainer.add(titleIcon);
		
		//title text
		JLabel titleText = new JLabel("Learn lesson #" + Integer.toString(lesson + 1) + " Vocabulary");
		titleText.setForeground(new Color(0, 255, 255));
		titleText.setFont(new Font("Arial", Font.BOLD, 50));
		titleContainer.add(titleText);
		
		//progress bar
		JProgressBar progressBar = new JProgressBar(0, 12);
		progressBar.setBackground(new Color(32, 178, 170));
		progressBar.setForeground(new Color(255, 99, 71));
		progressBar.setFont(new Font("Algerian", Font.BOLD, 15));
		progressBar.setStringPainted(true);
		progressBar.setBorderPainted(true);
		progressBar.setValue(learned);
		this.add(progressBar, BorderLayout.SOUTH);
		
		//previous button
		JButton previousButton = new JButton("prev");
		previousButton.setBackground(new Color(250, 128, 114));
		previousButton.setForeground(new Color(255, 165, 0));
		previousButton.setFont(new Font("Algerian", Font.BOLD, 27));
		previousButton.setFocusable(false);
		previousButton.setBorderPainted(false);
		this.add(previousButton, BorderLayout.WEST);
		previousButton.addActionListener(e -> {
			position = position > 0 ? position - 1 : 11;
			basicButton.setBackground(new Color(237, 208, 40));
			meaningButton.setBackground(new Color(165, 42, 42));
			exampleSentenceButton.setBackground(user.getShowExampleSentence() ? new Color(165, 42, 42) : Color.GRAY);
			wordFamilyButton.setBackground(new Color(165, 42, 42));
			displayBasic(showArea);
			understandButton.setEnabled(learnStatus [position] ? false : true);
		});
		
		//next button
		JButton nextButton = new JButton("next");
		nextButton.setBackground(new Color(250, 128, 114));
		nextButton.setForeground(new Color(255, 165, 0));
		nextButton.setFont(new Font("Algerian", Font.BOLD, 27));
		nextButton.setFocusable(false);
		nextButton.setBorderPainted(false);
		this.add(nextButton, BorderLayout.EAST);
		nextButton.addActionListener(e -> {
			position = position < 11 ? position + 1 : 0;
			basicButton.setBackground(new Color(237, 208, 40));
			meaningButton.setBackground(new Color(165, 42, 42));
			exampleSentenceButton.setBackground(user.getShowExampleSentence() ? new Color(165, 42, 42) : Color.GRAY);
			wordFamilyButton.setBackground(new Color(165, 42, 42));
			displayBasic(showArea);
			understandButton.setEnabled(learnStatus [position] ? false : true);
		});
		
		//flashcard container
		JPanel flashcard = new JPanel();
		flashcard.setBackground(new Color(210, 180, 140));
		flashcard.setLayout(new BorderLayout(0, 5));
		this.add(flashcard, BorderLayout.CENTER);
		
		//buttons container
		JPanel buttonContainer = new JPanel();
		buttonContainer.setOpaque(false);
		buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		flashcard.add(buttonContainer, BorderLayout.NORTH);
		
		//basic button
		basicButton.setBackground(new Color(237, 208, 40));
		basicButton.setForeground(Color.BLACK);
		basicButton.setFont(new Font("Arial", Font.PLAIN, 21));
		basicButton.setFocusable(false);
		buttonContainer.add(basicButton);
		basicButton.addActionListener(e -> {
			basicButton.setBackground(new Color(237, 208, 40));
			meaningButton.setBackground(new Color(165, 42, 42));
			exampleSentenceButton.setBackground(user.getShowExampleSentence() ? new Color(165, 42, 42) : Color.GRAY);
			wordFamilyButton.setBackground(new Color(165, 42, 42));
			displayBasic(showArea);
		});
		
		//meaning button
		meaningButton.setBackground(new Color(165, 42, 42));
		meaningButton.setForeground(Color.BLACK);
		meaningButton.setFont(new Font("Arial", Font.PLAIN, 21));
		meaningButton.setFocusable(false);
		buttonContainer.add(meaningButton);
		meaningButton.addActionListener(e -> {
			basicButton.setBackground(new Color(165, 42, 42));
			meaningButton.setBackground(new Color(237, 208, 40));
			exampleSentenceButton.setBackground(user.getShowExampleSentence() ? new Color(165, 42, 42) : Color.GRAY);
			wordFamilyButton.setBackground(new Color(165, 42, 42));
			displayMeanings(showArea);
		});
		
		//example sentence button
		exampleSentenceButton.setBackground(user.getShowExampleSentence() ? new Color(165, 42, 42) : Color.GRAY);
		exampleSentenceButton.setForeground(Color.BLACK);
		exampleSentenceButton.setFont(new Font("Arial", Font.PLAIN, 21));
		exampleSentenceButton.setFocusable(false);
		exampleSentenceButton.setEnabled(user.getShowExampleSentence() ? true : false);
		buttonContainer.add(exampleSentenceButton);
		exampleSentenceButton.addActionListener(e -> {
			if(user.getShowExampleSentence()) {
				basicButton.setBackground(new Color(165, 42, 42));
				meaningButton.setBackground(new Color(165, 42, 42));
				exampleSentenceButton.setBackground(new Color(237, 208, 40));
				wordFamilyButton.setBackground(new Color(165, 42, 42));
				displaySentences(showArea);
			}
		});
		
		//word family button
		wordFamilyButton.setBackground(new Color(165, 42, 42));
		wordFamilyButton.setForeground(Color.BLACK);
		wordFamilyButton.setFont(new Font("Arial", Font.PLAIN, 21));
		wordFamilyButton.setFocusable(false);
		buttonContainer.add(wordFamilyButton);
		wordFamilyButton.addActionListener(e -> {
			basicButton.setBackground(new Color(165, 42, 42));
			meaningButton.setBackground(new Color(165, 42, 42));
			exampleSentenceButton.setBackground(user.getShowExampleSentence() ? new Color(165, 42, 42) : Color.GRAY);
			wordFamilyButton.setBackground(new Color(237, 208, 40));
			displayFamily(showArea);
		});
		
		//understand button
		understandButton.setBackground(new Color(0, 100, 0));
		understandButton.setForeground(Color.BLACK);
		understandButton.setFont(new Font("Arial", Font.PLAIN, 21));
		understandButton.setFocusable(false);
		understandButton.setEnabled(user.getLessonWordLearnStatus(lesson, position) ? false : true);
		understandButton.addActionListener(e -> {
			learnStatus [position] = true;
			understandButton.setEnabled(false);
			progressBar.setValue(++learned);
		});
		flashcard.add(understandButton, BorderLayout.SOUTH);
		
		//show area
		showArea.setOpaque(false);
		flashcard.add(showArea, BorderLayout.CENTER);
		
		//by default, display basic information
		displayBasic(showArea);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				user.load();
				user.setLessonWordLearnStatus(lesson, learnStatus);
				user.update();
			};
		});
		
		this.setVisible(true);
	}
	
	private void displayBasic(JPanel showArea) {
		//remove all components of showArea
		showArea.removeAll();
		showArea.revalidate();
		
		//basic container
		JPanel basicContainer = new JPanel();
		basicContainer.setOpaque(false);
		basicContainer.setLayout(new BorderLayout(0, 10));
		showArea.add(basicContainer);
		
		//word label
		JLabel wordLabel = new JLabel(info.getWord(lesson, position));
		wordLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 90));
		wordLabel.setHorizontalAlignment(JLabel.CENTER);
		basicContainer.add(wordLabel, BorderLayout.NORTH);
		
		//phonetic symbol label
		JLabel phoneticSymbolLabel = new JLabel(info.getPhoneticSymbol(lesson, position));
		phoneticSymbolLabel.setFont(new Font("Arial", Font.BOLD, 80));
		phoneticSymbolLabel.setHorizontalAlignment(JLabel.CENTER);
		basicContainer.add(phoneticSymbolLabel, BorderLayout.SOUTH);
		
		//repaint showArea
		showArea.repaint();
	}
	
	private void displayMeanings(JPanel showArea) {
		//remove all components of showArea
		showArea.removeAll();
		showArea.revalidate();
		
		//meaning container
		JPanel meaningContainer = new JPanel();
		meaningContainer.setOpaque(false);
		meaningContainer.setLayout(new BorderLayout(0, 10));
		showArea.add(meaningContainer);
		
		//english meaning text area
		JTextArea englishMeaningArea = new JTextArea(5, 20);
		englishMeaningArea.setText(info.getEnglishMeaning(lesson, position));
		englishMeaningArea.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 39));
		englishMeaningArea.setFocusable(false);
		englishMeaningArea.setOpaque(false);
		englishMeaningArea.setEditable(false);
	    englishMeaningArea.setLineWrap(true);
	    englishMeaningArea.setWrapStyleWord(true);
		meaningContainer.add(englishMeaningArea, BorderLayout.NORTH);
		
		//chinese meaning label
		if(user.getShowChineseMeaning()) {
			JLabel chineseMeaningLabel = new JLabel(info.getChineseMeaning(lesson, position));
			chineseMeaningLabel.setFont(new Font("DFKai-SB", Font.BOLD, 39));
			meaningContainer.add(chineseMeaningLabel, BorderLayout.SOUTH);
		}
		
		//repaint showArea
		showArea.repaint();
	}
	
	private void displaySentences(JPanel showArea) {
		//remove all components of showArea
		showArea.removeAll();
		showArea.revalidate();
		
		//example sentence container
		JPanel exampleSentenceContainer = new JPanel();
		exampleSentenceContainer.setOpaque(false);
		exampleSentenceContainer.setLayout(new GridLayout(3, 1));
		showArea.add(exampleSentenceContainer);
		
		//example sentence text area
		JTextArea exampleSentenceArea [] = new JTextArea [3];
		for(int i = 0; i < 3; i++) {
			exampleSentenceArea [i] = new JTextArea(3, 30); 
			exampleSentenceArea [i].setText(info.getExampleSentence(lesson, position, i));
			exampleSentenceArea [i].setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
			exampleSentenceArea [i].setFocusable(false);
			exampleSentenceArea [i].setOpaque(false);
			exampleSentenceArea [i].setEditable(false);
			exampleSentenceArea [i].setLineWrap(true);
			exampleSentenceArea [i].setWrapStyleWord(true);
			exampleSentenceContainer.add(exampleSentenceArea [i]);
		}
		
		//repaint showArea
		showArea.repaint();
	}
	
	private void displayFamily(JPanel showArea) {
		//remove all components of showArea
		showArea.removeAll();
		
		//word family container
		JPanel wordFamilyContainer = new JPanel();
		wordFamilyContainer.setOpaque(false);
		wordFamilyContainer.setLayout(new GridLayout(4, 1, 0, 0));
		showArea.add(wordFamilyContainer);
		
		//add noun, verb, adjective and adverb forms content
		for(int i = 0; i < 4; i++)
			fillWordForms(wordFamilyContainer, i, lesson);
		
		//repaint showArea
		showArea.revalidate();
		showArea.repaint();
	}
	
	
	private void fillWordForms(JPanel container, int type, int lesson) {
		//form container
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		formContainer.setOpaque(false);
		container.add(formContainer);
		
		//form icon
		JLabel formIcon = new JLabel(type == 0 ? "n.      " : type == 1 ? "v.      " : type == 2 ? "a.      " : "ad.    ");
		formIcon.setForeground(new Color(160, 32, 240));
		formIcon.setFont(new Font("Cooper Black", Font.ITALIC, 50));
		formIcon.setVerticalAlignment(JLabel.CENTER);
		formIcon.setHorizontalAlignment(JLabel.LEFT);
		formContainer.add(formIcon);
		
		//form text
		JLabel formText = new JLabel();
		formText.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 32));
		String originalString = info.getWordFamily(lesson, position, type);
		if(originalString.contains(".")) {
			int startPosition = originalString.indexOf(".");
			String text = 	"<html><span style = 'color:black;'>" + originalString.substring(0, startPosition) + "</span><span style = 'color:gray;'>"
							+ originalString.substring(startPosition + 1) + "</span></html>";
			formText.setText(text);
		}else {
			formText.setText(originalString);
			formText.setForeground(originalString.contains("/") ? Color.GRAY : Color.BLACK);
		}
		formText.setVerticalAlignment(JLabel.CENTER);
		formText.setHorizontalAlignment(JLabel.LEFT);
		formContainer.add(formText);
	}
}