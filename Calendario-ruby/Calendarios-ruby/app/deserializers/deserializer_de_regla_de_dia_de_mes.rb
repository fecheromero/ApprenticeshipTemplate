class DeserializerDeReglaDeDiaDeMes
  def self.deserializar actionController
    hash=actionController.permit(:mes,:diaDeMes).to_h.symbolize_keys
    return ReglaDeFeriadoDeDiaDeMes.new(dia_de_mes:hash[:diaDeMes],mes:hash[:mes])
  end
  def self.puedo_deserializar? actionController
    actionController.has_key? :mes
  end
end