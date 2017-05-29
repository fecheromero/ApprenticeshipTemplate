class DeserializerDeReglaConIntervalo
  def self.deserializar actionController
    unaRegla=ReglasDeFeriadoDeserializer.deserialize(actionController[:reglaDeFeriado])
    hashIntervalo=actionController[:intervalo].permit(:inicioIntervalo,:finIntervalo).to_h.symbolize_keys
    unaReglaDeFeriadoConIntervalo=ReglaDeFeriadoConIntervalo.new(inicio:hashIntervalo[:inicioIntervalo],
                                                                 fin:hashIntervalo[:finIntervalo])
    unaReglaDeFeriadoConIntervalo.regla=unaRegla
    return unaReglaDeFeriadoConIntervalo
  end

  def self.puedo_deserializar? actionController

    actionController.has_key? :reglaDeFeriado
  end
end