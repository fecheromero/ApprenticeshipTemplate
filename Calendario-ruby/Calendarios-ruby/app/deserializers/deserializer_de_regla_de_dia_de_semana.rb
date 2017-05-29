class DeserializerDeReglaDeDiaDeSemana
  def self.deserializar actionController
    hash=actionController.permit(:diaDeSemanaFeriado).to_h.symbolize_keys
    return ReglaDeFeriadoDeDiaDeSemana.new(dia_de_semana:ConvertidorDeDias.numeroDeDia(hash[:diaDeSemanaFeriado]))
  end

  def self.puedo_deserializar? actionController
    actionController.has_key? :diaDeSemanaFeriado
  end
end