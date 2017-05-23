class ReglaDeFeriadoConPeriodo < ReglaDeFeriado

  has_one :regla_de_feriado


  def periodo
    return Range.new(self.inicio,self.fin)
  end

  def es_feriado?(una_fecha)
    (self.regla_de_feriado.es_feriado? una_fecha) && (self.periodo.include? una_fecha)
  end

end
