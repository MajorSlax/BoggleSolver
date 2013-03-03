package com.pavageau.boggle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pavageau
 * 
 *         Converts a text file containing one word per line into a Map<String,
 *         Boolean> where the keys are all the 2+ letter sequences that have a
 *         word beginning with them, and the values are true if that sequence is
 *         an actual word, false otherwise
 */
public class Dictionary {

	private final Map<String, Boolean> dictMap = new HashMap<String, Boolean>();

	/**
	 * @param path
	 *            the path of the file to use as a source
	 */
	public Dictionary(String path) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(path));

			String word;
			while ((word = in.readLine()) != null) {
				word = word.toLowerCase();
				// ignoring words containing non a-z characters, words shorter
				// than 2 or longer than 16 characters
				if (word.matches("[a-z]{2,16}")) {
					int wordLength = word.length();
					for (int i = 2; i < wordLength; i++) {
						String radix = word.substring(0, i);
						if (!dictMap.containsKey(radix)) {
							dictMap.put(radix, false);
						}
					}
					// true for key=word
					dictMap.put(word, true);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return the formatted dictionary as mentioned in the class javadoc
	 */
	public Map<String, Boolean> getWordMap() {
		return dictMap;
	}
}
