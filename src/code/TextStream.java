package code;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TextStream implements ReadAndWriteKeyWords {

    public static void manageFile(String fileReadName, String fileWriteName) {
        Map<String, Integer> keyWords = new HashMap();
        keyWordsToMap(keyWords);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReadName));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWriteName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                countKeyWords(line, keyWords);
            }
            writeFile(bufferedWriter, keyWords);
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist - " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void countKeyWords (String readLine, Map<String, Integer> keyWords){
        String[] words = readLine.split("[ .,()<>]+");
        for (String word : words) {
            for (String keyWord : KEYWORDS) {
                if (word.equals(keyWord)) {
                    keyWords.put(keyWord, keyWords.get(keyWord) + 1);
                    break;
                }
            }
        }
    }

    private static void writeFile(BufferedWriter bufferedWriter, Map<String, Integer> keyWords) throws IOException {
        bufferedWriter.write("name,count\n");
        for (Map.Entry entry : keyWords.entrySet()) {
            if ((int) entry.getValue() > 0) {
                bufferedWriter.write(String.valueOf(entry.getKey()));
                bufferedWriter.write("," + entry.getValue() + "\n");
            }
        }
    }

    private static void keyWordsToMap(Map<String, Integer> keyWords){
        for (String keyWord : KEYWORDS) {
            keyWords.put(keyWord, 0);
        }
    }
}
