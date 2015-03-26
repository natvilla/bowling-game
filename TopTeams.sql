# top scoring teams with goals >= 30
# list team name & total goals
# sorted desc by total goals
SELECT TeamName, TeamGoals FROM Teams 
JOIN 
	(SELECT TeamID, sum(NumGoals) AS TeamGoals FROM Players
	GROUP BY TeamID
	HAVING sum(NumGoals) >= 30) AS Goals
ON Teams.TeamID=Goals.TeamID
ORDER BY TeamGoals DESC
