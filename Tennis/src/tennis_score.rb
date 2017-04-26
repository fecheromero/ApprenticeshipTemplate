
class TennisScore
  attr_accessor :value
  attr_accessor :name
  @@scores=[]
  def self.scores
    @@scores
  end
  def initialize (value, name=value.to_s)
    self.value=value
    self.name=name
    TennisScore.scores.push(self)
  end
  def win?
    self==POINT_WIN
  end

  def isAdvantage?
    self==FORTY_ADVANTAGE
  end
  def nextScore

    return TennisScore.scores[TennisScore.scores.index(self)+1]
  end
  def winAgainst otherScore

    return self.nextScore
  end

  def loseAgainst otherScore
    if(otherScore.value==40)
      return LOVE
    else
      return self
    end

  end
  def previusScore
    return TennisScore.scores[TennisScore.scores.index(self)-1]

  end
end

LOVE=TennisScore.new(0)
FIFTEEN=TennisScore.new(15)
THYRTY=TennisScore.new(30)
FORTY=TennisScore.new(40)
FORTY.instance_eval do
  def winAgainst otherScore
    if(otherScore==self)
      return self.nextScore
    end
    if(otherScore.isAdvantage?)
      return self
    end
    return self.nextScore.nextScore
  end
  def loseAgainst otherScore
    return self
  end

end
FORTY_ADVANTAGE=TennisScore.new(40, 'advantage')
FORTY_ADVANTAGE.instance_eval do
  def loseAgainst otherScore
    return self.previusScore
  end
end

POINT_WIN=TennisScore.new(0, 'point win')
POINT_WIN.instance_eval do
  def winAgainst otherScore
    return self
  end
end
