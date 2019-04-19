package code;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Actor implements Serializable {
private String actorName;
private String actorSurname;

public Actor(String actorName, String actorSurname){
    this.actorName = actorName;
    this.actorSurname = actorSurname;
}
public String getActor(){
    return actorName + " " + actorSurname;
}
}
