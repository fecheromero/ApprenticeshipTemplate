class ReglaDeFeriadoConPeriodo < ApplicationRecord

  has_one :wrapper_de_regla_de_feriado, as: :regla
  belongs_to :regla, polymorphic:true
  def periodo
    return Range.new(self.inicio,self.fin)
  end
  def periodo=unPeriodo
    self.inicio=unPeriodo.first
    self.fin=unPeriodo.last
  end
  def es_feriado?(una_fecha)
    (self.regla.es_feriado? una_fecha) && (self.periodo.include? una_fecha)
  end

end
