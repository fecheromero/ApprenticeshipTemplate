require_relative '../src/tennis_team'
require_relative '../src/tennis_set'
class TennisGame
attr_accessor :teams
attr_accessor :set
  def initialize team1,team2
    self.teams=[]
    self.teams.push(team1)
    self.teams.push(team2)
    self.set=TennisSet.new team1,team2
  end
def controlValidTeam team
  if(!teams.include? team)
    raise 'invalid team'
  end
end
  def teamWinPoint team
    controlValidTeam team
    team.winPointAgainst (self.teams.select{|unTeam|  unTeam!=team}.first)
    controlWin team
  end

 def controlWin team
   rdo=team.score.win?
   if(rdo)
     self.teams.each { |unTeam| unTeam.score=LOVE }
     set.gameFor team
   end
   return rdo
 end
end