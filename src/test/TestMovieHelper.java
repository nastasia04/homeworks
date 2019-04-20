package test;
import code.ActorsDataBase;
import code.SaveAndReadMovies;
import java.io.File;
import java.util.ArrayList;
import java.util.*;

public class TestMovieHelper {

    public File saveOneStructure(ActorsDataBase structure, String structureSaveName){
        List <ActorsDataBase> structureToSave = new ArrayList<>();
        structureToSave.add(structure);
        File file = new File(structureSaveName);
        SaveAndReadMovies.save(structureSaveName, structureToSave);
        return file;
    }
    public File saveManyStructures(List <ActorsDataBase> structureToSave, String structureSaveName){
        File file = new File(structureSaveName);
        SaveAndReadMovies.save(structureSaveName, structureToSave);
        return file;
    }


    public ActorsDataBase addStandardStructure(ActorsDataBase structure) {
        structure.addMovie( "Forrest Gump");
        structure.addMovie( "The Green Mile");

        structure.addActorAtMovie("Forrest Gump", "Tom", "Hanks");
        structure.addActorAtMovie("Forrest Gump", "Robin", "Wright");
        structure.addActorAtMovie("The Green Mile", "Tom", "Hanks");

        return structure;
    }

    public List<String> checkStandardMovies() {

        List<String> constantStructure = new ArrayList<>();
        constantStructure.add("Forrest Gump");
        constantStructure.add( "The Green Mile");
        return constantStructure;
    }

    public List<List<String>> checkStandardActors() {

        List<List<String>> constantStructure = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add("Robin Wright");
        temp.add("Tom Hanks");
        Collections.sort(temp);
        constantStructure.add(temp);
        temp = new ArrayList<>();
        temp.add("Tom Hanks");
        constantStructure.add(temp);
        return constantStructure;
    }
}
