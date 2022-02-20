package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wordle {

	public static final int MAX_ATTEMPTS = 5;
	public static final int WORLD_LENGTH = 5;
	
	String secret = null;
	boolean playerWins = false;
	private List<String> attempts = new ArrayList<String>();
	
	private WordsProvider provider = null;
	
	public Wordle(WordsProvider provider) {
		
		this.provider = provider; 
		this.secret = provider.getRandomWord();
	}

	public String getSecret() {
		
		return secret;
	}

	public int remainingAttempts() {
		
		return MAX_ATTEMPTS - attempts.size();
	}
	
	public boolean isGameOver() {
		
		return playerWins || remainingAttempts() == 0;
	}
	
	public void makeAttempt(String guess) {
		
		guess = guess.toUpperCase();
		
		if (isValidAttempt(guess) && remainingAttempts() > 0) { 		
			playerWins = secret.equals(guess);
			attempts.add(guess);
		}
	}	
	
	public List<String> attempts () {
		
		return Collections.unmodifiableList(attempts);
	}

	public boolean isValidAttempt(String guess) {
		return WORLD_LENGTH == guess.length() && provider.isValidWorld(guess) && !attempts.contains(guess);
	}
	
	public boolean playerWins() {
		return playerWins;
	}
	
}
