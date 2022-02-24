import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    static Set<String> skillSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        int finalAward = 0;
        ArrayList<Contributor> contributors = new ArrayList<>();
        ArrayList<Project> projects = new ArrayList<>();
        Scanner scanner = new Scanner(new File("/Users/damiano/Documents/GitHub/workspace/IntelliJIDEAProjects/GHS2022/QualificationRound/a_an_example.in.txt"));
        String firstLine = scanner.nextLine();
        String[] CandP = firstLine.split(" ");
        int C = Integer.parseInt(CandP[0]);
        int P = Integer.parseInt(CandP[1]);
        // for scorrimento contributors
        for (int i = 0; i < C; i++){
            String contributorString = scanner.nextLine();
            String[] contributorStringSplitted = contributorString.split(" ");
            String name = contributorStringSplitted[0];
            ArrayList<Skill> skills = new ArrayList<>();
            int N = Integer.parseInt(contributorStringSplitted[1]);
            for (int j = 0; j < N; j++){
                String skillString = scanner.nextLine();
                String[] skillStringSplitted = skillString.split(" ");
                Skill skill = new Skill(skillStringSplitted[0], Integer.parseInt(skillStringSplitted[1]));
                skills.add(skill);
                skillSet.add(skill.name);
            }
            contributors.add(new Contributor(name, skills));
        }
        // for scorrimento projects
        for (int i = 0; i < P; i++) {
            String projectString = scanner.nextLine();
            String[] projectStringSplitted = projectString.split(" ");
            String projectName = projectStringSplitted[0];
            int D = Integer.parseInt(projectStringSplitted[1]);
            int S = Integer.parseInt(projectStringSplitted[2]);
            int B = Integer.parseInt(projectStringSplitted[3]);
            int R = Integer.parseInt(projectStringSplitted[4]);
            ArrayList<Skill> skills = new ArrayList<>();
            for (int j = 0; j < R; j++) {
                String skillString = scanner.nextLine();
                String[] skillStringSplitted = skillString.split(" ");
                String skillName = skillStringSplitted[0];
                int skillLevel = Integer.parseInt(skillStringSplitted[1]);
                Skill skill = new Skill(skillName, skillLevel);
                skills.add(skill);
            }
            projects.add(new Project(projectName, D, S, B, skills));
        }
        Collections.sort(projects, new Comparator<Project>() {
            @Override
            public int compare(Project proj1, Project proj2) {
                return Integer.compare(proj1.bestBefore - proj1.days, proj2.bestBefore - proj2.days);
            }
        });

        for (Project project : projects) {
            int currentIndex = projects.indexOf(project);
            if (currentIndex < P-1){
                Project nextProject = projects.get(currentIndex + 1);
                swapElements(projects, project, nextProject);
            }
        }

        for (Project project : projects){
            for (Skill pskill : project.skills){
                for (Contributor contributor : contributors){
                    for (Skill cskill : contributor.skills){
                        System.out.println(cskill.name + " " + pskill.name);
                        System.out.println(cskill.level + " " + pskill.level);
                        if(pskill.name == cskill.name && pskill.level <= cskill.level){
                            project.contributors.add(contributor);
                        }
                    }
                }
            }
        }

        File output = new File("/Users/damiano/Documents/GitHub/workspace/IntelliJIDEAProjects/GHS2022/QualificationRound/output.txt");
        if (output.createNewFile()) {
            FileWriter fileWriter = new FileWriter(output);
            fileWriter.write(P + "");
            fileWriter.write("\n");
            for (Project project : projects) {
                fileWriter.write(project.name + "\n");
                for (Contributor contributor : project.contributors) {
                    fileWriter.write(contributor.name + " ");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        }
    }

    public static boolean swapElements(ArrayList<Project> projects, Project project1, Project project2){
        if ((project1.bestBefore - project1.days) == (project2.bestBefore - project2.days)){
            if(project1.award < project2.award){
                Collections.swap(projects, projects.indexOf(project1), projects.indexOf(project2));
                if (projects.indexOf(project2) > 0) {
                    return swapElements(projects, projects.get(projects.indexOf(project2) - 1), project2);
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
