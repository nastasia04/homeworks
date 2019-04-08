package test;

import code.ActorsDataBase;
import code.SaveAndReadMovies;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


    public ActorsDataBase addStandartStructure(ActorsDataBase structure) {
        structure.addActorOrMovie("Tom Hanks", "Forrest Gump");
        structure.addActorOrMovie("Robin Wright", "Forrest Gump");
        structure.addActorOrMovie("Tom Hanks", "The Green Mile");
        return structure;
    }
    public HashMap<String, List<String>> checkStandardtStructure() {

        HashMap<String, List<String>> constantStructure = new HashMap<>();
        updateStructure(constantStructure, "Tom Hanks", new String[]{"Forrest Gump", "The Green Mile"});
        updateStructure(constantStructure, "Robin Wright", new String[]{"Forrest Gump"});

        return constantStructure;
    }

    private HashMap<String, List<String>> updateStructure(HashMap<String, List<String>> constantStructure,
                            String actor,
                            String [] movies){
        List<String> tempMovies = new ArrayList<>();
        for(String x : movies){
            tempMovies.add(x);
        }
        constantStructure.put(actor, tempMovies);
        return constantStructure;
    }
}
