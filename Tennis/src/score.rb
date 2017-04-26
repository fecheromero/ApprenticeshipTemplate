
class Score
  attr_accessor :value
  attr_accessor :name
  @@scores=[]
  def self.scores
    @@scores
  end
  def initialize (value, name=value.to_s)
    self.value=value
    self.name=name
    Score.scores.push(self)
  end
  def win?
    name=='point win'
  end

  def isAdvantage?
    name=='advantage'
  end
  def nextScore

    return Score.scores[Score.scores.index(self)+1]
  end
  def winAgainst otherScore

    return self.nextScore
  end

  def loseAgainst otherScore
    if(otherScore.value==40)
      return Score.scores.first
    else
      return self
    end

  end
  def previusScore
    return Score.scores[Score.scores.index(self)-1]

  end
end

Score.new(0)
Score.new(15)
Score.new(30)
Score.new(40).instance_eval do
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
Score.new(40,'advantage').instance_eval do
  def loseAgainst otherScore
    return self.previusScore
  end
end

Score.new(0,'point win').instance_eval do
  def winAgainst otherScore
    return self
  end
end
