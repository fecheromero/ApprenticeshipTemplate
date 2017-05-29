class ReglaDeFeriadoDeDiaDeMesSerializer < ActiveModel::Serializer
  attributes :diaDeMes,:mes

  def diaDeMes
    object.dia_de_mes
  end
end
