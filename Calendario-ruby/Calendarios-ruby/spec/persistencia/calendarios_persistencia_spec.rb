require 'rails_helper'

describe 'persistencia de calendarios' do


  it 'al crear una reglaDeFeriadoFecha y guardarla en la base aumenta la cantidad de reglas persistidas en la db' do
      unCalendario=CalendarioDeFeriado.new
      unaReglaDeFeriadoFecha=ReglaDeFeriadoFecha.new
      unaReglaDeFeriadoFecha.fecha=Date.new(2017,12,22)
      unCalendario.save!
      expect(unCalendario.reglas_de_feriado.empty?).to be_truthy
      unCalendario.save!
      unCalendario.agregar_regla_de_feriado unaReglaDeFeriadoFecha
      unCalendario.reload
      expect(unCalendario.reglas_de_feriado.empty?).to be_falsey

  end
end