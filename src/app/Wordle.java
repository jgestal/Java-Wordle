package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wordle {

	public static final int MAX_ATTEMPTS = 5;
	
	String secret = null;
	boolean wasSecretGuessed = false;
	private List<String> attempts = new ArrayList<String>();
	
	public Wordle(String secret) {
		this.secret = secret;
	}

	public boolean isGameOver() {
		return wasSecretGuessed || attempts.size() >= MAX_ATTEMPTS;
	}

	public void makeAttempt(String guess) {
		if (!isValidAttempt(guess)) { return; }
		wasSecretGuessed = secret.equalsIgnoreCase(guess);
		attempts.add(guess.toUpperCase());
	}	
	
	public List<String> attempts () {
		return Collections.unmodifiableList(attempts);
	}

	public boolean isValidAttempt(String guess) {
		return secret.length() == guess.length();
	}
}
