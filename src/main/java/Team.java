import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class Team {
    Player[] squad;
    Integer id;
    String name;

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    private static HttpURLConnection connection;

    public void teamHttpRequest(Integer id, String pos) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            String httpRequest = "http://api.football-data.org/v2/teams/";
            httpRequest += id;
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
        Team t = gson.fromJson(responseContent.toString(), Team.class);
        System.out.println("Choose the player you want to add in your team:");
        for (int i = 0; i < t.squad.length; i++) {
            if (t.squad[i].role.equals("PLAYER")) {
                if (t.squad[i].position.equals(pos)) {
                    System.out.println(t.squad[i].getName());
                }
            }
        }
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            String n = br.readLine();
            for (int i = 0; i < t.squad.length; i++) {
                if (t.squad[i].role.equals("PLAYER")) {
                    if (n.equals(t.squad[i].getName()) && t.squad[i].position.equals(pos)) {
                        Integer playerId = t.squad[i].getId();
                        Player p = new Player();
                        p.playerHttpRequest(playerId);
                        break;
                    }
                }
            }

    }
}
