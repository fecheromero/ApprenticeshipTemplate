require 'rails_helper'

describe 'Calendario de Feriados' do

  let(:calendario_de_feriados) { CalendarioDeFeriado.new }

  it 'test01: un dia de semana puede ser feriado' do
    un_sabado = Date.new(2017, 4, 29)
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoDiaDeSemana.new(un_sabado.cwday))
    expect(calendario_de_feriados.es_feriado? un_sabado).to be_truthy
  end

  it 'test02: un dia de semana puede NO ser feriado' do
    un_miercoles = Date.new(2017, 5, 3)
    expect(calendario_de_feriados.es_feriado? un_miercoles).to be_falsey
  end

  it 'test03: mas de un dia de semana puede ser feriado' do
    un_sabado = Date.new(2017, 5, 6)
    un_domingo = Date.new(2017, 5, 7)
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoDiaDeSemana.new(un_sabado.cwday))
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoDiaDeSemana.new(un_domingo.cwday))
    expect(calendario_de_feriados.es_feriado? un_sabado).to be_truthy
    expect(calendario_de_feriados.es_feriado? un_domingo).to be_truthy
  end

  it 'test04: un dia de mes puede ser feriado' do
    un_primero_de_mayo = Date.new(2017, 5, 1)
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoDiaDeMes.new(5, 1))
    expect(calendario_de_feriados.es_feriado? un_primero_de_mayo).to be_truthy
  end

  it 'test05: mas de un dia de mes puede ser feriado' do
    un_primero_de_mayo = Date.new(2017, 5, 1)
    un_25_de_mayo = Date.new(2017, 5, 25)
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoDiaDeMes.new(5, 1))
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoDiaDeMes.new(5, 25))
    expect(calendario_de_feriados.es_feriado? un_primero_de_mayo).to be_truthy
    expect(calendario_de_feriados.es_feriado? un_25_de_mayo).to be_truthy
  end

  it 'test06: un dia de mes puede NO ser feriado' do
    un_primero_de_mayo = Date.new(2017, 5, 1)
    expect(calendario_de_feriados.es_feriado? un_primero_de_mayo).to be_falsey
  end

  it 'test07: una fecha puede ser feriado' do
    cumpleaños_de_eze = Date.new(2017, 10, 16)
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoFecha.new(cumpleaños_de_eze))
    expect(calendario_de_feriados.es_feriado? cumpleaños_de_eze).to be_truthy
  end

  it 'test08: mas de una fecha puede ser feriado' do
    cumpleaños_de_eze = Date.new(2017, 10, 16)
    cumpleaños_de_feche = Date.new(2017, 12, 22)
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoFecha.new(cumpleaños_de_eze))
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoFecha.new(cumpleaños_de_feche))
    expect(calendario_de_feriados.es_feriado? cumpleaños_de_eze).to be_truthy
    expect(calendario_de_feriados.es_feriado? cumpleaños_de_feche).to be_truthy
  end

  it 'test09: una fecha puede NO ser feriado' do
    cumpleaños_de_eze = Date.new(2017, 10, 16)
    expect(calendario_de_feriados.es_feriado? cumpleaños_de_eze).to be_falsey
  end

  it 'test10: un dia de la semana puede ser feriado en un periodo de tiempo' do
    un_sabado = Date.new(2017, 4, 29)
    inicio = Date.new(2015, 10, 12)
    fin = Date.new(2019, 10, 12)
    periodo = Range.new(inicio, fin)
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoConPeriodo.new(
        ReglaDeFeriadoDiaDeSemana.new(un_sabado.cwday), periodo))
    expect(calendario_de_feriados.es_feriado? un_sabado).to be_truthy
  end

  it 'test11: un dia de la semana feriado en un periodo puede ser NO feriado fuera de ese periodo de tiempo' do
    un_sabado_fuera_del_periodo = Date.new(2014, 5, 10)
    inicio = Date.new(2015, 10, 12)
    fin = Date.new(2019, 10, 12)
    periodo = Range.new(inicio, fin)
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoConPeriodo.new(
        ReglaDeFeriadoDiaDeSemana.new(un_sabado_fuera_del_periodo.cwday), periodo))
    expect(calendario_de_feriados.es_feriado? un_sabado_fuera_del_periodo).to be_falsey
  end

  it 'test12: un dia de mes puede ser feriado en un periodo de tiempo' do
    cumpleaños_de_eze = Date.new(2017, 10, 16)
    inicio = Date.new(2015, 10, 12)
    fin = Date.new(2019, 10, 12)
    periodo = Range.new(inicio, fin)
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoConPeriodo.new(
        ReglaDeFeriadoDiaDeMes.new(10, 16), periodo))
    expect(calendario_de_feriados.es_feriado? cumpleaños_de_eze).to be_truthy
  end

  it 'test13: un dia de mes feriado en un periodo puede ser NO feriado fuera de ese periodo de tiempo' do
    un_cumpleaños_de_eze_fuera_del_periodo = Date.new(2014, 10, 16)
    inicio = Date.new(2015, 10, 12)
    fin = Date.new(2019, 10, 12)
    periodo = Range.new(inicio, fin)
    calendario_de_feriados.agregar_regla_de_feriado(ReglaDeFeriadoConPeriodo.new(
        ReglaDeFeriadoDiaDeMes.new(10, 16), periodo))
    expect(calendario_de_feriados.es_feriado? un_cumpleaños_de_eze_fuera_del_periodo).to be_falsey
  end

end