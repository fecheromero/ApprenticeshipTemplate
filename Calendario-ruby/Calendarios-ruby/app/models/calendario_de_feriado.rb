class CalendarioDeFeriado < ApplicationRecord

  has_many :reglas_de_feriado_con_periodo, class_name: "ReglaDeFeriadoConPeriodo"
  has_many :reglas_de_feriado_de_dia_de_mes, class_name: "ReglaDeFeriadoDeDiaDeMes"
  has_many :reglas_de_feriado_de_dia_de_semana, class_name: "ReglaDeFeriadoDeDiaDeSemana"
  has_many :reglas_de_feriado_fecha, class_name: "ReglaDeFeriadoFecha"
  def es_feriado?(una_fecha)
    self.reglas_de_feriado.any? { |regla|
      regla.es_feriado? una_fecha }
  end

  def reglas_de_feriado
    return reglas_de_feriado_con_periodo +
        reglas_de_feriado_de_dia_de_semana +
        reglas_de_feriado_de_dia_de_mes +
        reglas_de_feriado_fecha
  end


  def agregar_regla_de_feriado(una_regla)
    self.save!
    una_regla.calendario_de_feriado_id=id
    una_regla.save!
  end
  def feriados_entre(fecha_desde,fecha_hasta)
    Range.new(fecha_desde,fecha_hasta).to_a.select{|unaFecha| es_feriado?unaFecha}
  end

end

