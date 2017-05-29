class ReglaDeFeriadoFecha < ApplicationRecord
  has_one :regla_de_feriado_con_intervalo, as: :regla

  belongs_to :calendario_de_feriado, optional: true
   def es_feriado?(una_fecha)
    self.fecha.eql? una_fecha
  end
end
