package code;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TextStream implements ReadAndWriteKeyWords {

    public Map<String, Integer> KeyWords = new HashMap();

    {
        for (String x : KEYWORDS) {
            KeyWords.put(x, 0);
        }
    }

    public void manageFile(String fileReadName, String fileWriteName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileReadName));
             BufferedWriter wr = new BufferedWriter(new FileWriter(fileWriteName))) {
            wr.write("name,count\n");
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("[ .,()<>]+");
                for (String word : words) {
                    for (String x : KEYWORDS) {
                        if (word.equals(x)) {
                            KeyWords.put(x, KeyWords.get(x) + 1);
                            break;
                        }
                    }
                }
            }
            writeFile(wr);
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist - " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeFile(BufferedWriter wr) throws IOException {

        for (Map.Entry entry : KeyWords.entrySet()) {
            if ((int) entry.getValue() > 0) {
                wr.write(String.valueOf(entry.getKey()));
                wr.write("," + entry.getValue() + "\n");
            }
        }
    }
}
