import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class UserCmdController {
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
        scanner.close();
    }
}
