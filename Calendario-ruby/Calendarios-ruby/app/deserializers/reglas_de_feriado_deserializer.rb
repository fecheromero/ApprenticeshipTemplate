class ReglasDeFeriadoDeserializer

  def self.numeroDeDia dia
    case dia
      when 'MONDAY'
        1
      when 'TUESDAY'
        2
      when 'WEDNESDAY'
        3
      when 'THURSDAY'
        4
      when 'FRIDAY'
        5
      when 'SATURDAY'
        6
      when 'SUNDAY'
        7
    end
  end
  def self.hashDeserialize actionControler

    if actionControler.has_key? :fecha
      hash=actionControler.permit(:fecha).to_h.symbolize_keys
      return ReglaDeFeriadoFecha.new(hash)
    end
    if actionControler.has_key? :reglaDeFeriado
      unaRegla=hashDeserialize(actionControler[:reglaDeFeriado])
      hashIntervalo=actionControler[:intervalo].permit(:inicioIntervalo,:finIntervalo).to_h.symbolize_keys
      unaReglaDeFeriadoConIntervalo=ReglaDeFeriadoConIntervalo.new(inicio:hashIntervalo[:inicioIntervalo],
                                                                 fin:hashIntervalo[:finIntervalo])
      unaReglaDeFeriadoConIntervalo.regla=unaRegla
      return unaReglaDeFeriadoConIntervalo
    end
    if actionControler.has_key? :mes
      hash=actionControler.permit(:mes,:diaDeMes).to_h.symbolize_keys
      return ReglaDeFeriadoDeDiaDeMes.new(dia_de_mes:hash[:diaDeMes],mes:hash[:mes])
    end
    if actionControler.has_key? :diaDeSemanaFeriado
      hash=actionControler.permit(:diaDeSemanaFeriado).to_h.symbolize_keys
      return ReglaDeFeriadoDeDiaDeSemana.new(dia_de_semana:numeroDeDia(hash[:diaDeSemanaFeriado]))
    end
    raise 'No se pudo deserializar'
  end
end