class ReglaDeFeriadoDeDiaDeSemanaSerializer < ActiveModel::Serializer
  attributes :diaDeSemanaFeriado

  def stringDeDiaDeSemana numDia
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

  def diaDeSemanaFeriado
   stringDeDiaDeSemana(object.dia_de_semana)
  end
end
