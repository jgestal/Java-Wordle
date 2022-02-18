package app;

public class WordColorizer {

	public static final String colorizeGuess(String secret, String guess) {

		String result = "";
		
		if (secret.length() != guess.length()) {
			return result;
		}
		
		for (int i = 0; i < guess.length(); i++) {
			
			char character = guess.charAt(i);
			
			if (isCharPresentInSecretAtTheRightPosition(character, secret, i)) {
				result += ConsoleColors.greenString(String.valueOf(character));
			}
			else if (isCharPresentInSecret(character, secret)) {
				result += ConsoleColors.yellowString(String.valueOf(character));
			}
			else {
				result += String.valueOf(character);
			}
		}
	
		return result;
	}

	
	private static final boolean isCharPresentInSecret(char character, String secret) {
		return secret.indexOf(character) != -1;
	}
	
	private static final boolean isCharPresentInSecretAtTheRightPosition(char character, String secret, int position) {
		return secret.charAt(position) == character;
	}
	
}
