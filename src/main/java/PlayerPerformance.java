import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;

class PlayerPerformance {
    Integer rating = 0;
    Player player;
    String side;
    Match[] matches;
    Boolean bench = true;

    public void teamPerformance(HashMap<String, Integer> teamPlayers, boolean coach) throws IOException {
        for(String  i : teamPlayers.keySet()) {
            playerPerformance(i, teamPlayers.get(i),coach);
        }
    }
    
    public void playerPerformance(String name, Integer id, Boolean coach) throws IOException {
        HTTPCommunication httpCommunication = new HTTPCommunication();
        StringBuffer responseContent = httpCommunication.playerPerformanceHttpRequest(id);
        Gson gson = new Gson();
        PlayerPerformance playerPerformance = gson.fromJson(responseContent.toString(), PlayerPerformance.class);

        // Iterate through all the matches
        for (int i = 0; i < playerPerformance.matches.length; i++) {
            if(!coach) {
            // Check for goals
                for (int j = 0; j < playerPerformance.matches[i].goals.length; j++) {
                    if (playerPerformance.matches[i].goals[j].scorer.getName().equals(name))
                        rating += 4;

                        // Check for goal assist
                    else if (playerPerformance.matches[i].goals[j].assist != null)
                        if (playerPerformance.matches[i].goals[j].assist.getName().equals(name))
                            rating += 3;
                }

                // Check for cards
                for (int j = 0; j < playerPerformance.matches[i].bookings.length; j++) {
                    // Check for red cards
                    if (playerPerformance.matches[i].bookings[j].card.equals("RED_CARD") &&
                            playerPerformance.matches[i].bookings[j].player.getName().equals(name))
                        rating -= 3;
                        // Check for yellow cards
                    else if (playerPerformance.matches[i].bookings[j].card.equals("YELLOW_CARD") &&
                            playerPerformance.matches[i].bookings[j].player.getName().equals(name))
                        rating -= 1;
                }

                // Check if player is part of starting home team
                for (int j = 0; j < playerPerformance.matches[i].homeTeam.lineup.length; j++) {
                    if (playerPerformance.matches[i].homeTeam.lineup[j].getName().equals(name)) {
                        rating += 2;
                        side = "home";
                        bench = false;
                    }
                }

                // Check if player is part of starting away team
                for (int j = 0; j < playerPerformance.matches[i].awayTeam.lineup.length; j++) {
                    if (playerPerformance.matches[i].awayTeam.lineup[j].getName().equals(name)) {
                        rating += 2;
                        side = "away";
                        bench = false;
                    }
                }

                if (!bench) {
                    // Check for substitutions
                    for (int j = 0; j < playerPerformance.matches[i].substitutions.length; j++) {
                        if (playerPerformance.matches[i].substitutions[j].playerOut.getName().equals(name) &&
                                playerPerformance.matches[i].substitutions[j].minute < 60)
                            rating -= 2;
                        else if (playerPerformance.matches[i].substitutions[j].playerOut.getName().equals(name) &&
                                playerPerformance.matches[i].substitutions[j].minute >= 60)
                            rating -= 1;
                    }

                    // Check for goals received for defenders and goalkeeper
                    if (playerPerformance.matches[i].score.fullTime.getScore(side) == 0) {
                        if (playerPerformance.player.getPosition().equals("Goalkeeper"))
                            rating += 5;
                        if (playerPerformance.player.getPosition().equals("Defender"))
                            rating += 4;
                        else if (playerPerformance.player.getPosition().equals("Midfielder"))
                            rating += 1;
                    }

                    if (playerPerformance.matches[i].score.fullTime.getScore(side) > 1) {
                        if (playerPerformance.player.getPosition().equals("Goalkeeper") || playerPerformance.player.getPosition().equals("Defender"))
                            rating -= 1;
                    }
                }
            }

            else {
                // Check if coach is part of starting home team
                if (playerPerformance.matches[i].homeTeam.coach.getName().equals(name)) {
                    if (playerPerformance.matches[i].score.fullTime.getWinner().equals("home")) rating += 3;
                    else if (playerPerformance.matches[i].score.fullTime.getWinner().equals("draw")) rating += 1;
                }

                // Check if coach is part of starting away team
                if (playerPerformance.matches[i].awayTeam.coach.getName().equals(name)) {
                    if (playerPerformance.matches[i].score.fullTime.getWinner().equals("away")) rating += 3;
                    else if (playerPerformance.matches[i].score.fullTime.getWinner().equals("draw")) rating += 1;
                }
            }
        }

        // Store the rating in the correct team
        if (Main.teamNumber == 1) {
            Main.teamRating.put(name, rating);
        } else {
            Main.teamRating2.put(name, rating);
        }
    }
}