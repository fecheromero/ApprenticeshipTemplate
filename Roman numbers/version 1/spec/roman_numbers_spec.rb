require 'rspec'
require_relative '../src/roman_numbers'
describe 'roman numbers test' do
  it 'numeros menores a 10 se convierten correctamente' do

    expect(RomanConversor.convertir_a_romano 5).to eq('V')
  end
   it 'numeros menores a 50 se convierten correctamente' do

      expect(RomanConversor.convertir_a_romano 14).to eq('XIV')
   end

  it 'numeros menores a 100 se convierten correctamente' do

    expect(RomanConversor.convertir_a_romano 55).to eq('LV')
  end
  it 'numeros menores a 500 se convierten correctamente' do

    expect(RomanConversor.convertir_a_romano 158).to eq('CLVIII')
  end


  it 'numeros menores a mil se convierten correctamente' do

    expect(RomanConversor.convertir_a_romano 2534).to eq('MMDXXXIV')
  end


  it 'numeros menores a 3000 se convierten correctamente' do

    expect(RomanConversor.convertir_a_romano 2000 ).to eq('MM')
  end

  it 'numeros negativos no se pueden convertir' do

    expect{RomanConversor.convertir_a_romano -4}.to raise_error('numero invalido para convertir a romano')
  end

  it 'numeros mayores a 3000 no se pueden convertir' do

    expect{RomanConversor.convertir_a_romano 3001}.to raise_error('numero invalido para convertir a romano')
  end

  it 'el 0 no se puede convertir' do

    expect{RomanConversor.convertir_a_romano 0}.to raise_error('numero invalido para convertir a romano')
  end
end