package test.testData.Streams;

import java.util.Date;

public class testComplexVariableNames {
    private String title;
    private String body;
    private Date intdate;

    public testComplexVariableNames(String title, String body) {
        this.body = body;
        this.title = title;
        this.intdate = new Date();
    }

    public void publicEditNote(String body) {
        this.body = body;
        this.intdate = new Date();
    }

    public String publicGetNoteBody() {
        return this.body;
    }

    public String publicGetNoteTitle() {
        return this.title;
    }

    public Date publicGetNoteDate() {
        return this.intdate;
    }
}
