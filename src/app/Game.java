package app;

import java.util.Scanner;

import model.Wordle;
import model.SpanishWordsProvider;
import ui.TextPrinter;

public class Game {

	public static void main(String[] args) throws Exception {
				
		TextPrinter.printLogo();
		Wordle wordle = new Wordle(new SpanishWordsProvider("words-es.txt"));
		//System.out.println(wordle.getSecret());
		TextPrinter.printTable(wordle);
		Scanner in = new Scanner(System.in);
		
		while (!wordle.isGameOver()) {
			
			TextPrinter.printRemainingAttempts(wordle.remainingAttempts(), Wordle.MAX_ATTEMPTS);
			TextPrinter.printAskForUserInput();
			String guess = in.nextLine();
			
			if (!wordle.isValidAttempt(guess)) {
				
				TextPrinter.printInvalidWord(guess);			
				continue;
			}
			
			wordle.makeAttempt(guess);
			TextPrinter.printTable(wordle);
			
		}
		
		in.close();
		
		if (wordle.playerWins()) {
			
			TextPrinter.printCongratulationsMessage();
		}
		else {
			
			TextPrinter.printGameOverMessage();
		}
	}	
}
