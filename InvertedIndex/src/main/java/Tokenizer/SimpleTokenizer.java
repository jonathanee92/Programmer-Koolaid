package Tokenizer;

import StopWordStore.SimpleStopWordStore;
import StopWordStore.StopWordStore;

import java.util.LinkedList;
import java.util.List;

public class SimpleTokenizer implements Tokenizer {
	private StopWordStore stopWords = new SimpleStopWordStore();
    private static final String specialCharactersRegex =
            "[?\\.!\\*\\t\\,\\#\\d\\<\\>\\[\\]\\;\\:\\\"\\\\\\/\\+\\@\\#\\^\\%\\$]";

	public WordAtIndex[] getWordTokensFromText(String text) {
        List<WordAtIndex> wordAtIndices = new LinkedList<WordAtIndex>();
        String[] textSplit = text.split("\n");
        for (int i = 0; i < textSplit.length; i++) {
            String textLine = textSplit[i].replaceAll(specialCharactersRegex, " ");
            textLine = textLine.toLowerCase();

            String[] words = textLine.split(" ");
            for(String word : words) {
                if(word.length() > 2 && !stopWords.isWordInStore(word)) {
                    wordAtIndices.add(new WordAtIndex(word, i));
                }
            }
        }
        return wordAtIndices.toArray(new WordAtIndex[wordAtIndices.size()]);
    }

    private String[] getNonStopWords(List<String> words) {
        List<String> nonStopWords = new LinkedList<String>();
        for(String word : words) {
            if(!stopWords.isWordInStore(word)) {
                nonStopWords.add(word);
            }
        }

        return nonStopWords.toArray(new String[nonStopWords.size()]);
    }
}
