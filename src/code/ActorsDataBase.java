package code;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActorsDataBase implements Serializable {
    private Map<String, List<String>> actorsInFilms = new HashMap();

    public void addActorOrMovie(String actor, String film) {
        if (!actorsInFilms.containsKey(actor)) {
            actorsInFilms.put(actor, new ArrayList<String>());
        }
        actorsInFilms.get(actor).add(film);
    }

    public void dropMovieFromActor(String actor, String movie) {
        if (!actorsInFilms.containsKey(actor)) {
            System.out.println("This actor doesn't exist in your collection");
            return;
        }
        if(!actorsInFilms.get(actor).contains(movie)){
            System.out.println("This movie doesn't exist in your collection");
            return;
        }
        actorsInFilms.get(actor).removeIf(x -> x.equals(movie));
    }

    public void dropActor(String actor) {
        if (!actorsInFilms.containsKey(actor)) {
            System.out.println("Actor " + actor + " doesn't exist in your collection");
            return;
        }
        actorsInFilms.remove(actor);
    }

    public void changeMovieTitle(String actor, String oldTitle, String newTitle) {
        if (!actorsInFilms.containsKey(actor)) {
            System.out.println("Actor " + actor + " doesn't exist in your collection");
            return;
        }
        int index;
        for (String x : actorsInFilms.get(actor)) {
            if (x.equals(oldTitle)) {
                index = actorsInFilms.get(actor).indexOf(x);
                actorsInFilms.get(actor).set(index, newTitle);
                return;
            }
        }
        System.out.println("This movie doesn't exist in your collection");
    }

    public Map<String, List<String>> getAll() {
        return actorsInFilms;
    }

    public List<String> getFilmsFromActor(String actor) {
        return actorsInFilms.get(actor);
    }
}
