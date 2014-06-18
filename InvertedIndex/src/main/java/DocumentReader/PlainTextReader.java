package DocumentReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PlainTextReader implements DocReader {
    public String getTextFromFile(File file) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		while(line != null) {
			stringBuilder.append(line + "\n");
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		
		return stringBuilder.toString();
	}
}
