class CalendarioDeFeriado < ApplicationRecord

  has_many :regla_de_feriados

  def es_feriado?(una_fecha)
    self.reglas_de_feriado.any? { |regla| regla.es_feriado? una_fecha }
  end

  def reglas_de_feriado
    return self.regla_de_feriados
  end


  def agregar_regla_de_feriado(una_regla)
    self.reglas_de_feriado << una_regla
  end
end
