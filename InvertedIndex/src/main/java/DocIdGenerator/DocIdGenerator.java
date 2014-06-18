package DocIdGenerator;

import java.io.File;
import java.io.IOException;

public interface DocIdGenerator {
    String getDocIdForFile(File file) throws IOException;
}
