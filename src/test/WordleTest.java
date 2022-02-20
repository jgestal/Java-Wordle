package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.SpanishWordsProvider;
import model.Wordle;
import model.WordsProvider;

class WordleTest {
	
	Wordle sut; 
	
	class FakeWordsProvider implements WordsProvider {

		public static String secret = "PATAS";
		public static String[] invalidWords = {"EBANE", "AEIOUAAAAA","AAA", "EEEE", "IIIII", "OOOOO", "UUUUU", "AWERE", "AAHAH"};
		public static String[] validWords = { "BICHO", "FILIS", "POTES", "PIASE", "BALSA", "PATIO", "CASAS", "PEROS" };
		public static String invalidSizeWord = "A";	
		
		@Override
		public boolean isValidWorld(String word) {
			return Arrays.asList(validWords).contains(word.toUpperCase()) || word.equalsIgnoreCase(secret);
		}
		
		@Override
		public String getRandomWord() {
			return secret;
		}
	}
	
	void makeManyValidAttempts() {
		for (int i=0; i <= Wordle.MAX_ATTEMPTS; i++) {
			sut.makeAttempt(FakeWordsProvider.validWords[i]);
		}
	}
	
	@BeforeEach 
	void init() {
	      sut = new Wordle(new FakeWordsProvider());
	}

	@Test
	void test_whenInitialized_thenSecretIsNotNull() {
		
		assertNotNull(sut.getSecret());
	}
	
	@Test
	void test_whenInitialized_thenPlayerWinsIsFalse() {
		
		assertFalse(sut.playerWins());
	}
	
	@Test
	void test_whenGuessIsRight_thenPlayerWins() {
	
		sut.makeAttempt(sut.getSecret());
		assertTrue(sut.playerWins());
	}
	
	@Test
	void test_whenGuessIsNotRight_thenPlayerWinsIsFalse() {
		
		sut.makeAttempt(FakeWordsProvider.validWords[0]);
		assertFalse(sut.playerWins());
	}
	
	@Test
	void test_whenInitialized_thenRemainingAttemptsAreEqualToMaxAttempts() {
		
		assertEquals(Wordle.MAX_ATTEMPTS, sut.remainingAttempts(),0);
	}
	
	@Test
	void test_whenUserMakesAnAttempt_thenRemainingAttemptsAreEqualToMaxAttemptsMinusOne() {

		sut.makeAttempt(FakeWordsProvider.validWords[0]);
		int expectedAttempts = Wordle.MAX_ATTEMPTS - 1;
		
		assertEquals(sut.remainingAttempts(), expectedAttempts);		
	}

	@Test
	void test_whenUserMakesALotOfAttempts_thenRemainingAttemptsAreEqualToZero() {
		
		for (int i = 0; i <= Wordle.MAX_ATTEMPTS; i++) {
			sut.makeAttempt(FakeWordsProvider.validWords[i]);			
		}		

		assertEquals(sut.remainingAttempts(),0);
	}
	
	@Test
	void test_whenGameInit_thenGameIsNotOver() {
		
		assertFalse(sut.isGameOver());
	}

	@Test
	void test_whenUserGuessSecret_thenGameIsOver() {
		
		sut.makeAttempt(sut.getSecret());
		assertTrue(sut.isGameOver());
	}
	
	@Test
	void test_whenRemainingAttemptsAreEqualToZero_thenGameIsOver() {
		
		makeManyValidAttempts();	
		
		assertTrue(sut.isGameOver());
	}
	
	@Test
	void test_whenUserTypesSecretInUppercase_thenItDoesntReallyMatter() {
		
		sut.makeAttempt(sut.getSecret().toUpperCase());
		assertTrue(sut.isGameOver());
	}

	@Test
	void test_whenUserTypesSecretInLowercase_thenItDoesntReallyMatter() {
		
		sut.makeAttempt(sut.getSecret().toLowerCase());
		assertTrue(sut.isGameOver());
	} 
	
	@Test
	void test_whenUserMakesAWrongAttempt_thenIsNotGameOver() {
		
		sut.makeAttempt(FakeWordsProvider.invalidWords[0]);
		assertFalse(sut.isGameOver());
	}
	
	@Test
	void test_whenUserMakesManyAttempts_thenIsGameOver() {
		
		makeManyValidAttempts();
		
		assertTrue(sut.isGameOver());
	}
	
	@Test
	void test_whenUserMakesManyAttempts_thenTheAttemptsListStopsToGrow() {
	
		makeManyValidAttempts();
		assertEquals(sut.attempts().size(), Wordle.MAX_ATTEMPTS);
	}
	
	@Test
	void test_whenGameBegins_AttemptsListIsEmpty() {
		
		assertEquals(sut.attempts().size(),0);
	}
	
	@Test
	void test_whenUserMakesOneValidAttempt_thenAttemptsListSizeIsOne() {
		
		sut.makeAttempt(FakeWordsProvider.validWords[0]);
		assertEquals(sut.attempts().size(),1);
	}

	@Test
	void test_whenUserMakesTwoAttemptsThatAreTheSame_thenAttemptsListSizeIsOne() {
		sut.makeAttempt(FakeWordsProvider.validWords[0]);
		sut.makeAttempt(FakeWordsProvider.validWords[0]);
		assertEquals(sut.attempts().size(),1);
	}
	
	@Test
	void test_whenUserMakesOneValidAttempt_thenTheLastAttemptsListElementIsTheUserGuess() {
		
		sut.makeAttempt(FakeWordsProvider.validWords[0]);
		assertTrue(sut.attempts().get(0).equals(FakeWordsProvider.validWords[0]));
	}
	
	@Test
	void test_whenUserMakesOneValidAttempt_thenTheAttemptsListIsEmpty() {
		
		sut.makeAttempt(FakeWordsProvider.invalidWords[0]);
		assertEquals(sut.attempts().size(),0);
	}
	
	@Test
	void test_whenGuessLengthIsInvalid_ThenTheAttemptIsInvalid() {
		sut.makeAttempt(FakeWordsProvider.invalidSizeWord);
	}
	
}
