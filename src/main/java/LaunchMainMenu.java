import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class LaunchMainMenu {

    Integer selectedDefenders = 0;
    Integer selectedMidfielders = 0;
    Integer selectedAttackers = 0;

    public LaunchMainMenu() throws IOException {
        // If the first option "Create team" has been selected do the following
        while (true) {
            // First of all choose the option to either create the team or to get information
            String option = chooseOption();
            if (option.equals("Create team")) {
                // Check if the first team was created, if so, than create the second team
                if (Main.teamNumber == 0) {
                    Main.teamNumber = 1;
                } else Main.teamNumber = 2;
                chooseTeamName();
                chooseFormation();
                chooseCompetition("Goalkeeper");
                for (int i = 0; i < selectedDefenders; i++) chooseCompetition("Defender");
                for (int i = 0; i < selectedMidfielders; i++) chooseCompetition("Midfielder");
                for (int i = 0; i < selectedAttackers; i++) chooseCompetition("Attacker");
                chooseCompetition("Coach");
            }
            else if (option.equals("Get information")) getInformation();
            else if (option.equals("Display teams")) displayTeams();
            else if (option.equals("Display ratings")) displayRatings();
            else if (option.equals("Exit")) System.exit(0);
        }
    }

    public String chooseOption() throws IOException {
        Boolean correctOption = false;
        do{
            System.out.println("Fantasy Football App");
            System.out.println("Choose option:\nCreate team\nGet information\nDisplay teams\nDisplay ratings\nExit");

            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            String s = br.readLine();

            if (s.equals("Create team")) {
                correctOption = true;
                return "Create team";
            }

            else if(s.equals("Get information")) {
                correctOption = true;
                return "Get information";
            }

            else if(s.equals("Display teams")){
                correctOption = true;
                return "Display teams";
            }

            else if(s.equals("Display ratings")){
                correctOption = true;
                return "Display ratings";
            }

            else if(s.equals("Exit")){
                correctOption = true;
                return "Exit";
            }

            System.out.println("Invalid option, please try again.");

        }while(!correctOption);
        return null;
    }

    public void chooseTeamName() throws IOException {
        System.out.println("Choose your team name:");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        // Change the name of the first team if it wasn't created, otherwise change the name of 2nd team.
        if (Main.teamNumber == 1) {
            Main.teamName = br.readLine();
        }
        else {
            Main.teamName2 = br.readLine();
        }
    }

    public void chooseFormation() throws IOException {
        Boolean correctFormat = false;
        Boolean correctFormation = false;

        do{
            // Read user input
            System.out.println("Choose formation: (E.g. 4-4-2)");
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            String n = br.readLine();
            int totalAmountOfPlayers = 0;
            /*
                E.g. formation: 4-2-2-2
                First character will be the number of defenders
                Last character will be the number of attackers
                The sum of all the integers in between first and
                last characters will be the number of midfielders
             */

            // Check if total amount of players is equal to 10
            for(int i = 0; i < n.length(); i++){
                if(Character.getNumericValue(n.charAt(i)) > 0){
                    totalAmountOfPlayers += Character.getNumericValue(n.charAt(i));
                }
            }

            // Check if format is correct for instance 4-4-2 is correct 4/4/2 is incorrect.
            for(int i = 1; i < n.length()-1; i = i + 2){
                if(n.charAt(i) == '-')
                    correctFormat = true;
                else {
                    correctFormat = false;
                    break;
                }
            }

            // if the format is correct, and the total amount of players is equal to 10 than create team
            // otherwise, start the process of introducing the formation all over again
            if(correctFormat) {
                if (totalAmountOfPlayers == 10) {
                    // The number of defenders
                    selectedDefenders = Character.getNumericValue(n.charAt(0));

                    // The number of midfielders
                    for (int i = 2; i < n.length() - 2; i++) {
                        // try statement used to ignore characters that are '-'
                        try {
                            selectedMidfielders += Integer.parseInt(String.valueOf(n.charAt(i)));
                        } catch (Exception e) {
                        }
                    }

                    // The number of attackers
                    selectedAttackers = Character.getNumericValue(n.charAt(n.length()-1));
                    correctFormation = true;
                }
                else System.out.println("Total amount of players is not equal to 10. Please try again.");
            }
            else System.out.println("Incorrect format, please use '-' in between formation. E.g. 4-2-2");

        }while(!correctFormation);
    }

    public void chooseCompetition(String position) throws IOException {
        Boolean correctCompetitionFormat = false;
        do {
            System.out.println("Choose the League from which you want to select your " + position + ".");
            System.out.println("The competitions available are the following ones:");
            // Show all the available competitions which were mapped in teamsMapping HashMap
            for(String key : Main.teamsMapping.keySet())
                System.out.println(key);

            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            String n = br.readLine();
            Integer id = null;

            // If the user input matches one of the competitions from the default teamsMapping HashMap continue
            if (Main.teamsMapping.containsKey(n)) {
                correctCompetitionFormat = true;
                id = Main.teamsMapping.get(n);
                Competition c = new Competition();
                // Send HTTP Request to the API with the id of the competition
                c.createCompetition(id, position);
            }
            else System.out.println("No such competition found. Please try again.");
        }while(!correctCompetitionFormat);
    }

    public void getInformation() throws IOException {
        Boolean correctCompetitionFormat = false;
        Boolean correctInformationFormat = false;
        Integer id = null;

        // This section is used to select the competition from where to get the desired information
        do {
            System.out.println("Choose the League from which you want to get information:");
            System.out.println("The competitions available are the following:");
            // Display all available competitions
            for (String key : Main.teamsMapping.keySet())
                System.out.println(key);

            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            String n = br.readLine();

            // Check whether the competition introduced by the user is a valid competition
            if (Main.teamsMapping.containsKey(n)) {
                id = Main.teamsMapping.get(n);
                correctCompetitionFormat = true;
            }
        }while(!correctCompetitionFormat);

        // In this second section the user has the possibility to choose the desired information
        do{
            System.out.println("Get information about one of the followings:\nStandings\nTop scorers\nSchedule");
            InputStreamReader is2 = new InputStreamReader(System.in);
            BufferedReader br2 = new BufferedReader(is2);
            String s = br2.readLine();

            // Get information about the standings
            if (s.equals("Standings")) {
                correctInformationFormat = true;
                CompetitionStandings competitionStandings = new CompetitionStandings();
                competitionStandings.showStandings(id);
            }

            // Get information about the Top scorers
            else if (s.equals("Top scorers")) {
                correctInformationFormat = true;
                ScorersStandings scorersStandings = new ScorersStandings();
                scorersStandings.scorersRequest(id);
            }

            // Get information about the Schedule
            else if (s.equals("Schedule")) {
                correctInformationFormat = true;
                Schedule schedule = new Schedule();
                schedule.showSchedule(id);
            }

        }while(!correctInformationFormat);
    }

    public void displayTeams() throws IOException {
        if (Main.showSquad.size() > 0) {
            System.out.println("Team name: " + Main.teamName);
            System.out.println(Main.showSquad);
            System.out.println();

            if (Main.showSquad2.size() > 0) {
                System.out.println("Team name: " + Main.teamName2);
                System.out.println(Main.showSquad2);
                System.out.println();
            }
        }
        else {
            System.out.println("No team created");
        }

        System.out.println("Continue?\nIf so please press enter.");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
    }

    public void displayRatings() throws IOException {
        Integer TotalPoints = 0;
        Integer TotalPoints2 = 0;
        if (Main.showSquad.size() > 0) {
            System.out.println("Team name: " + Main.teamName);
            System.out.println(Main.teamRating);

            for (Integer points : Main.teamRating.values()) {
                TotalPoints = TotalPoints + points;
            }
            System.out.println(TotalPoints);

            System.out.println("Total team points: " + TotalPoints);

            if (Main.showSquad2.size() > 0) {
                System.out.println("Team name: " + Main.teamName2);
                System.out.println(Main.teamRating2);

                for (Integer points : Main.teamRating2.values()) {
                    TotalPoints2 = TotalPoints2 + points;
                }
                System.out.println("Total team points: " + TotalPoints2);
                System.out.println();
            }
            if(Main.showSquad.size() > 0 && Main.showSquad2.size() > 0) {
                if (TotalPoints > TotalPoints2) {
                    System.out.println("Team " + Main.teamName + " wins this week's fantasy competition\n");
                }
                else if (TotalPoints2 > TotalPoints) {
                    System.out.println("Team " + Main.teamName2 + " wins this week's fantasy competition\n");
                }
                else {
                    System.out.println("Team " + Main.teamName + " and team " + Main.teamName2 + " have the same amount of points\n");
                }
            }
        }

        else {
            System.out.println("No team created");
        }

        System.out.println("Continue?\nIf so please press enter.");
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
    }
}
