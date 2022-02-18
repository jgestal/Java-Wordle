package app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordleTest {
	
	Wordle sut; 
	String secret = "SeCRET";
	String secretLowerUpper = "SeCrEt";
	String badGuess = "TERCES";
	String badGuessLowerUppercase = "TeRCeS";
	String invalidGuess = "INVALID_ATTEMPT"; 
	
	@BeforeEach 
	void init() {
	      sut = new Wordle(secret);
	}

	@Test
	void test_whenInitialized_thenIsNotGameOver() {
		assertFalse(sut.isGameOver());
	}

	@Test
	void test_whenUserDiscoversSecretWorld_thenIsGameOver() {
		sut.makeAttempt(secret);
		assertTrue(sut.isGameOver());
	}
	
	@Test
	void test_whenUserTypesSecretInLowerCaseOrUpperCase_thenItDoesntReallyMatter() {
		sut.makeAttempt(secretLowerUpper);
		assertTrue(sut.isGameOver());
	}
	
	@Test
	void test_whenUserMakesAWrongAttempt_thenIsNotGameOver() {
		sut.makeAttempt(badGuess);
		assertFalse(sut.isGameOver());
	}
	
	@Test
	void test_whenUserMakesManyAttempts_thenIsGameOver() {
		
		for (int i=0; i <= Wordle.MAX_ATTEMPTS; i++) {
			sut.makeAttempt(badGuess);
		}
		assertTrue(sut.isGameOver());
	}
	
	@Test
	void test_whenGameBegins_AttemptsListIsEmpty() {
		assertEquals(sut.attempts().size(),0);
	}
	
	@Test
	void test_whenUserMakesOneAttempt_thenAttemptsListSizeIsOne() {
		sut.makeAttempt(badGuess);
		assertEquals(sut.attempts().size(),1);
	}
	
	@Test
	void test_whenUserMakesOneAttempt_thenTheLastAttemptsListElementIsTheUserGuess() {
		sut.makeAttempt(badGuess);
		assertTrue(sut.attempts().get(0).equals(badGuess));
	}
	
	@Test
	void test_whenUserMakesOneAttemptInLowerOrUppercase_thenTheLastAttemptsListElementIsTheUserGuessInUppercase() {
		sut.makeAttempt(badGuessLowerUppercase);
		assertTrue(sut.attempts().get(0).equals(badGuessLowerUppercase.toUpperCase()));
	}
	
	@Test
	void test_whenUserMakesAValidAttempt_theAttemptIsValid() {
		assertTrue(sut.isValidAttempt(secret));
	}
	
	@Test
	void test_whenUserMakesAnInvalidAttempt_theAttemptIsNotValid() {
		assertFalse(sut.isValidAttempt(invalidGuess));
	}
	
	@Test
	void test_whenUserMakesAnInvalidAttempt_theGuessIsNotAddedToAttemptsList() {
		sut.makeAttempt(invalidGuess);
		assertEquals(sut.attempts().size(),0);
	}
	
	
}
