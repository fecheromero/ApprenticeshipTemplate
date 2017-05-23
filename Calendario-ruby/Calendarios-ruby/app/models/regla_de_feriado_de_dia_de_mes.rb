class ReglaDeFeriadoDeDiaDeMes < ReglaDeFeriado

  has_many :regla_de_feriado, as: :regla
  has_one :regla_de_feriado_con_periodo, as: :regla_de_feriado


  def es_feriado?(una_fecha)
    (self.mes.eql? una_fecha.month) && (self.dia_de_mes.eql? una_fecha.day)
  end
end