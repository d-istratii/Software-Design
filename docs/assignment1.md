Assignment 1
Introduction
Author(s): E.Guduk, R.Florea

At the beginning of the introduction part, the basic explanation of our project is an online soccer game (FantasySoccer). It is a game in which participants assemble an imaginary team of real-life footballers and score points based on those players' actual performance during the league games. The points are scored mostly every Sunday after all football league matches have been played. Usually, players are selected from one specific division in a particular country, however, our project will include players from the most popular football leagues around the world. We target users that are football fans and want to have a way to test their football knowledge on what players are playing well at the moment.

​ First of all, we are planning to create a basic graphic user interface for the player. By using this interface, the player will be able to log in and start playing the game. After the login process, the user will be able to create one or more football teams which consist of 1 goalkeeper, 4 defenders, 4 midfielders and 2 attackers; in total 11 football players. Normally, we have been asked to create some football players and create a statistics table about football players. In our project, we will fetch the data by using a specific API from a synchronized website. So, the football players' data will be ready to use and up to date. In the statistics table, we will keep points for football players based on their recent goals, assists, clean sheets, saved penalties, win/loss ratio, etc. Hence, whenever the user wants to create a team, s/he will be using the table as a manifestation of the football players.

​ Furthermore, the user would have the option to view the results of the recently played matches in the most popular football league around the world in order to help him choose what players to choose. Finally, we will calculate the total points of the user’s team by summing up every football player’s points in the team and create “Team Points” for each team. According to the performances of the players in the user’s team, the points will be updated and whenever the user wants to view the fixture, it will be ready to use. In the end, all of the created teams will be placed in a league, using the Team Points, teams will be compared and ranked in that league.

The links to the external URLs from where we get our ideas:
https://en.wikipedia.org/wiki/Fantasy_football_(association)

https://fantasy.premierleague.com

https://www.youtube.com/watch?v=SV_F-cL8fC0

Functional features
Author(s): D.Istratii , R.Florea

ID	Short name	Description
F1	Team name	The user can create a new team by providing the team name
F2	Commands	The available commands are: - add player: add the wanted player in your team -view information: get information about the specific player
F3	Ranking	See the ranking between the created teams by the users in the app, based on the total points earned
F4	View results	The user can view the results of the previous matches from the wanted league
Quality requirements
Author(s): A.Anton, R.Florea

ID	Short name	Quality attribute	Description
QR1	Instantaneous results	Responsiveness	When the scores of all the football players are provided by the user/online database, the results of the total points should be available instantaneous.
QR2	Extensible Competitions	Maintainability	The system can be easily expendable by adding seasonal competitions such as the Champions League or World Cup
QR3	Continuous service	Availability	The system should be available whenever the user accesses it
QR4	Efficient system	Usability	The users should be able to use the system quickly without getting errors
Java libraries
Author(s): D.Istratii

Name	Description
JFoenix	Used for styling the user interface of the system. We chose this as it comes with a lot of components looking pretty close to the material design guidelines and all the required animations: sliding menus, flying in and out popups, color pickers and much much more.
fastjson	We will use it for reading and writing JSON configuration files containing the description of the football players such as the name, goals scored, etc. We chose this as it is easy to convert from a JSON object to a Java object.
java.net	It provides classes for implementing networking applications. We chose it as it is reliable and easy to use for fetching data from HTTP requests.
java.io	It is used to process the input and produce the output. The java.io uses the concept of a stream to make an I/O operation fast. We chose this as it contains all the classes required for input and output operations.
