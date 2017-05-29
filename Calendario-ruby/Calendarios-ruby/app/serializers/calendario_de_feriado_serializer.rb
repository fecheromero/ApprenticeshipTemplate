class CalendarioDeFeriadoSerializer < ActiveModel::Serializer
  attributes :id,:nombre, :reglasDeFeriado

  def reglasDeFeriado
    object.reglas_de_feriado.map { |regla|
      ActiveModelSerializers::SerializableResource.new(regla).as_json}
  end
end
