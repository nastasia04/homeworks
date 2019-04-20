package test;
import code.TextStream;
import org.junit.*;
import org.junit.rules.TestName;
import java.io.*;


public class TextStreamTest {
    private TextStream fileKeyWords;
    private String expectedResultName;
    private String testFileName;
    private String actualResult;
    private final String prefix = "src/test/testData/Streams/";
    private File file;

    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() {
        fileKeyWords = new TextStream();
        String testName = name.getMethodName();
        testFileName = prefix + testName + ".java";
        expectedResultName = prefix + testName + ".csv";
        actualResult = "Result.csv";
        file = new File(actualResult);
        fileKeyWords.manageFile(testFileName, actualResult);
    }

    @After
    public void tearDown() {

        file.delete();
    }

    @Test
    //check that resultFile doesn't contain words like: intString
    public void testComplexVariableNames() {

        StreamTestHelper.compareTwoFiles(expectedResultName, actualResult);
    }

    @Test
    public void testReadingFromEmptyFile() {

        StreamTestHelper.compareTwoFiles(expectedResultName, actualResult);
    }

    @Test
    public void testReadingFromFileWithoutKeyWords() {
        StreamTestHelper.compareTwoFiles(expectedResultName, actualResult);
    }

    @Test
    public void testReadingFromFileWhichDoesnotExist() {

        Assert.assertFalse(file.exists());
    }


}
