class TennisSet
  attr_accessor :teams
  def initialize team1, team2
    self.teams = {team1 => 0,team2=> 0}
  end

  def gameFor team
    self.teams[team]=self.teams[team]+1
    controlSetWIn
  end

  def controlSetWIn
   points=self.teams.values
    pointOfWinner=points.select{|point| point>=6 && !points.any? { |otherPoint| otherPoint>(point-2) && otherPoint!=point}}.first
    if(pointOfWinner.nil?)
      return
    end
  else
    teamWinner=self.teams.key(pointOfWinner)
    raise (self.printWinner teamWinner)
  end


  def printStatusTeam aTeam
    aTeam.name+' games:'+self.teams[aTeam].to_s+' '
  end

  def printStatusSet
    message=''
    self.teams.keys.each { |team| message=message + (self.printStatusTeam team)}
    message
  end

  def printWinner winner
    self.printStatusSet+ winner.name+' Win.'
  end

end