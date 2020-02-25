import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

class PlayerPerformance {
    Integer rating = 0;
    Player player;
    String side;
    Match[] matches;

    private static HttpURLConnection connection;

    public void teamPerformance(HashMap<String, Integer> teamPlayers) throws IOException {
        for(String  i : teamPlayers.keySet())
           playerPerformanceHttpRequest(i, teamPlayers.get(i));
    }

    public void playerPerformanceHttpRequest(String name, Integer id) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            String httpRequest = "http://api.football-data.org/v2/players/" + id + "/matches?status=FINISHED&limit=1";
            URL url = new URL(httpRequest);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Auth-Token", "db6e589079004b379cbcc5f1e56b332c");
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        PlayerPerformance pp = gson.fromJson(responseContent.toString(), PlayerPerformance.class);


        for (int i = 0; i < pp.matches.length; i++) {
            for (int j = 0; j < pp.matches[i].goals.length; j++) {
                // Check if scored
                if (pp.matches[i].goals[j].scorer.getName().equals(name))
                    rating += 4;

                    // Check for goal assist
                else if (pp.matches[i].goals[j].assist != null)
                    if (pp.matches[i].goals[j].assist.getName().equals(name))
                        rating += 3;
            }

            for(int j = 0; j < pp.matches[i].bookings.length; j++) {
                // Check for red cards
                if (pp.matches[i].bookings[j].card.equals("RED_CARD") &&
                        pp.matches[i].bookings[j].player.getName().equals(name))
                    rating -= 3;
                // Check for yellow cards
                else if (pp.matches[i].bookings[j].card.equals("YELLOW_CARD") &&
                    pp.matches[i].bookings[j].player.getName().equals(name))
                    rating -= 1;
            }

            // Check if player is part of starting home team
            for(int j = 0; j < pp.matches[i].homeTeam.lineup.length; j++)
                if(pp.matches[i].homeTeam.lineup[j].getName().equals(name)) {
                    rating += 2;
                    side = "home";
                }
//
            // Check if player is part of starting away team
            for(int j = 0; j < pp.matches[i].awayTeam.lineup.length; j++)
                if(pp.matches[i].awayTeam.lineup[j].getName().equals(name)) {
                    rating += 2;
                    side = "away";
                }
//
            // Check for substitutions
            for(int j = 0; j < pp.matches[i].substitutions.length; j++) {
                // Check for red cards
                if (pp.matches[i].substitutions[j].playerOut.getName().equals(name) &&
                    pp.matches[i].substitutions[j].minute < 60)
                    rating -= 2;
                else if (pp.matches[i].substitutions[j].playerOut.getName().equals(name) &&
                        pp.matches[i].substitutions[j].minute >= 60)
                    rating -= 1;
            }
//
            // Check for goals received for defenders and goalkeeper
            if(pp.matches[i].score.fullTime.getScore(side) == 0){
                if(pp.player.getPosition().equals("Goalkeeper"))
                    rating += 5;
                if(pp.player.getPosition().equals("Defender"))
                    rating += 4;
                else if(pp.player.getPosition().equals("Midfielder"))
                    rating += 1;
            }
            if(pp.matches[i].score.fullTime.getScore(side) > 1){
                if(pp.player.getPosition().equals("Goalkeeper") || pp.player.getPosition().equals("Defender"))
                    rating -= 1;
            }

            // Implement concieved goals when on bench
        }

        Main.teamRating.put(name, rating);
    }
}