package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SpanishWordsProvider implements WordsProvider {
	
	private List<String> wordsList = new ArrayList<String>();
	
	public SpanishWordsProvider(String sourceFilename) throws Exception {
		loadWordsFromFile(sourceFilename);
	}

	private void loadWordsFromFile(String sourceFilename) throws Exception {
	
		BufferedReader br = new BufferedReader(new FileReader(sourceFilename));
		String line = br.readLine();		
		
		while (line != null) {
			wordsList.add(line.toUpperCase());
			line = br.readLine();
		}		
		
		br.close();
	}

	@Override
	public boolean isValidWorld(String word) {
		word = word.toUpperCase();
		return wordsList.contains(word);
	}

	@Override
	public String getRandomWord() {
		int index = (int)(Math.random() * wordsList.size());
		return wordsList.get(index);
	}
}
