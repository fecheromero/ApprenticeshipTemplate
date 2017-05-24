class CalendarioDeFeriado < ApplicationRecord

  has_many :reglas,class_name: "WrapperDeReglaDeFeriado"

  def es_feriado?(una_fecha)
    self.reglas_de_feriado.any? { |regla| regla.es_feriado? una_fecha }
  end

  def reglas_de_feriado
    return reglas.map { |wrappRegla| wrappRegla.regla}
  end


  def agregar_regla_de_feriado(una_regla)
    unWrapperDeReglaDeFeriado=WrapperDeReglaDeFeriado.new
    unWrapperDeReglaDeFeriado.regla=una_regla
    self.reglas<< unWrapperDeReglaDeFeriado
    save!
  end
  def feriados_entre(fecha_desde,fecha_hasta)
    Range.new(fecha_desde,fecha_hasta).to_a.select{|unaFecha| es_feriado?unaFecha}
  end
end

