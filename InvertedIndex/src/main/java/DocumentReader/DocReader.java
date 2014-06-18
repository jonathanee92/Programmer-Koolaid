package DocumentReader;

import java.io.File;
import java.io.IOException;

public interface DocReader {
    String getTextFromFile(File file) throws IOException;
}
