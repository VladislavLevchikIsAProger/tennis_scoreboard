# Tennis Scoreboard

![overview](<Frame Matches1.png>)

## Overview

A web application that implements a scoreboard for a tennis match. The idea is taken from [here](https://zhukovsd.github.io/java-backend-learning-course/Projects/TennisScoreboard/)

## Technologies / tools used:
![Static Badge](https://img.shields.io/badge/Java-asd?style=for-the-badge&labelColor=black&color=black)
![Static Badge](https://img.shields.io/badge/jakarta%20ee-asd?style=for-the-badge&labelColor=black&color=black)
![Static Badge](https://img.shields.io/badge/hibernate-asd?style=for-the-badge&logo=Hibernate&labelColor=black&color=black)
![Static Badge](https://img.shields.io/badge/HTML-asd?style=for-the-badge&logo=html5&labelColor=black&color=black)
![Static Badge](https://img.shields.io/badge/css-asd?style=for-the-badge&logo=css3&labelColor=black&color=black)
![Static Badge](https://img.shields.io/badge/Maven-asd?style=for-the-badge&logo=apachemaven&labelColor=black&color=black)
![Static Badge](https://img.shields.io/badge/junit5-asd?style=for-the-badge&logo=junit5&labelColor=black&color=black)
![Static Badge](https://img.shields.io/badge/H2-asd?style=for-the-badge&labelColor=black&color=black)

 - Hibernate
 - Jakarta EE
 - H2 database
 - Maven
 - HQL
 - Criteria API
 - JUnit5
 - ModelMapper
 - HTML
 - CSS
 - JSP/JSTL

## Application interface

### Home page

![Home-page](image-1.png)

 - Links leading to the new match and the list of completed matches pages

### New match page

![New-match-page](image-2.png)

URL - `/new-match`

Interface:

 - HTML form with fields “Player name 1”, “Player name 2” and a “start” button. For simplicity, let's assume that player names are unique. A player cannot play with himself.
 - Clicking the “start” button will result in a POST request to `/new-match`

POST request handler:

 - Checks for the existence of players in the Players table. If a player with this name does not exist, we create one.
 - Create an instance of the Match class (containing the players' ids and current score) and put it into the collection of current matches (existing only in the application memory or in key-value storage). The key of the collection is the UUID, the value is the Match class instance
 - Redirect to the page `/match-score?uuid=$match_id`


### Match score page

![Match-score-page](image-3.png)

URL - `/match-score?uuid=$match_id`. GET parameter uuid contains the UUID of the match.

Interface:

 - Table with player names, current score
 - Forms and buttons for actions - “player 1 won the current point”, “player 2 won the current point”
 - Clicking the buttons leads to a POST request to `/match-score?uuid=$match_id`, the fields of the submitted form contain the id of the player who won the point.

POST request handler:

 - Retrieves an instance of the Match class from the collection
 - According to which player won the point, updates the match score
 - If the match is not over - renders the match score table with the buttons described above
 - If the match is over:
   - Delete the match from the collection of current matches
   - Write the finished match to SQL database
   - Rendering the final score

### Match result page

![Match-resulte-page](image-4.png)

 - The match result page is returned when the match ends.
 - The address is the same as the match itself.
 - Contains a link to all matches


### Played matches page 

![Played-matches-page](image-5.png)

URL - `/matches?page=$page_number&filter_by_player_name=$player_name`. GET parameters:
 - `page` - page number. If the parameter is not specified, the first page is implied
 - `filter_by_player_name` - name of the player whose matches we are looking for. If the parameter is not set, all matches are displayed

Interface:

 - Form with filter by player name. An input field for the name and a “search” button. A GET request of the form /matches?filter_by_player_name=${NAME} is generated when clicked.
 - List of matches found
 - Page switch, if more matches are found than can fit on one page.

## Database diagram

![database](<QuickDBD-Free Diagram (1).png>)

## Requirements
  + Java 17+
  + Apache Maven
  + Tomcat 10
  + Intellij IDEA

## Project launch

1. Clone the repository:
   ```
   git clone https://github.com/VladislavLevchikIsAProger/tennis_scoreboard.git
   ```

2. Open Intellij IDEA and in Main Menu -> Open select the folder you have decloned.
   
3. In Intellij IDEA, select Run -> Edit Configuration.
  
4. In the pop-up window, click "+" and add Tomcat :
   
    ![Add tomcat](image-7.png)

5. Then click "Fix" : 

    ![Fix botton](https://github.com/VladislavLevchikIsAProger/currency_exchange/assets/153897612/516b7afb-42ef-4374-b96e-2a49d3f866c9)

6. In the window that pops up, select :

   ![War exploded](image-6.png)


7. In the Application context leave the following :
   
   ![Application context](https://github.com/VladislavLevchikIsAProger/currency_exchange/assets/153897612/895091c7-dd29-49b9-8edc-c9b5f29cf018)

8. Click Apply and start Tomcat

## Communication
My Telegram - https://t.me/IamNotARapperr