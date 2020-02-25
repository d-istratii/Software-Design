import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class Schedule {
    Matches[] matches;
    Integer cuurentmatchday;
    private static HttpURLConnection connection;

    public void scheduleRequest(Integer id) throws IOException {
        Matchday matchday = new Matchday();
        cuurentmatchday = matchday.matchdayRequest(id);

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            String httpRequest = "http://api.football-data.org/v2/competitions/";
            httpRequest += id + "/matches?matchday=" + cuurentmatchday;
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
        Schedule sh = gson.fromJson(responseContent.toString(), Schedule.class);
        System.out.println("Matchday: " + cuurentmatchday);
        for (int i = 0; i < sh.matches.length; i++) {
            System.out.println(sh.matches[i].homeTeam.getName() + " VS " + sh.matches[i].awayTeam.getName());
        }
    }
}