package Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains the DocumentID + List of indices for where the word is in the document
 *
 */
public class DocWordIndex {
	private Map<String, Integer[]> documentWordIndex = new HashMap<String, Integer[]>();
	
	private DocWordIndex() {}
	
	public DocWordIndex(String documentID, Integer[] wordIndices) {
		documentWordIndex.put(documentID, wordIndices);	
	}
	
	public Integer[] getWordIndicesForDocument(String documentID) {
		return documentWordIndex.get(documentID);
	}
	
	public Map<String, Integer[]> getDocumentWithWordIndices() {
		// return a clone of this
		return documentWordIndex;
	}
}
