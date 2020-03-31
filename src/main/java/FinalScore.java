public class FinalScore {
    Integer homeTeam;
    Integer awayTeam;

    public Integer getScore(String side){
        if(side.equals("home")) return this.awayTeam;
        else if(side.equals("away")) return this.homeTeam;
        else return 2;
    }

    public String getWinner(){
        if (homeTeam > awayTeam){
            return "home";
        }
        else if (homeTeam < awayTeam){
            return "away";
        }
        else {
            return "draw";
        }
    }
}
