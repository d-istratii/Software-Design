

# Assignment 2



# Implemented feature

| ID   | Short name                 | **Description**                                              |
| ---- | -------------------------- | :----------------------------------------------------------- |
| F1   | Choose option              | The user has the possibility to choose between creating a team or to get information |
| F2   | Create team                | The user is able to create a new team                        |
| F3   | Get information            | The user can get information about a specific competition    |
| F4   | Create team name           | The user has to provide a team name for his team             |
| F5   | Choose team formation      | Choose the team formation from a list of possible formations displayed |
| F6   | Choose competition         | Choose the competition from where to select the player from  |
| F7   | Choose team                | Choose the team from where the player should be selected     |
| F8   | Choose player              | The user gets the list of players from which he can choose based on his previous choices |
| F12  | View competition standings | Display the current standings of the chosen competition      |
| F13  | View top scorers           | Display the current top scorers of the chosen competition    |
| F14  | View schedule              | Display the next week schedule of the chosen competition     |



# Used modeling tool

For the Class diagram, Object diagram and State machine diagram we have used [Modelio](https://www.modelio.org) and for the Sequence diagram we have used [Draw.io](https://www.draw.io).



# Class Diagram

Authors: Daniel, Radu

In software engineering, a class diagram in the Unified Modeling Language (UML) is a type of static structure diagram that describes the structure of a system by showing the system's classes, their attributes, operations (or methods), and the relationships among objects.

![Class Diagram](https://imgur.com/lPHEnGM)



### **Class FantasySoccer**

The class diagram, shown in Fig 1, is centered around the **FantasySoccer class**, which represents the entire application. The only attributes that this main class has are the number of *defenders, midfielders and attackers*, which is the most important factor in creating the desired team, those attributes are public and of type integer. Those attributes are used later in the process of creating the team, by selecting the desired team formation (e.g 4-4-2, 4-3-2-1, etc).

The operations of the main component class of the diagram, **FantasySoccer**, representing the methods/functions that can be performed by an instance of a class or interface.

Firstly, in order to understand the purpose and scope of each operation, we will briefly explain chronologically each operation as they are encountered and implemented. The first operation is *chooseOption()*, which represents the two possible states of the application. The first state is the state when the client/user has to create the desired team and the second state is when the client/user has the possibility to get information about the Standings, the Top Scorers or the Standings of one of the available Competition from the API. 



## Create Team section

This operation is of type String, a string value which will be returned and used when an instance will be created by using the constructor *~FantasySoccer(),* every other operation is of type void, no values needed to be returned, only operations to be done in the instance of the app. The second operation is *chooseTeamName()*, which is self-explanatory. This operation represents the mandatory step that needs to be taken by the user when the option to create a team, was selected, which is to choose a team name for the “Fantasy Team”. The next operation is *chooseFormation()*, which represents the requirement of the user to select one of the possible displayed team formations (e.g 4-4-2, 4-3-2-1, etc), that will be used in order to create the team. Therefore, after the formation has been selected, the attributes corresponding with this formation will be assigned with the values inserted by the user. For instance, if the user chooses the most widely used formation of 4-4-2, the attribute defenders will equal 4, midfielders equal 4, and the number of attackers will be equal to 2, error-handling is also present in our implementation, meaning that whenever the user chooses an unknown formation, the operation of selecting the team formation will be looped until the user choose a valid formation for his team. For the next operation of the class, we have *chooseCompetition(),* which will have a parameter on String value representing the position of the selected player that will be checked in the specific competition, for instance after the user will choose the Competition Serie A, and the Team Juventus, and the position that the user is interested, only players with that position, from that team and that competition will be shown. This entire process is done by using HTTP Requests to the Football API, and saving the HTTP Response in a variable called responseContent**,** which will contain the JSON Object which will be further parsed to Java Objects. The next operation of the **FantasySoccer** class is *getInformation()*, which as mentioned in the beginning of the documentation, offers the user the possibility to see the Standings, the Top Scorers or the Schedule of the desired Competition, an operation which will also work based on HTTP Communication between the User and the Server. Lastly, we have the constructor *~FantasySoccer()* which will basically connect and associate all of the operations of this class. It starts by calling the function *chooseOption(),* then after the desired option was selected by the user, it either creates a new team either shows relevant information to the user.

Secondly, assuming the user chooses the first possible state of the application and wants to create a team, a new class is created and associated based on **FantasySoccer**, the class **Competition**. This class has as attributes the teams, the name, and an HTTP connection. Additionally, it has as operations the getter *getName()*, which will return the value of the variable name of the object. Lastly, this class has as operation the *competitionHttpRequest()*, which will send the HTTP request to the server in order to get relevant information about that competition, the response will show all the available teams from that competition. This response will be processed further, in which the app will send further requests with that team in order to get all the players, and so forth, several requests per minute being made (we would like to mention that in order to use this Application, a paid subscription needs to be active because of the number of requests per minute sent).

Thirdly, the team that was obtained from that HTTP response will be parsed further to a Java Class, therefore the **Class Team** represents exactly that, the entire team that was chosen by the user/player, with the following attributes. Firstly, the attribute *squad* which is of type **Player**, representing the actual collection of players (squad) of the team. Secondly, we had to use in our design and modeling process attributes such as *id* and *name*, in order to identify that player by using the API, because for instance if we won’t get relevant information about Cristiano Ronaldo, we need its full name and the id associated with him in the API such that the user can add it to their team, additionally, we used a HashMap to link/tie together the name and ids of the players added to the user’s team. Additionally a connection attribute of type HttpURLConnection. Lastly, this class has as operations 2 getters in order to return *getName* and *getId* of each player that the user is interested into, and the operation that is responsible for the HTTP request in order to get information about all the players of the team which is the *teamHttpRequest()* operation.

Lastly, the last class in chronological order going backward from App to Competition to Team to the **Class Player**. This class contains attributes *id, name, position and role, and connection.* The id and name attributes were explained previously what they represent, so we will explain what the attribute position and role were used for. The attribute position is used to represent the position of each player from the team, e.g Cristiano Ronaldo is an attacker, therefore the Player with the name Cristiano Ronaldo has the value of the position equal to “attacker”, and the role attribute represents the role of each member of the team, one member could be a “PLAYER” and another member could be a “COACH”. In the end, this class has as operations similar to the previous class, getters for id, name, and position, but also the operation that handles the HTTP request of the player that the user is interested in. This is the documentation for how the “Create team” part of the Application is designed and operates, in the following section we will see how the “Get information” part of the Application is modeled and thought.



### Class PlayerRatings

In this class the rating of each player in the user's team is going to be calculated. It has the following attributes: *rating* of type integer, *player* of type class **Player**, *matches* of type class **Matches** and *connection* of type HttpURLConnection. The *rating* variable would store the final rating of the player, *player* has the information about the player such as the *name* or *position* and in *matches*, we have the information about the last matched when the player participated. Furthermore, the *connection* is used for the API request to get the data. The operation in this class is *playerPerformanceHttpRequest* which gets the *name* as an attribute. This request gets the needed information from the last match in order to calculate the rating. 



## Get information section

### Class ScorerStandings

This class is formed of two attribute which are *scorers* of class **Scorers** and *connection* of type HttpURLConnection. The *scorers* attribute would store the information about the top scorers from the chosen league and *connection* is used for the API request to get the data. The operation in this class is just the *scorersRequest* that gets as an attribute the *id* of the chosen league. This request would display the top ten top scorers from that league in the following format: name + goals scored + team.



### Class Scorers

This class is formed of three attributes, *player* of class type **TopScorer**, *team* of class type **Team** and *numberOfGoals* of type integer. The *player*  variable is used to get the name of the player and the team *variable* to get the team name of that player. The operation in this class is only *getGoals* that returns the number of goals scored by that specific player.



### Class CompetitionStandings

This class is formed of only two attributes which are *standings* of type class **Standing** and *connection* of type HttpURLConnection. The *standings* variable is used to store the information about the standings in an particular league and *connection* is used for the API request to get the data. The only operation is the function *standingsRequest* that gets an attribute *id* of type integer. This function would display the stadnings from the chosen league in the following format: position + team name + points.



### Class Standing

This class is formed of two attributes, *type* of type string  and *table* of type class **Table**. The variable *type* is used to get from the API the right type of the information and the variable *table* is used to store about the information about each team's points and name. The only operation is the function *getType* that gets the type of the API information.



### Class Table

This class is formed of three attributes, *position* of type integer, *team* of type class **Team** and *points* of type integer. There are two operations, the function *getPos* and *getPoints*, that both return the position and the points of a specific team.



### **Class Schedule** 

This class is formed of three attributes, *matches* of type class **Matches**, *currentMatchday* of type integer and *connection* of type HttpURLConnection. The variable *matches* stores the home and away team of a particular match, *currentMatchday* stores the league's matchday  and *connection* is used for the API request to get the data for that particular matchday.

There is only one operation, the *scheduleRequest* function that gets an attribute *id* of type integer. This function is responsible to display the schedule of the next week matches from the selected league. 



### Class Matches

This class is made of two attributes *homeTeam* and *awayTeam* both of type class **Team**. These two variables would store the information about the teams that play against each other such as their name.



### **Class Matchday**

There are two attributes in this class, *currentSeason* of type class **Season** and *connection* of type HttpURLConnection. The variable *currentSeason* is used to get information about this league's season and  *connection* is used for the API request to extract the need information about this season. There is only one operation, the *matchdayRequest* function that gets an attribute *id* of type integer. This function is responsible to find out the current matchday of the wanted league.



### Class Season

There is just one attribute in this class, *currentMatchday* of type integer where the matchday would be stored. In addition, there is one operation, which is the getter,*getCurrentMatchday* .It would give back the exact integer of the matchday.



# Object diagram 

Authors: Daniel, Radu



![Object diagram](/home/msi/Desktop/Object diagram.png)



The object diagram display the status in the system when the user adds players in their team. The user has selected the formation at the beginning of the system 4-4-2. This is converted into *defenders* , *midfielders* and *attackers* as 3 separated integers. The user is choosing two attackers based on the choice of his team formation that has 2 forwards. 

In the diagram we can see that he has choose as his first choice the Serie A league, then he receives a number of teams from that league and he chooses  Juventus. Furthermore, the user get all the forwards from Juventus and he chooses Cristiano Ronaldo as his first attacker. All the information about Ronaldo is stored in the Player class such as his position, role and ID in the API.

Going into the next state, he has on more attacker to choose. He starts again the process by choosing the Premier league, then Manchester United and finally Mason Greenwood as his final choice for his attack. The player are stored in his team and their ratings are gonna be calculated in the following part of the system where other classes and information from the API needs to be involved. 



# State machine diagrams

Author: Kolkma



![WhatsApp Image 2020-03-06 at 15.33.52](/home/msi/Downloads/WhatsApp Image 2020-03-06 at 15.33.52.jpeg)





# Sequence diagrams

Author: Emre Guduk

When the program is executed, there are 2 possible paths to be followed. The first one is “Create team” and the second one is “Get information”. Accordingly, the program flow differs.

### Create team sequence

If the user wants to create a team in the beginning of the execution, basically this diagram will be behind the stream:



![WhatsApp Image 2020-03-05 at 15.07.12](/home/msi/Downloads/WhatsApp Image 2020-03-05 at 15.07.12.jpeg)

First of all, there are 3 objects and 1 actor in this diagram. Those components will be interacting with each other during the execution. “Actor” is the user/player, “Client” is the code combined with the GUI, “Api” is the tool which will be used to communicate with the database and the “Data” is the ready to use data which holds the all current soccer data. In the beginning, the client(actual code combined with GUI) will be asking for a “Team Name” to create a new team. This operation is handled and the user’s response will be held in the client's working directory. When the name of the team is ready, as the next step, the client will be asking for the team. This formation will be used later on to fill up the team with players by using a hash map. Afterwards, the formation response from the actor will also be held in the client's working directory. After this point, the actor will be setting up his\her team with players. As the third step, the client will be asking for a league. Once the client receives a response from the actor, it will match the response with the equivalent id number in its data and then send a request to the API by using this id number. When the API receives this request, it will fetch all of the teams in the requested league from the database. Then, the API will send those teams to the client. Afterwards, the actor will be asked to enter a team name. If the actor responds, the client will receive the response, match it with the equivalent id number and send a request to API by using this team id number. The API will fetch the players in the requested team from the database and bring it back to the client. The formation is important here. We hard coded that first of all, the goalkeeper will be chosen from the requested team. So, no matter what operation is going, the first pick for the players will be the goalkeeper. That means, when the first team request is sent to the API, API will bring only the list of goalkeepers in the requested team. Afterwards, according to the formation, API will bring the relevant players till it reaches its limits. For instance, if the actor chooses 4-4-2 as the formation, first team request will bring the goalkeeper and then 4 defenders and so forth. When 11 players are added to the team, the client will ask for a coach and all of the newly created team data will be held in the client's working directory. Then the program will display the user's team by showing each player and his position  in the order they were chosen. After, the program will terminate itself. We do not have error handling yet. So, if we had error handling, on the client side, the client would be checking for formation, league name, team name and player name. Because, if there is a problem with the input taken from the Actor, the rest of the program will not work as required. For instance, every league and every team has been attached to an id number. If the user’s input for the team name does not match with any of those id numbers, the program will not be able to jump into the next step. So, the whole process will be stuck at this point.



### Get information sequence

If the user wants to get information about the fixture in the beginning of the execution, basically this diagram will be behind the stream:



![WhatsApp Image 2020-03-05 at 15.07.12 (1)](/home/msi/Downloads/WhatsApp Image 2020-03-05 at 15.07.12 (1).jpeg)



First of all, the client will be asking for the name of the league to get information about. If Actor responds, the client will hold the league name and as the next step, it will be asking for what the Actor wants to know exactly. The response of Actor is limited in this case. Actors can choose either “Standings”, “Scorers” or “Schedule”. When the Actor chooses what s\he wants, the client will convert the league name into a specific id number and by using the user’s wish (“Standings”/ “Scorers”/ “Schedule”), the Client will send a request to the API. Once the API receives this request, it will fetch the relevant data in the Database and bring it back to the client. As the last step, the requested information will be shown to the Actor. Based on what the user choice was the information is displayed in different formats. 

We have not done the error-handling part, that is why interactions between objects do not occur frequently. The only case for the Client to interact with API is sending exactly the right format of the data. So, if the users enter a wrong input, the program will throw an error and terminate itself. When we have the error handling, the interaction between objects will more or less double. In that case, we need to check whether the input is valid or the requested information is held in the database and the user is asked again the input what the correct input.



# Implementation

Authors: Daniel, Radu

Moving from the designing/modeling phase to the actual implementation was by following what classes, objects, state machines, and sequence diagrams we initially had in mind, additionally with all the attributes and operations with their specific data type, value, and functionality. However, in some cases, our implementation did not know according to our initial design, and therefore some parts had to be rethought and implemented again. For instance, we had encountered several problems while extracting data from the Internet using the Football API. We would like to mention that the API we used is the following one: https://www.football-data.org/, and our Application will work only with a valid subscription for this Football API, for the moment the user is using our own paid subscription, but in the future if application will be used, a paid subscription will indeed be necessary.

The transition was easy as we could  divide the application in two distinct parts, “Create team” and “Get information”. This help us to spread the task of constructing the code much better as we could work on both task at the same time.

Firstly, two of our members have started with the “Create team” branch of the system, implementing it and debug it in order to have our first working segment of our App.

Secondly, the other two members started working on the other branch, however encountered some problems with linking the API, so they start looking for design ideas for the GUI part of the project. When the first segment was done, we all met and found out the problem with the API and continued with implementing the second segment of the code in order to meet the deadline.

### **Key solutions**

We base our project on getting the data from the Football API, meaning that we first read the format of the data (as JSON) that we got and try to parse and interpret it accordingly. The “Get information” section is all about getting the correct information that is asked by the users, manipulating that data and display it in a nice format in order for the user to be able to read it. We manage to do this by storing in classes and variables each important part of data we are looking for and printing them on the terminal in this phase, but we would like to extend our application’s user experience by providing a basic GUI.

### Main class

The **main** class of the Application can be found in Software-Design/src/main/java/Main.java as a directory and as a [link](https://github.com/daniel-istratii/Software-Design/tree/Assignment-2/src/main/java).  

This class, **main** starts with the declaration on multiple hashmaps that will store the user's team and the possible football leagues from where the user can look for players, in addition to a string variable that store the team name. After that, the leagues are added in the hashmap for future use. Finally, the FantasySoccer class is initialized and its' function is called to start the app.

### Jar file

The Jar file for directly executing the code can be found in Software Design/out/artifacts/software_design_vu_2020_jar/software_design_vu_2020.jar or as a [link](https://github.com/daniel-istratii/Software-Design/tree/Assignment-2/out/artifacts/software_design_vu_2020_jar).

### **Video**

We have created two videos to show the functionalities of our Fantasy Soccer app. The videos are posted on our Youtube and can be accessed via the links.

The first video: https://youtu.be/Gs1-5UTbKQA , we present how the user can create a team and the steps in order to add player to his team.

The second video: https://youtu.be/Z00OpOBmIb8 , we show how the "Get information" section works and what the player can find out about his favorite football league. In our example, we just show the standings in the Serie A league.
