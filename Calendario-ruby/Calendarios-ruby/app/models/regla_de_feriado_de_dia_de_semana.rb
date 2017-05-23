class ReglaDeFeriadoDeDiaDeSemana < ReglaDeFeriado

  has_many :regla_de_feriado, as: :regla
  has_one :regla_de_feriado_con_periodo, as: :regla_de_feriado
  def es_feriado?(una_fecha)
    self.dia_de_semana.eql? una_fecha.cwday
  end
end
