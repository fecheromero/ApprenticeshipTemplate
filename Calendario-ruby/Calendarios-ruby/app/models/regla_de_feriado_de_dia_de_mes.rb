class ReglaDeFeriadoDeDiaDeMes < ApplicationRecord

  has_one :regla_de_feriado_con_intervalo, as: :regla

  belongs_to :calendario_de_feriado, optional: true
  def es_feriado?(una_fecha)
    (self.mes.eql? una_fecha.month) && (self.dia_de_mes.eql? una_fecha.day)
  end
end