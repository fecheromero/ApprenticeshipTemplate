class ReglasDeFeriadoDeserializer

  def self.hashDeserialize hashRecibido
    hash=hashRecibido.symbolize_keys
    camposDelHash=hash.keys
    if camposDelHash.include? :fecha
      return ReglaDeFeriadoFecha.new(hash)
    end
    if camposDelHash.include? :regla
      unaRegla=hashDeserialize hash[:regla]
      unaReglaDeFeriadoConPeriodo=ReglaDeFeriadoConPeriodo.new(hash.except(:regla))
      unaReglaDeFeriadoConPeriodo.regla=unaRegla
      return unaReglaDeFeriadoConPeriodo
    end
    if camposDelHash.include? :mes
      return ReglaDeFeriadoDeDiaDeMes.new(hash)
    end
    if camposDelHash.include? :dia_de_semana
      return ReglaDeFeriadoDeDiaDeSemana.new(hash)
    end
    raise 'No se pudo deserializar'
  end
end