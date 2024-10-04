package data;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseConnector{
	private Connection con = null;
	private Statement stm = null;
	private ResultSet result = null;
	
	//create a connection
	public DatabaseConnector(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vocabulary", "Caesar", "Caesar@8964");
			stm = con.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	//disconnection
	public void close() {
		try {
			result.close();
			stm.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//get a specific word
	public String getWord(int lesson, int position) {
		String word = null;
		try {
			result = stm.executeQuery("SELECT word FROM vocabulary WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				word = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return word;
	}
	
	//get a specific phonetic symbol
	public String getPhoneticSymbol(int lesson, int position) {
		String phoneticSymbol = null;
		try {
			result = stm.executeQuery("SELECT phoneticSymbol FROM vocabulary WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				phoneticSymbol = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return phoneticSymbol;
	}
	
	//get a specific english meaning
	public String getEnglishMeaning(int lesson, int position) {
		String englishMeaning = null;
		try {
			result = stm.executeQuery("SELECT englishMeaning FROM vocabulary WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				englishMeaning = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return englishMeaning;
	}
	
	//get a specific chinese meaning
	public String getChineseMeaning(int lesson, int position) {
		String chineseMeaning = null;
		try {
			result = stm.executeQuery("SELECT chineseMeaning FROM vocabulary WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				chineseMeaning = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return chineseMeaning;
	}
	
	//get a specific sentenceA
	public String getSentenceA(int lesson, int position) {
		String sentenceA = null;
		try {
			result = stm.executeQuery("SELECT sentenceA FROM vocabulary WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				sentenceA = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return sentenceA;
	}
	
	//get a specific sentenceB
	public String getSentenceB(int lesson, int position) {
		String sentenceB = null;
		try {
			result = stm.executeQuery("SELECT sentenceB FROM vocabulary WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				sentenceB = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return sentenceB;
	}
	
	//get a specific sentenceC
	public String getSentenceC(int lesson, int position) {
		String sentenceC = null;
		try {
			result = stm.executeQuery("SELECT sentenceC FROM vocabulary WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				sentenceC = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return sentenceC;
	}
	
	//get a specific noun
	public String getNoun(int lesson, int position) {
		String noun = null;
		try {
			result = stm.executeQuery("SELECT noun FROM vocabulary WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				noun = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return noun;
	}
	
	//get a specific verb
	public String getVerb(int lesson, int position) {
		String verb = null;
		try {
			result = stm.executeQuery("SELECT verb FROM vocabulary WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				verb = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return verb;
	}
	
	//get a specific adjective
	public String getAdjective(int lesson, int position) {
		String adjective = null;
		try {
			result = stm.executeQuery("SELECT adjective FROM vocabulary WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				adjective = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return adjective;
	}
	
	//get a specific adverb
	public String getAdverb(int lesson, int position) {
		String adverb = null;
		try {
			result = stm.executeQuery("SELECT adverb FROM vocabulary WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				adverb = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return adverb;
	}
	
	//get a specific question
	public String getQuestion(int lesson, int position) {
		String question = null;
		try {
			result = stm.executeQuery("SELECT question FROM qna WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				question = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return question;
	}
	
	//get a specific answer
	public String getAnswer(int lesson, int position) {
		String answer = null;
		try {
			result = stm.executeQuery("SELECT answer FROM qna WHERE id = " + Integer.toString((lesson + 1) * 100 + (position + 1)));
			if(result.next())
				answer = result.getString(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return answer;
	}
}