# list top-scoring player on each team
# display: name of each team, the first and last name of the team's top scorer, 
# and the number of goals scored by that player.
# sort desc order by num goals
# decision: if players on same team have equal goals, display both players

SELECT TeamName, FirstName, LastName, NumGoals from Teams t 
JOIN (
	SELECT p.FirstName, p.LastName, p.NumGoals, p.TeamID FROM Players p
	INNER JOIN (
		SELECT TeamID, max(NumGoals) as MaxGoals FROM Players
		group by TeamID
	) as mostGoalsTbl
	ON p.TeamID = mostGoalsTbl.TeamID and p.NumGoals=mostGoalsTbl.MaxGoals
) as topPlayers
ON topPlayers.TeamID=t.TeamID
ORDER BY NumGoals DESC
