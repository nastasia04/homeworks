package code;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ByteStream implements ReadAndWriteKeyWords {
    Map<byte[], Integer> KeyWords = new HashMap();

    {
        for (String i : KEYWORDS) {
            KeyWords.put(i.getBytes(), 0);
        }
    }

    public void manageFile(String fileReadName, String fileWriteName) {

        try (BufferedInputStream fileRead =
                     new BufferedInputStream(new FileInputStream(fileReadName));
             BufferedOutputStream fileWrite =
                     new BufferedOutputStream(new FileOutputStream(fileWriteName))) {
            int bytesAvailable = fileRead.available();
            System.out.println("Ready to read: " + bytesAvailable + " bytes");
            byte[] containedWords = new byte[bytesAvailable];
            int no_bytes_read = fileRead.read(containedWords);
            System.out.println("Read: " + no_bytes_read + " bytes");

            byte[] separators = new byte[8];
            char[] separator = {' ', ',', '.', '(', ')', '<', '>', '\n'};

            for (int i = 0; i < separator.length; i++) {
                separators[i] = (byte) separator[i];
            }

            int pointer = 0;
            byte[] buffer = new byte[no_bytes_read];
            for (byte x : containedWords) {
                if (contains(separators, x)) {
                    byte[] z = Arrays.copyOfRange(buffer, 0, pointer);
                    for (byte[] y : KeyWords.keySet()) {
                        if (Arrays.equals(y, z)) {
                            KeyWords.put(y, KeyWords.get(y) + 1);
                            break;
                        }
                    }
                    buffer = new byte[no_bytes_read];
                    pointer = 0;
                } else {
                    buffer[pointer] = x;
                    pointer++;
                }
            }

            fileWrite.write(("name,count\n").getBytes());
            for (byte[] x : KeyWords.keySet()) {
                if (KeyWords.get(x) > 0) {
                    fileWrite.write(x);
                    fileWrite.write(("," + KeyWords.get(x)).getBytes());
                    fileWrite.write(System.getProperty("line.separator").getBytes());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist - " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean contains(byte[] a, byte value) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == value) {
                return true;
            }
        }
        return false;
    }
}
