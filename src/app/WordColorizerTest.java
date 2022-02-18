package app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WordColorizerTest {

	@Test
	void test_whenWordsHaveDifferentSize_thenReturnEmptyString() {
		assertTrue("".equals(WordColorizer.colorizeGuess("secret","blah")));
	}

	@Test
	void test_whenWordsHaveTheSameSize_thenReturnAtLeastAWordOfTheSameSize() {
		
		String guess = "SeCrET";
		String result = WordColorizer.colorizeGuess(guess, guess);
		
		assertTrue(result.length() >= guess.length());
	
	}
	
	@Test
	void test_wordsAndColors() {
		System.out.println("ZAPATILLA\n" + WordColorizer.colorizeGuess("ZAPATILLA","ZAPATILLO"));
	}
}
