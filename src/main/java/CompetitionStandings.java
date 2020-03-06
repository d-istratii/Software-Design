import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class CompetitionStandings {
    Standing[] standings;

    private static HttpURLConnection connection;

    public void standingsRequest(Integer id) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            String httpRequest = "http://api.football-data.org/v2/competitions/";
            httpRequest += id + "/standings";
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
        CompetitionStandings ccs = gson.fromJson(responseContent.toString(), CompetitionStandings.class);
        for (int i = 0; i < ccs.standings.length; i++) {
            if (ccs.standings[i].getType().equals("TOTAL")) {
                for (int x = 0; x < ccs.standings[i].table.length; x++) {
                    System.out.println(ccs.standings[i].table[x].getPos() + "." + ccs.standings[i].table[x].team.getName() + " " + ccs.standings[i].table[x].getPoints() + " pts");
                }
            }
        }
    }
}
