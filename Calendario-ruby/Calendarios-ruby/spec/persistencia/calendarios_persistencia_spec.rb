require 'rails_helper'

describe 'persistencia de calendarios' do

  it 'se puede relacionar una regla de feriado con periodo con otra regla'do
        unaReglaDeFeriadoFecha=ReglaDeFeriadoFecha.new
        unaReglaDeFeriadoFecha.fecha=Date.new(2017,12,22)
        unaReglaDeFeriadoConPeriodo=ReglaDeFeriadoConIntervalo.new
        unaReglaDeFeriadoConPeriodo.regla=unaReglaDeFeriadoFecha
        unaReglaDeFeriadoConPeriodo.periodo=Range.new(Date.new(2015,1,1),Date.new(2017,1,1))
        unaReglaDeFeriadoConPeriodo.save
        expect(ReglaDeFeriadoFecha.all.empty?).to be_falsey
        expect(unaReglaDeFeriadoConPeriodo.regla).to be(unaReglaDeFeriadoFecha)
        expect(ReglaDeFeriadoConIntervalo.all.empty?).to be_falsey

    end

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
      expect(ReglaDeFeriadoFecha.all.empty?).to be_falsey
  end
end