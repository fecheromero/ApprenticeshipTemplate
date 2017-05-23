class ReglaDeFeriadoFecha < ApplicationRecord
  has_one :regla_de_feriado_con_periodo, as: :regla

  has_one :wrapper_de_regla_de_feriado, as: :regla
   def es_feriado?(una_fecha)
    self.fecha.eql? una_fecha
  end
end
