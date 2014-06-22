package Model;

import Tokenizer.WordAtIndex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class InvertedIndex {
    // the key is the word and the value is the Model.DocWordIndex > the doc id and
    // the line index in that document for that word
    private Map<String, LinkedList<DocWordIndex>> invertedIndex = new
            HashMap<String, LinkedList<DocWordIndex>>();

    public void addDocument(String documentID, WordAtIndex[] wordsWithIndices) {
        // Cache contains key (word) and value (list of indices where word is in document)
        Map<String, LinkedList<Integer>> cache = new HashMap<String, LinkedList<Integer>>();
        for (int i = 0; i < wordsWithIndices.length; i++) {
            addWordToCache(wordsWithIndices[i].getWord(),
                    wordsWithIndices[i].getIndex(), cache);
        }

        for (String word : cache.keySet()) {
            LinkedList<Integer> wordIndicesList = cache.get(word);
            Integer[] indices = wordIndicesList.toArray(new
                    Integer[wordIndicesList.size()]);
            DocWordIndex docWordIndex = new DocWordIndex(documentID, indices);
            addDocWordIndex(word, docWordIndex);
        }
    }

    private void addWordToCache(String word, Integer wordIndex, Map<String,
            LinkedList<Integer>> cache) {
        if (!cache.containsKey(word)) {
            cache.put(word, new LinkedList<Integer>());
        }
        cache.get(word).add(wordIndex);
    }

    private void addDocWordIndex(String word, DocWordIndex docWordIndex) {
        if (!invertedIndex.containsKey(word)) {
            invertedIndex.put(word, new LinkedList<DocWordIndex>());
        }
        invertedIndex.get(word).add(docWordIndex);
    }

    public Iterator<DocWordIndex> getDocWordIndicesForWord(String word) {
        LinkedList<DocWordIndex> docWordIndexes = invertedIndex.get(word);
        return (docWordIndexes != null) ? docWordIndexes.iterator() : null;
    }

    public void deleteDocumentFromIndex(String fileName) {

    }
}