package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import model.SpanishWordsProvider;

class SpanishWordsProviderTest {

	private static String invalidFilename = "invalid-file.txt";
	
	@Test
	void test_whenSourceFilenameDoesntExists_thenThrowsFileNotFoundException() {
		
		Exception exception = assertThrows(FileNotFoundException.class, () -> {
			new SpanishWordsProvider(invalidFilename);		
		});
		
		String expectedeMessage = "The system cannot find the file specified";
		String actualMessage = exception.getMessage();		
		
		assertTrue(actualMessage.contains(expectedeMessage));
	}
	
	@Test
	void test_whenSourceFilenameExists_thenGetRandomWordIsNotNull() throws Exception {
		
		SpanishWordsProvider sut = new SpanishWordsProvider("words-es.txt");
		assertNotNull(sut.getRandomWord());
	}
	
	@Test
	void test_RandomWord_isAValidWordEventIfItIsUppercasedOrLowercased() throws Exception {
	
		SpanishWordsProvider sut = new SpanishWordsProvider("words-es.txt");
		
		String word = sut.getRandomWord();

		assertTrue(sut.isValidWorld(word.toUpperCase()));
		assertTrue(sut.isValidWorld(word.toLowerCase()));		
	}
	
	
	
}
