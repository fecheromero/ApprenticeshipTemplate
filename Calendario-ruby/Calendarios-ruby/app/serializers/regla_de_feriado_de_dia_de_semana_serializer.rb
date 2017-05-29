class ReglaDeFeriadoDeDiaDeSemanaSerializer < ActiveModel::Serializer
  attributes :diaDeSemanaFeriado



  def diaDeSemanaFeriado
   ConvertidorDeDias.stringDeDiaDeSemana(object.dia_de_semana)
  end
end
