import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Contributor {

    String name;
    ArrayList<Skill> skills;
    int daysBeforeFree = 0;

    Contributor(String name, ArrayList<Skill> skills){
        this.name = name;
        this.skills = skills;
    }

    public void addAllOtherSkills(){
        Set<String> skillsName = new HashSet<>();
        for (Skill skill : this.skills) {
            skillsName.add(skill.name);
        }
        Set<String> mySkillSet = new HashSet<>(Main.skillSet);
        mySkillSet.removeAll(skillsName);
        for (String newSkill : mySkillSet) {
            this.skills.add(new Skill(newSkill, 0));
        }
    }

}
