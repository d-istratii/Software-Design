import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class Player{
    int id;
    String name;
    String position;
    String role;
    Boolean coach = false;

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getPosition(){
        return this.position;
    }

    public void addPlayer(Integer id) throws IOException {
        HTTPCommunication httpCommunication = new HTTPCommunication();
        StringBuffer responseContent = httpCommunication.playerHttpRequest(id);

        Gson gson = new Gson();
        Player p = gson.fromJson(responseContent.toString(), Player.class);

        // If the application is still in the process of creating the first team do the following
        if(Main.teamNumber == 1) {
            // Add the player to the team and get the player performance (rating) of the player
            Main.teamPlayers.put(p.getName(), p.getId());
            if(p.getPosition() != null) {
                Main.showSquad.put(p.getName(), p.getPosition());
            }
            else {
                Main.showSquad.put(p.getName(), "Coach");
                coach = true;
            }

        }

        else {
            Main.teamPlayers2.put(p.getName(), p.getId());
            if (p.getPosition() != null) {
                Main.showSquad2.put(p.getName(), p.getPosition());
            }
            else {
                Main.showSquad.put(p.getName(), "Coach");
                coach = true;
            }
                PlayerPerformance pp = new PlayerPerformance();
                pp.teamPerformance(Main.teamPlayers2,coach);
        }
    }
}
