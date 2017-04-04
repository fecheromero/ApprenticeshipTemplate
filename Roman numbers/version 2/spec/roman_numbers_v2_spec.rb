require 'rspec'
require_relative '../src/Roman_numbersv2'
describe 'roman numbers test' do

  it '0 es un numero invalido para convertir a romano' do
    expect { 0.romano }.to raise_error("numero invalido para convertir a romano" )
  end
  it 'los numeros negativos son numeros invalidos para convertir a romano' do
    expect { -8.romano }.to raise_error("numero invalido para convertir a romano" )
  end
  it ' transformar un numero en el primer intervalo quinario' do
    expect(1.romano).to eq('I')
  end

  it ' transformar el limite menor del intervalo quinario' do
    expect(5.romano).to eq('V')
  end

  it 'transformar un numero en el segundo intervalo quinario' do
    expect(6.romano).to eq('VI')
  end

  it 'transformar el limite mayor del itnervalo quinario' do
    expect(9.romano).to eq('IX')
  end
  it 'transformar decenas puras' do
    expect(10.romano).to eq('X')
  end

  it 'transformar decenas + unidades ' do
    expect(45.romano).to eq('XLV')
  end

  it 'transformar centenas puras' do
    expect(500.romano).to eq('D')
  end
  it 'transformar centenas + unidades' do
    expect(207.romano).to eq('CCVII')
  end
  it ' transformar centenas + decenas + unidades' do
    expect(985.romano).to eq('CMLXXXV')
  end
  it ' transformar millares puros' do
    expect(1000.romano).to eq('M')
  end
  it 'transformar millares + unidades' do
    expect(1002.romano).to eq('MII')
  end
  it 'transformar millares + decenas + unidades' do
    expect(2089.romano).to eq('MMLXXXIX')
  end

  it 'transformar millares + centenas + decenas + unidades' do
    expect(2756.romano).to eq('MMDCCLVI')
  end

  it 'no se pueden transformar numeros mayores a 3000' do
    expect { 3001.romano }.to raise_error("numero invalido para convertir a romano" )
  end






end