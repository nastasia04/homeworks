package code;
import java.io.Serializable;
import java.util.*;

public class Film implements Serializable {
    private Set <Actor> actors;
    private String movieTitle;
    public Film(String movieTitle){
        actors = new HashSet<>();
        this.movieTitle = movieTitle;
    }
    public void addActorToFilm(String name, String surName){
        actors.add(new Actor(name, surName));
    }
    public void deleteActorFromFilm(String name, String surName){
        if(!actorInFilm(name, surName)){
            System.out.println("This actor doesn't exist in this film");
        }
        actors.removeIf(actor -> actor.getActor().equals(name+" "+surName));
    }
    public boolean actorInFilm(String name, String surName){
        return actors.stream()
                .anyMatch(actor -> actor.getActor().equals(name+" "+surName));
    }

    public String getTitle(){
        return movieTitle;
    }
    public void changeTitle(String movieTitle){
        this.movieTitle = movieTitle;
    }
    public Set<Actor> getActors(){
        return actors;
    }
}
