package code;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ByteStream implements ReadAndWriteKeyWords {
    

    public static void manageFile(String fileReadName, String fileWriteName) {

        Map<byte[], Integer> keyWords = new HashMap();
        keyWordsToBite(keyWords);

        try (BufferedInputStream fileRead =
                     new BufferedInputStream(new FileInputStream(fileReadName));
             BufferedOutputStream fileWrite =
                     new BufferedOutputStream(new FileOutputStream(fileWriteName))) {
            int bytesAvailable = fileRead.available();
            System.out.println("Ready to read: " + bytesAvailable + " bytes");
            byte[] containedWords = new byte[bytesAvailable];
            int bytesRead = fileRead.read(containedWords);
            System.out.println("Read: " + bytesRead + " bytes");
            countKeyWords(containedWords, bytesRead, keyWords);
            writeFile(fileWrite, keyWords);
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist - " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeFile(BufferedOutputStream fileWrite, Map<byte[], Integer> keyWords) throws IOException {

        fileWrite.write(("name,count\n").getBytes());
        for (byte[] arrayKeyWord : keyWords.keySet()) {
            if (keyWords.get(arrayKeyWord) > 0) {
                fileWrite.write(arrayKeyWord);
                fileWrite.write(("," + keyWords.get(arrayKeyWord)).getBytes());
                fileWrite.write(System.getProperty("line.separator").getBytes());
            }
        }
    }


    private static void countKeyWords(byte[] containedWords, int bytesRead, Map<byte[], Integer> keyWords ){
        byte[] separators = new byte[8];
        char[] separator = {' ', ',', '.', '(', ')', '<', '>', '\n'};

        for (int i = 0; i < separator.length; i++) {
            separators[i] = (byte) separator[i];
        }

        int pointer = 0;
        byte[] buffer = new byte[bytesRead];
        for (byte word : containedWords) {
            if (contains(separators, word)) {
                byte[] arrayToCopy = Arrays.copyOfRange(buffer, 0, pointer);
                for (byte[] byteKeyWord : keyWords.keySet()) {
                    if (Arrays.equals(byteKeyWord, arrayToCopy)) {
                        keyWords.put(byteKeyWord, keyWords.get(byteKeyWord) + 1);
                        break;
                    }
                }
                buffer = new byte[bytesRead];
                pointer = 0;
            } else {
                buffer[pointer] = word;
                pointer++;
            }
        }
    }

    private static void keyWordsToBite(Map<byte[], Integer> keyWords){
        for (String keyWord : KEYWORDS) {
            keyWords.put(keyWord.getBytes(), 0);
        }
    }

    private static boolean contains(byte[] array, byte value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }
}
