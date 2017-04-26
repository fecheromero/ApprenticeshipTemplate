require_relative '../src/tennis_set'
require_relative 'tennis_game'
class TennisFactory

  def makeA40_40 team1,team2
  score40=Score.scores.select{|score| score.name=='40'}.first
  team1.score=score40
    team2.score=score40
  end

  def makeASetOfGame game,pointOfteam1,pointOfteam2
    teams=game.set.teams.keys
    game.set.teams[teams[0]]=pointOfteam1
    game.set.teams[teams[1]]=pointOfteam2
  end
  def pointOfIndex index
    Score.scores[index]
  end
  def points15
    pointOfIndex 1
  end

  def points30
    pointOfIndex 2
  end

  def points40
    pointOfIndex 3
  end

  def points45
    pointOfIndex 4
  end

end