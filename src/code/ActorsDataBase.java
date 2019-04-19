package code;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;
import java.util.stream.*;



public class ActorsDataBase implements Serializable {
    private List <Film> movies = new ArrayList<>();

    public void addMovie(String filmTitle) {
        if(isMovieExist(filmTitle)){
            System.out.println("This movie is exist in your collection yet");
            return;
        }
        Film temp = new Film(filmTitle);
        movies.add(temp);
    }
    
    public void addActorAtMovie(String movieTitle, String nameActor, String surNameActor) {
        if(!isMovieExist(movieTitle)){
            System.out.println("This movie doesn't exist in your collection");
            return;
        }
        movies = movies.stream().
                peek(film -> {if (film.getTitle().equals(movieTitle))
                    film.addActorToFilm(nameActor,surNameActor);}).
                collect(Collectors.toList());
    }
    
    public void dropActor(String movieTitle, String nameActor, String surNameActor) {
        if(!isMovieExist(movieTitle)){
            System.out.println("This movie doesn't exist in your collection");
            return;
        }
        movies = movies.stream().
                peek(film -> {if(film.getTitle().equals(movieTitle) && film.getActors().stream().
                        anyMatch(actors -> actors.getActor().equals(nameActor + " " + surNameActor))){
                    film.deleteActorFromFilm(nameActor,surNameActor);
                }
                }).
                collect(Collectors.toList());
    }

    public void dropMovie(String movieTitle) {
        if(!isMovieExist(movieTitle)){
            System.out.println("This movie doesn't exist in your collection");
            return;
        }
        movies.removeIf(film -> film.getTitle().equals(movieTitle));
    }


    public void changeMovieTitle(String oldMovieTitle, String newMovieTitle) {
        if(!isMovieExist(oldMovieTitle)){
            System.out.println("This movie doesn't exist in your collection");
            return;
        }
        movies = movies.stream().
                peek(film -> {if(film.getTitle().equals(oldMovieTitle)){
                    film.changeTitle(newMovieTitle);
                }
                }).
                collect(Collectors.toList());
    }

    public List<String> getFilmsFromActor(String actorName, String actorSurName) {
        List<String> temp = movies.stream().
                filter(film -> film.actorInFilm(actorName,actorSurName)).
                map(film -> film.getTitle()).
                collect(Collectors.toList());
        if(temp.size() > 0)
            return temp;
         return null;
    }

    public List<String> getAllMoviesTitle() {
        return movies.stream().map(film -> film.getTitle()).collect(Collectors.toList());

    }
    public List<List<String>> getAllActorsNames() {
        List<List<String>> temp;
        temp = movies.stream().map(film ->
                film.getActors().stream().map(actor->actor.getActor()).collect(Collectors.toList()))
                .collect(Collectors.toList());
        return temp.stream().peek(actorsList ->Collections.sort(actorsList)).collect(Collectors.toList());

    }

    private boolean isMovieExist(String movieTitle){
        return movies.stream()
                .anyMatch(film -> film.getTitle().equals(movieTitle));
    }
}
