import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class FantasySoccer{

    Integer defenders;
    Integer midfielders;
    Integer attackers;

    public FantasySoccer() throws IOException {
        String option = chooseOption();
        if(option.equals("Create team")) {
            chooseTeamName();
            chooseFormation();
            chooseCompetition("Goalkeeper");
            for (int i = 0; i < 0; i++) {
                chooseCompetition("Defender");
            }
            for (int i = 0; i < 0; i++) {
                chooseCompetition("Midfielder");
            }
            for (int i = 0; i < 0; i++) {
                chooseCompetition("Attacker");
            }
            //chooseCompetition("Coach");
        }
        else if(option.equals("Get information")) {
            getInformation();
        }
    }

    public String chooseOption() throws IOException {
        System.out.println("Choose option:\nCreate team\nGet information");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();

        if (s.equals("Create team")) return "Create team";
        else if(s.equals("Get information")) return "Get information";
        else return "Not an option";
    }

    public void chooseTeamName() throws IOException {
        System.out.println("Choose your team name:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        Main.teamName = br.readLine();
    }

    public void chooseFormation() throws IOException {
        Boolean bool = true;
        do {
            System.out.println("Choose formation:");
            System.out.println("4-4-2\n4-4-1-1\n4-3-3\n4-5-1\n4-2-3-1\n3-4-3\n3-5-2\n3-5-1-1\n");
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            String n = br.readLine();
            switch (n) {
                case "4-4-2":
                    defenders = 4;
                    midfielders = 4;
                    attackers = 2;
                    bool = false;
                    break;

                case "4-4-1-1":
                    defenders = 4;
                    midfielders = 5;
                    attackers = 1;
                    bool = false;
                    break;

                case "4-3-3":
                    defenders = 4;
                    midfielders = 3;
                    attackers = 3;
                    bool = false;
                    break;

                case "4-5-1":
                    defenders = 4;
                    midfielders = 5;
                    attackers = 1;
                    bool = false;
                    break;

                case "4-2-3-1":
                    defenders = 4;
                    midfielders = 5;
                    attackers = 1;
                    bool = false;
                    break;

                case "3-4-3":
                    defenders = 3;
                    midfielders = 4;
                    attackers = 3;
                    bool = false;
                    break;

                case "3-5-2":
                    defenders = 3;
                    midfielders = 5;
                    attackers = 2;
                    bool = false;
                    break;

                case "3-5-1-1":
                    defenders = 3;
                    midfielders = 6;
                    attackers = 1;
                    bool = false;
                    break;

                default:
                    System.out.println("Formation unknown.");
                    bool = false;
                    break;
            }
        } while (bool);
    }

    public void chooseCompetition(String position) throws IOException {
        System.out.println("Choose the League from which you want to select the player:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String n = br.readLine();
        Integer id = Main.teamsMapping.get(n);
        Competition c = new Competition();
        c.competitionHttpRequest(id, position);
    }

    public void getInformation() throws IOException {
        System.out.println("Choose the League from which you want to get information:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String n = br.readLine();
        Integer id = Main.teamsMapping.get(n);

        System.out.println("Get information about one of the followings:\nStandings\nScorers\nSchedule");
        InputStreamReader is2 = new InputStreamReader(System.in);
        BufferedReader br2 = new BufferedReader(is2);
        String s = br2.readLine();

        if (s.equals("Standings")) {
            CompetitionStandings css = new CompetitionStandings();
            css.standingsRequest(id);
        }

        else if(s.equals("Scorers")) {
            ScorersStandings ss = new ScorersStandings();
            ss.scorersRequest(id);
        }
        else if(s.equals("Schedule")){
            Schedule sh = new Schedule();
            sh.scheduleRequest(id);
        }

    }
}