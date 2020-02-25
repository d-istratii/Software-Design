public class FinalScore {
    Integer homeTeam;
    Integer awayTeam;

    public Integer getScore(String side){
        if(side.equals("home")) return this.awayTeam;
        else if(side.equals("away")) return this.homeTeam;
        else return 1;
    }
}
