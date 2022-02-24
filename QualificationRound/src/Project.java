import java.util.ArrayList;

public class Project {

    String name;
    int days;
    int award;
    int bestBefore;
    ArrayList<Contributor> contributors = new ArrayList<>();
    ArrayList<Skill> skills;

    public Project(String name, int days, int award, int bestBefore, ArrayList<Skill> skills){
        this.name = name;
        this.days = days;
        this.award = award;
        this.bestBefore = bestBefore;
        this.skills = skills;
    }





}
