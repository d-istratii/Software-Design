import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

class Competition {
    Team[] teams;
    String name;


    public String getName() {
        return this.name;
    }

    public void createCompetition(Integer id, String position) throws IOException {
        HTTPCommunication httpCommunication = new HTTPCommunication();
        StringBuffer responseContent = httpCommunication.competitionHttpRequest(id);
        Boolean correctTeamFormat = false;

        Gson gson = new Gson();
        Competition c = gson.fromJson(responseContent.toString(),Competition.class);


        do {
            // Print all the possible teams from which the user can choose the players
            System.out.println("Choose the team from which you want to select the player:");
            for (int i = 0; i < c.teams.length; i++) System.out.println(c.teams[i].getName());

            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            String n = br.readLine();

            // Check whether the user input is a valid team from the possible teams printed previously
            for (int i = 0; i < c.teams.length; i++) {
                if (n.equals(c.teams[i].getName())) {
                    correctTeamFormat = true;
                    Integer teamId = c.teams[i].getId();
                    Team t = new Team();
                    t.createTeam(teamId, position);
                    break;
                }
            }
            if(!correctTeamFormat) System.out.println("No such team found. Please try again.");
        }while(!correctTeamFormat);
    }
}