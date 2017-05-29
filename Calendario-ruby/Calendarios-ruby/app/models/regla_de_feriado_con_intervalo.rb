class ReglaDeFeriadoConIntervalo < ApplicationRecord

  belongs_to :regla, polymorphic:true
  belongs_to :calendario_de_feriado, optional: true
  def intervalo
    return Range.new(self.inicio,self.fin)
  end
  def intervalo=unIntervalo
    self.inicio=unIntervalo.first
    self.fin=unIntervalo.last
  end
  def es_feriado?(una_fecha)
    (self.regla.es_feriado? una_fecha) && (self.intervalo.include? una_fecha)
  end

end
