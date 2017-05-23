class ReglaDeFeriadoDeDiaDeMes < ApplicationRecord

  has_one :regla_de_feriado_con_periodo, as: :regla
  has_one :wrapper_de_regla_de_feriado, as: :regla
  def es_feriado?(una_fecha)
    (self.mes.eql? una_fecha.month) && (self.dia_de_mes.eql? una_fecha.day)
  end
end