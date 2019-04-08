package test;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StreamTestHelper {
    public static void compareTwoFiles(String fileNameExpect, String fileNameResult) {
        List<String> actualResultLines = new ArrayList<>();
        List<String> expectResultLines = new ArrayList<>();

        try (BufferedReader realResult = new BufferedReader(new FileReader(fileNameResult));
             BufferedReader expectedResult = new BufferedReader(new FileReader(fileNameExpect))) {

            readLine(realResult, actualResultLines);
            readLine(expectedResult, expectResultLines);

            Assert.assertNotNull("Actual result is null", actualResultLines);

            Assert.assertEquals("The sizes of files aren't the same", expectResultLines.size(), actualResultLines.size());

            while (!actualResultLines.isEmpty()) {
                Assert.assertEquals("Not the same lines in results", expectResultLines.get(0), actualResultLines.get(0));
                expectResultLines.remove(0);
                actualResultLines.remove(0);

            }
        } catch (FileNotFoundException e) {
            Assert.fail("File doesn't exist - " + e.getMessage());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    private static List<String> readLine (BufferedReader Result, List<String> readlines) throws IOException{
        String line;
            while ((line = Result.readLine()) != null) {
                readlines.add(line);
        }
        Collections.sort(readlines);
        return readlines;
    }
}
