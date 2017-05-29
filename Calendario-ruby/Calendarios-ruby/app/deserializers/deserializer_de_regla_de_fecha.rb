class DeserializerDeReglaDeFecha
  def self.deserializar actionController

    hash=actionController.permit(:fecha).to_h.symbolize_keys
    return ReglaDeFeriadoFecha.new(hash)
  end

  def self.puedo_deserializar? actionController
    actionController.has_key? :fecha
  end
end