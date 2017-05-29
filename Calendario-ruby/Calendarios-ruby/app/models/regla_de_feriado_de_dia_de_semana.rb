class ReglaDeFeriadoDeDiaDeSemana < ApplicationRecord
  has_one :regla_de_feriado_con_intervalo, as: :regla

  belongs_to :calendario_de_feriado, optional: true
  def es_feriado?(una_fecha)
    self.dia_de_semana.eql? una_fecha.cwday
  end
end
