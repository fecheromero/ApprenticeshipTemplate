require 'rspec'
require_relative '../src/RomanToDigit'

context 'RomanToDigit' do

  it 'Se tansforman bien numeros romanos unitarios' do
    expect('I'.to_digit).to eq(1)
  end
  it 'se transforman bien numeros romanos que representan sumas contiguas ' do
    expect('II'.to_digit).to eq(2)
  end
  it 'V es 5' do
    expect('V'.to_digit).to eq(5)
  end
  it 'se transforman bien numeros romanos que presentan restas internas' do
    expect('IX'.to_digit).to eq(9)
  end
  it 'X es 10' do
    expect('X'.to_digit).to eq(10)
  end
  it 'XIV es 14' do
    expect('XIV'.to_digit).to eq(14)
  end

  it 'se transforman bien unidades + decenas' do
    expect('XXIII'.to_digit).to eq(23)
  end
  it 'se transforman bien unidades + decenas + centenas' do
    expect('CCLXXXIX'.to_digit).to eq(289)
  end

  it 'se transforman bien unidades + decenas + centenas' do
    expect('CCXL'.to_digit).to eq(240)
  end

  it 'se transforman bien millares' do
    expect('MM'.to_digit).to eq(2000)
  end

  it 'se transforman bien millares + centenas' do
    expect('MMCD'.to_digit).to eq(2400)
  end

  it 'se transforman bien millares + centenas + decenas' do
    expect('MMCCCXL'.to_digit).to eq(2340)
  end

  it 'se transforman bien millares + centenas + decenas' do
    expect('MMCCCXLV'.to_digit).to eq(2345)
  end
  it 'Strings que no representan numeros romanos no se pueden transformar' do
    expect { 'saraza'.to_digit }.to raise_error("numero romano invalido" )
  end
end