package code;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveAndReadMovies {
    public static void save(String nameToWrite, List<ActorsDataBase> objectsToSave) {
        try (ObjectOutputStream writeCollection = new ObjectOutputStream(
                new FileOutputStream(nameToWrite))) {

            writeCollection.writeObject(objectsToSave);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ActorsDataBase> open(String nameToRead) {
        List<ActorsDataBase> objects = new ArrayList<>();
        try (ObjectInputStream readCollection = new ObjectInputStream(
                new FileInputStream(nameToRead))) {

            objects = (List<ActorsDataBase>) readCollection.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist - " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return objects;
    }
}
