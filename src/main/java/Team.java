import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Team{
    Player[] squad;
    Integer id;
    String name;


    public String getName(){
        return this.name;
    }

    public Integer getId(){
        return this.id;
    }

    public void createTeam(Integer id, String position) throws IOException {
        Boolean coach = false;
        Boolean correctPlayerFormat = false;
        Boolean teamContainsPlayerAlready = false;
        HTTPCommunication httpCommunication = new HTTPCommunication();
        StringBuffer responseContent = httpCommunication.teamHttpRequest(id);

        Gson gson = new Gson();
        Team t = gson.fromJson(responseContent.toString(),Team.class);

        do {
            if(position.equals("Coach")) {
                coach = true;
                System.out.println("Choose the coach of your team:");
            }
            else {
                System.out.println("Choose the " + position +  " you want to add in your team:");
            }

            // PRINT ALL THE PLAYERS FROM WHICH THE USER CAN CHOOSE

            for (int i = 0; i < t.squad.length; i++) {
                // if the player is not part of the team already continue
                if (!Main.teamPlayers.containsKey(t.squad[i].getName())) {
                    if (t.squad[i].role.equals("PLAYER") && coach == false) {
                        if (t.squad[i].position.equals(position)) {
                            System.out.println(t.squad[i].getName());
                        }
                    }
                    else if (t.squad[i].role.equals("COACH") && coach == true) {
                        System.out.println(t.squad[i].getName());
                    }
                }
            }

            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            String n = br.readLine();

            // If the player is not part of the team continue
            if(!Main.teamPlayers.containsKey(n)) {
                for (int i = 0; i < t.squad.length; i++) {
                    if (t.squad[i].role.equals("PLAYER")) {
                        if (n.equals(t.squad[i].getName()) && t.squad[i].position.equals(position)) {
                            correctPlayerFormat = true;
                            Integer playerId = t.squad[i].getId();
                            Player p = new Player();
                            p.addPlayer(playerId);
                            teamContainsPlayerAlready = true;
                            break;
                        }
                    }
                    else if (t.squad[i].role.equals("COACH")) {
                        if (n.equals(t.squad[i].getName())) {
                            correctPlayerFormat = true;
                            Integer playerId = t.squad[i].getId();
                            Player p = new Player();
                            p.addPlayer(playerId);
                            teamContainsPlayerAlready = true;
                            break;
                        }
                    }
                }
            }
            if(!correctPlayerFormat) System.out.println("No such player found. Please try again.");
        }while(!correctPlayerFormat && !teamContainsPlayerAlready);

    }
}