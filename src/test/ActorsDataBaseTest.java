package test;

import code.ActorsDataBase;
import code.SaveAndReadMovies;
import org.junit.*;

import java.io.File;
import java.util.*;
import java.util.List;

public class ActorsDataBaseTest {
    private ActorsDataBase structure;
    private List<ActorsDataBase> structureToSave, structureToRead;
    private List<String> expectedMovies;
    TestMovieHelper helper = new  TestMovieHelper();

    private final String prefix = "src/test/testData/ActorsDataBase/";
    private final String structureNamePrev = prefix + "saveActorsAndMovies.out";
    private final String structureNameMiddle = prefix + "saveActorsAndMoviesMiddle.out";
    private final String structureNameFinal = prefix + "saveActorsAndMoviesFinal.out";
    private File file;


    @Test
    public void addActorsAndMoviesAndSaveStructure() {
        structure = new ActorsDataBase();
        structure = helper.addStandartStructure(structure);
        Assert.assertNotNull("Structure with actors is empty", structure.getAll());
        Assert.assertEquals("Not all actors or movies in the structure",
                helper.checkStandardtStructure(),
                structure.getAll());
        // save structure
        file = helper.saveOneStructure(structure, structureNamePrev);
        Assert.assertTrue("Structure wasn't saved", file.exists());
    }

    @Test
    public void addOneActorAndTwoMovies() {
        structure = new ActorsDataBase();
        structure = helper.addStandartStructure(structure);

        expectedMovies = new ArrayList<>();
        expectedMovies.add("Forrest Gump");
        expectedMovies.add("The Green Mile");

        Assert.assertEquals("This actor was in two movies",
                expectedMovies,
                structure.getFilmsFromActor("Tom Hanks"));
    }


    @Test
    public void readSavedStructureAndChangeIt() {
        structureToRead = SaveAndReadMovies.open(structureNamePrev);
        Assert.assertTrue("There no one saved structure", structureToRead.size() > 0);

        //check saved structure
        structure = structureToRead.get(0);
        Assert.assertEquals("Not all actors or movies in the structure",
                helper.checkStandardtStructure(),
                structure.getAll());

        //change movie title
        structure.changeMovieTitle("Robin Wright", "Forrest Gump", "Форрест Гамп");
        expectedMovies = new ArrayList<>();
        expectedMovies.add("Форрест Гамп");
        Assert.assertEquals("This actor was in more than one movie",
                expectedMovies,
                structure.getFilmsFromActor("Robin Wright"));

        //drop the actor
        structure.dropActor("Robin Wright");
        Assert.assertNull("There is not this actor", structure.getFilmsFromActor("Robin Wright"));

        //delete movie
        structure.dropMovieFromActor("Tom Hanks", "Forrest Gump");
        expectedMovies = new ArrayList<>();
        expectedMovies.add("The Green Mile");
        Assert.assertEquals("The movie wasn't deleted",
                expectedMovies,
                structure.getFilmsFromActor("Tom Hanks"));
        //save structure
        file = helper.saveOneStructure(structure, structureNameMiddle);
        Assert.assertTrue("Structure wasn't saved", file.exists());
    }

    @Test
    public void SaveAndReadMoreThanOneObjects() {
        //add three structures
        int count = 3;
        structureToSave = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            structure = new ActorsDataBase();
            structure =  helper.addStandartStructure(structure);
            structureToSave.add(structure);
        }

        //save three structures
        file = helper.saveManyStructures(structureToSave, structureNameFinal);
        Assert.assertTrue("Structures weren't saved", file.exists());

        //read three structures
        structureToRead = SaveAndReadMovies.open(structureNameFinal);
        Assert.assertTrue("There aren't saved structures", structureToRead.size() > 0);

        //check saved structure
        while (structureToRead.size() > 0) {
            structure = structureToRead.get(0);
            Assert.assertEquals("The structure is incorrect",
                    helper.checkStandardtStructure(), structure.getAll());
            structureToRead.remove(0);
        }
    }

    @Test
    public void readFromFileWhichDoesNotExist() {
        structure = new ActorsDataBase();
        structureToRead = SaveAndReadMovies.open("NameDoesNotExist.txt");
        Assert.assertFalse("There isn't saved structure", structureToRead.size() > 0);
    }

    //add or edit something that doesn't exist
    @Test
    public void doesNotExist() {
        structure = new ActorsDataBase();
        structure.dropActor("Robin Wright");
        structure.changeMovieTitle("Tom Hanks", "Forrest Gump", "Форрест Гамп");
        structure.dropMovieFromActor("Tom Hanks", "Forrest Gump");
        Assert.assertTrue("This structure should be empty", structure.getAll().isEmpty());
    }




}
