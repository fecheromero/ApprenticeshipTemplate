class ReglasDeFeriadoDeserializer
  def self.deserializers
    [DeserializerDeReglaDeDiaDeSemana,DeserializerDeReglaConIntervalo,DeserializerDeReglaDeDiaDeMes,DeserializerDeReglaDeFecha]
  end

  def self.deserialize actionControler
    deserializers.select{|deserializer| deserializer.puedo_deserializar? actionControler}.first.deserializar actionControler
  end
end