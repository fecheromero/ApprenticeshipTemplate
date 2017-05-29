class ReglaDeFeriadoConIntervaloSerializer < ActiveModel::Serializer
  attributes :inicioIntervalo,:finIntervalo, :reglaDeFeriado

  def inicioIntervalo
    object.inicio
  end
  def finIntervalo
    object.fin
  end
  def reglaDeFeriado
    ActiveModelSerializers::SerializableResource.new(object.regla).as_json
  end
end
