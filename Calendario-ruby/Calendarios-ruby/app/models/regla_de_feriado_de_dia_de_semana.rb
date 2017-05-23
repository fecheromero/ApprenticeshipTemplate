class ReglaDeFeriadoDeDiaDeSemana < ApplicationRecord
  has_one :regla_de_feriado_con_periodo, as: :regla

  has_one :wrapper_de_regla_de_feriado, as: :regla
  def es_feriado?(una_fecha)
    self.dia_de_semana.eql? una_fecha.cwday
  end
end
