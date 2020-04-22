import com.google.gson.Gson;

import java.io.IOException;

class Matchday {
    Season currentSeason;

    public Integer getMatchDay(Integer id) throws IOException {
        HTTPCommunication httpCommunication = new HTTPCommunication();
        StringBuffer responseContent = httpCommunication.matchDayRequest(id);
        Gson gson = new Gson();
        Matchday matchday = gson.fromJson(responseContent.toString(), Matchday.class);
        return matchday.currentSeason.getCurrentMatchday();
    }
}