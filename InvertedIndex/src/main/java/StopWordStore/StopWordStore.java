package StopWordStore;

import java.util.Iterator;
import java.util.List;

public interface StopWordStore {
    void addWordToStore(String word);
    void removeWordFromStore(String word);
    void addWordsToStore(List<String> words);
    Iterator<String> getStopWords();
    boolean isWordInStore(String word);
}
