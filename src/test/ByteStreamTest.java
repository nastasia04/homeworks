package test;
import code.ByteStream;
import org.junit.*;
import org.junit.rules.TestName;
import java.io.*;

public class ByteStreamTest {
    private ByteStream fileKeyWords;
    private String expectedResultName;
    private String testFileName;
    private String actualResult;
    private static final String prefix = "src/test/testData/Streams/";
    private File file;

    @Rule
    public TestName name = new TestName();

    @Before
    public void setUp() {
        fileKeyWords = new ByteStream();
        String testName = name.getMethodName();
        testFileName = prefix + testName + ".java";
        expectedResultName = prefix + testName + ".csv";
        actualResult  = "Result.csv";
        file = new File(actualResult);
        fileKeyWords.manageFail(testFileName, actualResult);
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
    public void enterEmptyfile() {

        StreamTestHelper.compareTwoFiles(expectedResultName, actualResult);
    }

    @Test
    public void readNotJavaFileWithoutKeyWords() {
        StreamTestHelper.compareTwoFiles(expectedResultName, actualResult);
    }

    @Test
    public void enterfileDoesnotExist() {
       Assert.assertFalse(file.exists());
    }


}
