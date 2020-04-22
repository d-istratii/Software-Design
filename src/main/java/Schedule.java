import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Schedule {
    Matches[] matches;
    Integer currentMatchDay;

    public void showSchedule(Integer id) throws IOException {
        HTTPCommunication httpCommunication = new HTTPCommunication();
        Matchday matchday = new Matchday();
        currentMatchDay = matchday.getMatchDay(id);
        StringBuffer responseContent = httpCommunication.scheduleRequest(id, currentMatchDay);

        Gson gson = new Gson();
        Schedule schedule = gson.fromJson(responseContent.toString(), Schedule.class);

        // Print all the matches that are scheduled
        System.out.println("Matchday: " + currentMatchDay);
        for (int i = 0; i < schedule.matches.length; i++) {
            System.out.println(schedule.matches[i].homeTeam.getName() + " VS " + schedule.matches[i].awayTeam.getName());
        }

        System.out.println("Continue?\nIf so please press enter.");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
    }
}