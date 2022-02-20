package ui;

import model.Wordle;

public class TextPrinter {
	
	public static void printLogo() {
		
		System.out.println();
		System.out.println(ConsoleColors.BLACK_BOLD 	+ "---------------------------------------------");		                
		System.out.println(ConsoleColors.RED_BOLD 		+ " __    __   ___   ____   ___    _        ___  ");
		System.out.println(ConsoleColors.RED_BOLD 		+ "|  |__|  | /   \\ |    \\ |   \\  | |      /  _] ");
		System.out.println(ConsoleColors.GREEN_BOLD 	+ "|  |  |  ||     ||  D  )|    \\ | |     /  [_  ");
		System.out.println(ConsoleColors.YELLOW_BOLD 	+ "|  |  |  ||  O  ||    / |  D  || |___ |    _] ");
		System.out.println(ConsoleColors.BLUE_BOLD 		+ "|  `  '  ||     ||    \\ |     ||     ||   [_  ");
		System.out.println(ConsoleColors.PURPLE_BOLD 	+ " \\      / |     ||  .  \\|     ||     ||     | ");
		System.out.println(ConsoleColors.CYAN_BOLD 		+ "  \\_/\\_/   \\___/ |__|\\_||_____||_____||_____| ");
		System.out.println(ConsoleColors.BLACK_BOLD		+ "---------------------------------------------");		                
		System.out.println(ConsoleColors.RESET);

	}
	
	public static void printAskForUserInput() {
		System.out.println("\nIntroduce tu palabra: ");
	}
	
	public static void printInvalidWord(String word) {
		System.out.println("\nLa palabra introducida: " + ConsoleColors.RED_BOLD + word + ConsoleColors.RESET + " no es válida.");
	}
	
	public static void printRemainingAttempts(int remainingAttempt, int remainingAttempts) {
		System.out.println("\nTe quedan " + ConsoleColors.BLUE_BOLD + remainingAttempt + ConsoleColors.RESET + " de " + ConsoleColors.BLUE_BOLD + remainingAttempts + ConsoleColors.RESET + " intentos.");
	}
	
	public static void printCongratulationsMessage() {
		System.out.println( ConsoleColors.GREEN_BOLD + "¡Enhorabuena! Has ganado." + ConsoleColors.RESET);
	}
	
	public static void printGameOverMessage() {
		System.out.println(ConsoleColors.RED_BOLD + "GAME OVER. Has perdido");
	}
	
	public static void printTable(Wordle wordle) {
			
		for (int i = 0; i < Wordle.MAX_ATTEMPTS; i++) {
			
			if (i >= wordle.attempts().size()) {
				printRow(null, wordle.getSecret());
			} else {
				String attempt = wordle.attempts().get(i);
				printRow(attempt, wordle.getSecret());
			}
		}	
	}
	
	private static void printRow(String attempt, String secret) {

		String letters = " | #0 | #1 | #2 | #3 | #4 |";
		
		if (attempt == null) {
			letters = " |   |   |   |   |   |";
		}
		else {
			
			for (int i = 0; i < Wordle.WORLD_LENGTH; i++) {
				
				char character = attempt.charAt(i);
				
				if (secretContainsCharAtSamePosition(character, secret, i)) {					
					letters = letters.replace("#" + i, ConsoleColors.GREEN_BOLD + character + ConsoleColors.RESET);
				}
				else if (secretContainsChar(character, secret)) {
					letters = letters.replace("#" + i, ConsoleColors.YELLOW_BOLD + character + ConsoleColors.RESET);
				}
				else {
					letters = letters.replace("#" + i, ConsoleColors.BLACK_BOLD + character + ConsoleColors.RESET);
				}
			}	
		}
		
		printLetters(letters);
	}
	
	private static void printLetters(String letters) {
		System.out.println(" +---+---+---+---+---+");		
		System.out.println(letters);
		System.out.println(" +---+---+---+---+---+");		
	}
	
	private static final boolean secretContainsChar(char character, String secret) {
		return secret.indexOf(character) != -1;
	}
	
	private static final boolean secretContainsCharAtSamePosition(char character, String secret, int position) {
		return secret.charAt(position) == character;
	}	
}
