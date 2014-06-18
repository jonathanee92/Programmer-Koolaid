import DocIdGenerator.DocIdGenerator;
import DocumentReader.DocReader;
import DocumentReader.PlainTextReader;
import Model.DocWordIndex;
import Model.InvertedIndex;
import Tokenizer.SimpleTokenizer;
import Tokenizer.Tokenizer;
import Tokenizer.WordAtIndex;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DocumentController {
    private DocReader docReader = new PlainTextReader();
    private InvertedIndex invertedIndex = new InvertedIndex();
    private Tokenizer tokenizer = new SimpleTokenizer();
    private DocIdGenerator docIdGenerator;

    public void addDocumentToIndex(File file) {
        try {
            String text = docReader.getTextFromFile(file);
            WordAtIndex[] wordWithIndices = tokenizer.getWordTokensFromText(text);
//            String documentID = docIdGenerator.getDocIdForFile(file);
            String documentID = file.getName(); // TODO

            invertedIndex.addDocumentToInvertedIndex(documentID, wordWithIndices);
        } catch (Exception ex) {
            // LOG
        }
    }

    public void removeDocumentFromIndex(String fileName) {
        // TODO
    }

    public Iterator<String> getDocumentsForQuery(String query) {
        List<String> documentsMatchingQuery = new LinkedList<String>();
        Iterator<DocWordIndex> docWordIndicesForWord = invertedIndex.getDocWordIndicesForWord(query);
        while (docWordIndicesForWord != null && docWordIndicesForWord.hasNext()) {
            DocWordIndex docWordIndex = docWordIndicesForWord.next();
            documentsMatchingQuery.addAll(docWordIndex.getDocumentWithWordIndices().keySet());
        }

        return (documentsMatchingQuery.size() > 0) ? documentsMatchingQuery.iterator() :
                null;
    }

    private static void printDocuments(Iterator<String> documents) {
        while(documents.hasNext()) {
            System.out.println(documents.next());
        }
    }

    public static void main(String [] args) {
        DocumentController documentController = new DocumentController();
        File docFolder = new File("src/main/resources/Documents");
        for(File document : docFolder.listFiles()) {
            documentController.addDocumentToIndex(document);
        }
        Iterator<String> matchingDocs = documentController.getDocumentsForQuery("love");
        printDocuments(matchingDocs);

        Scanner scanner = new Scanner(System.in);
        String query = "";
        System.out.println("Type your query...");
        while(!query.equals("quit")) {
            query = scanner.nextLine();
            Iterator<String> docsForQuery = documentController.
                    getDocumentsForQuery(query.toLowerCase());
            if(docsForQuery != null) {
                printDocuments(docsForQuery);
            } else {
                System.out.println("Nothing matches :(");
            }
        }
    }
}
