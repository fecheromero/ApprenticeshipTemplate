class CalendarioDeFeriadoSerializer < ActiveModel::Serializer
  attributes :id,:nombre, :reglas

  def reglas
    object.reglas_de_feriado
  end
end
