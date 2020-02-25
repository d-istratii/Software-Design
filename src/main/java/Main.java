import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;

public class Main {
    public static HashMap<String, Integer> teamPlayers = new HashMap<String, Integer>();
    public static HashMap<String, Integer> teamRating = new HashMap<String, Integer>();
    public static String teamName;
    public static HashMap<String, Integer> teamsMapping = new HashMap<String, Integer>();
    private static HttpURLConnection connection;

    public static void main(String[] args) throws IOException {
        teamsMapping.put("Ligue 1", 2015);
        teamsMapping.put("Premier League", 2021);
        teamsMapping.put("Bundesliga", 2002);
        teamsMapping.put("La Liga", 2014);
        teamsMapping.put("Serie A", 2019);
        teamsMapping.put("Primeira Liga", 2017);

        FantasySoccer fantasySoccer = new FantasySoccer();
        PlayerPerformance pp = new PlayerPerformance();
        pp.teamPerformance(teamPlayers);
        System.out.println(teamRating);
    }
}