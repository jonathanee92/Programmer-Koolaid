package StopWordStore;

import java.util.*;

public class SimpleStopWordStore implements StopWordStore {
    private Set<String> stopWords = new HashSet<String>();

    public SimpleStopWordStore() {
        stopWords.addAll(Arrays.asList(new String[]{"I", "a", "about", "an",
                "are","as", "at", "be", "by", "com", "for", "from", "how",
                "in", "is", "it", "of", "on", "or", "that", "the", "this",
                "to", "was", "what", "when", "where", "who", "will", "with",
                "the", "www"}));
    }

    public void addWordToStore(String word) {
        stopWords.add(word);
    }

    public void removeWordFromStore(String word) {
        stopWords.remove(word);
    }

    public void addWordsToStore(List<String> words) {
        stopWords.addAll(words);
    }

    public Iterator<String> getStopWords() {
        return stopWords.iterator();
    }

    public boolean isWordInStore(String word) {
        return stopWords.contains(word);
    }
}
