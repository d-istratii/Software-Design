import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class CompetitionStandings {
    Standing[] standings;

    public void showStandings(Integer id) throws IOException {
        HTTPCommunication httpCommunication = new HTTPCommunication();
        StringBuffer responseContent = httpCommunication.standingsRequest(id);
        Gson gson = new Gson();
        CompetitionStandings competitionStandings = gson.fromJson(responseContent.toString(), CompetitionStandings.class);

        // PRINT THE TOTAL COMPETITION STANDINGS
        for (int i = 0; i < competitionStandings.standings.length; i++) {
            if (competitionStandings.standings[i].getType().equals("TOTAL")) {
                for (int x = 0; x < competitionStandings.standings[i].table.length; x++) {
                    System.out.println(competitionStandings.standings[i].table[x].getPos() + "." + competitionStandings.standings[i].table[x].team.getName() + " " + competitionStandings.standings[i].table[x].getPoints() + " pts");
                }
            }
        }

        System.out.println("Continue?\nIf so please press enter.");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
    }
}
