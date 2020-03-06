



# **Assignment 1**



#### **Introduction**

Author(s): E.Guduk, R.Florea

At the beginning of the introduction part, the basic explanation of our project is an online soccer game (FantasySoccer). It is a game in which participants assemble an imaginary team of real-life footballers and score points based on those players' actual performance during the league games. The points are scored mostly every Sunday after all football league matches have been played. Usually, players are selected from one specific division in a particular country, however, our project will include players from the most popular football leagues around the world. We target users that are football fans and want to have a way to test their football knowledge on what players are playing well at the moment. First of all, we are planning to create a basic graphic user interface for the player. By using this interface, the player will be able to log in and start playing the game. After the login process, the user will be able to create one or more football teams which consist of 1 goalkeeper, 4 defenders, 4 midfielders and 2 attackers; in total 11 football players. Normally, we have been asked to create some football players and create a statistics table about football players. In our project, we will fetch the data by using a specific API from a synchronized website. So, the football players' data will be ready to use and up to date. In the statistics table, we will keep points for football players based on their recent goals, assists, clean sheets, saved penalties, win/loss ratio, etc. Hence, whenever the user wants to create a team, s/he will be using the table as a manifestation of the football players.
Furthermore, the user would have the option to view the results of the recently played matches in the most popular football league around the world in order to help him choose what players to choose.
Finally, we will calculate the total points of the user’s team by summing up every football player’s points in the team and create “Team Points” for each team. According to the performances of the players in the user’s team, the points will be updated and whenever the user wants to view the fixture, it will be ready to use. In the end, all of the created teams will be placed in a league, using the Team Points, teams will be compared and ranked in that league. The stakeholders of the system are gonna be the project build team, which are responsible for the graphic user interface and the back-end of the system, the facilitator of the league, which is the [Football API](https://www.football-data.org), the direct users and the academic stuff.

##### **The links to the external URLs from where we get our ideas:**

[**https://en.wikipedia.org/wiki/Fantasy_football_(association)**](https://en.wikipedia.org/wiki/Fantasy_football_(association))

[**https://fantasy.premierleague.com**](https://fantasy.premierleague.com)

[**https://www.youtube.com/watch?v=SV_F-cL8fC0**](https://www.youtube.com/watch?v=SV_F-cL8fC0)



#### Functional features**

Author(s): D.Istratii , R.Florea

| ID   | Short name                   | **Description**                                              |
| ---- | ---------------------------- | :----------------------------------------------------------- |
| F1   | Choose option                | The user has the possibility to choose between creating a team or to get information |
| F2   | Create team                  | The user is able to create a new team                        |
| F3   | Get information              | The user can get information about a specific competition    |
| F4   | Create team name             | The user has to provide a team name for his team             |
| F5   | Choose team formation        | Choose the team formation from a list of possible formations displayed |
| F6   | Choose competition           | Choose the competition from where to select the player from  |
| F7   | Choose team                  | Choose the team from where the player should be selected     |
| F8   | Choose player                | The user gets the list of players from which he can choose based on his previous choices |
| F9   | Choose captain               | After selecting eleven players for his team, the user would have to choose his capitain of the team |
| F10  | Choose manager               | Select team manager, by choosing the league and the team in which he is coaching |
| F11  | View team performance/rating | Get a detailed overview of the user's team prformance showing the total points and the points that each player gained based on the last week real performance |
| F12  | View competition standings   | Display the current standings of the chosen competition      |
| F13  | View top scorers             | Display the current top scorers of the chosen competition    |
| F14  | View schedule                | Display the next week schedule of the chosen competition     |



#### **Quality requirements**

Author(s): A.Anton, R.Florea

| **ID** | **Short name**          | **Quality attribute** | **Description**                                              |
| ------ | ----------------------- | --------------------- | ------------------------------------------------------------ |
| QR1    | Instantaneous results   | Responsiveness        | When the scores of all the football players are provided by the user/online database, the results of the total points should be available instantaneous. |
| QR2    | Extensible Competitions | Maintainability       | The system can be easily expendable by adding seasonal competitions such as the Champions League or World Cup |
| QR3    | Continuous service      | Availability          | The system should be available as long as the user has an internet connection and an active [Football API](https://www.football-data.org) subscription |
| QR4    | Error handling          | Usability             | The system should have error handling in each state of the app in order to let the user access the features of the system |

**Java libraries**

Author(s): D.Istratii

| Name                                                         | Description                                                  |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [**Swing**](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html) | Used for styling the user interface of the system. We chose this as it comes with a lot of components looking pretty close to the material design guidelines and all the required animations: sliding menus, flying in and out popups, color pickers and much much more. |
| [**gson**](https://github.com/google/gson)                   | We will use it for reading and writing JSON configuration files containing the description of the football players such as the name, goals scored, etc. We chose this as it is easy to convert from a JSON object to a Java object. |
| [**java.net**](https://docs.oracle.com/javase/8/docs/api/java/net/package-summary.html) | It provides classes for implementing networking applications. We chose it as it is reliable and easy to use for fetching data from HTTP requests. |
| [**java.io**](https://docs.oracle.com/javase/7/docs/api/java/io/package-summary.html) | It is used to process the input and produce the output. The java.io uses the concept of a stream to make an I/O operation fast. We chose this as it contains all the classes required for input and output operations. |

