import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class Competition {
    Team[] teams;
    String name;
    private static HttpURLConnection connection;

    public String getName() {
        return this.name;
    }

    public void competitionHttpRequest(Integer id, String position) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            String httpRequest = "http://api.football-data.org/v2/competitions/";
            httpRequest += id + "/teams";
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
        Competition c = gson.fromJson(responseContent.toString(),Competition.class);
        System.out.println("Choose the team from which you want to select the player:");
        for(int i = 0; i < c.teams.length; i++){
            System.out.println(c.teams[i].getName());
        }
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String n = br.readLine();
        for(int i = 0; i < c.teams.length; i++){
            if(n.equals(c.teams[i].getName())){
                Integer teamId = c.teams[i].getId();
                Team t = new Team();
                t.teamHttpRequest(teamId, position);
                break;
            }
        }
    }
}