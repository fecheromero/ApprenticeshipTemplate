
require_relative '../src/tennis_score'
class TennisTeam
  attr_accessor :score
  attr_accessor :name
  def initialize name
    self.score=TennisScore.scores.first
    self.name=name

  end

  def winPointAgainst oponnent
    self.score=self.score.winAgainst (oponnent.score)
    oponnent.score=oponnent.score.loseAgainst (self.score)
    return self.score
  end


end