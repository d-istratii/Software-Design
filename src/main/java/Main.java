import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static Integer teamNumber = 0;
    public static String teamName;
    public static HashMap<String, Integer> teamPlayers = new HashMap<String, Integer>();
    public static HashMap<String, Integer> teamRating = new HashMap<String, Integer>();
    public static HashMap<String, String> showSquad = new HashMap<String, String>();

    public static String teamName2;
    public static HashMap<String, Integer> teamPlayers2 = new HashMap<String, Integer>();
    public static HashMap<String, Integer> teamRating2 = new HashMap<String, Integer>();
    public static HashMap<String, String> showSquad2 = new HashMap<String, String>();

    public static HashMap<String, Integer> teamsMapping = new HashMap<String, Integer>();


    public static void main(String[] args) throws IOException {
        teamsMapping.put("Ligue 1", 2015);
        teamsMapping.put("Premier League", 2021);
        teamsMapping.put("Bundesliga", 2002);
        teamsMapping.put("La Liga", 2014);
        teamsMapping.put("Serie A", 2019);
        teamsMapping.put("Primeira Liga", 2017);

        // Launch application
        LaunchMainMenu launchMainMenu = new LaunchMainMenu();

    }
}