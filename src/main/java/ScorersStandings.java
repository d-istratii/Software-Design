import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ScorersStandings {
    TopScorers[] scorers;

    public void scorersRequest(Integer id) throws IOException {
        HTTPCommunication httpCommunication = new HTTPCommunication();
        StringBuffer responseContent = httpCommunication.scorersRequest(id);

        Gson gson = new Gson();
        ScorersStandings ss = gson.fromJson(responseContent.toString(), ScorersStandings.class);

        // PRINT THE TOP SCORERS
        for (int i = 0; i < ss.scorers.length; i++) {
            System.out.println("Name: " + ss.scorers[i].player.getName() + " " + ss.scorers[i].getGoals() + " goals - " +ss.scorers[i].team.getName());
        }

        System.out.println("Continue?\nIf so please press enter.");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
    }
}