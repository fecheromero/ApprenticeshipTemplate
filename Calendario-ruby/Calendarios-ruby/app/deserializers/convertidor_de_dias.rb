class ConvertidorDeDias

  def self.numeroDeDia dia
    case dia
      when 'MONDAY'
        1
      when 'TUESDAY'
        2
      when 'WEDNESDAY'
        3
      when 'THURSDAY'
        4
      when 'FRIDAY'
        5
      when 'SATURDAY'
        6
      when 'SUNDAY'
        7
    end
  end
  def self.stringDeDiaDeSemana numDia
    case numDia
      when 1
        'MONDAY'
      when 2
        'TUESDAY'
      when 3
        'WEDNESDAY'
      when 4
        'THURSDAY'
      when 5
        'FRIDAY'
      when 6
        'SATURDAY'
      when 7
        'SUNDAY'
    end
  end
  end