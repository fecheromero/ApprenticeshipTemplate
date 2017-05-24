class ReglasDeFeriadoDeserializer
  def self.deserialize json
    hash=JSON.parse(json)
    hashDeserialize hash
  end
  def self.hashDeserialize hash

    camposDelHash=hash.keys
    if camposDelHash.any? { |campo| campo=='fecha'}
      return ReglaDeFeriadoFecha.new(hash)
    end
    if(camposDelHash.any? { |campo| campo=='regla'})
      unaRegla=hashDeserialize hash['regla']
      unaReglaDeFeriadoConPeriodo=ReglaDeFeriadoConPeriodo.new(hash.except('regla'))
      unaReglaDeFeriadoConPeriodo.regla=unaRegla
      return unaReglaDeFeriadoConPeriodo
    end
    if(camposDelHash.any? { |campo| campo=='mes'})
      return ReglaDeFeriadoDeDiaDeMes.new(hash)
    end
    if(camposDelHash.any? { |campo| campo=='dia_de_semana'})
      return ReglaDeFeriadoDeDiaDeSemana.new(hash)
    end
    return nil
  end
end