require 'rspec'
require_relative '../src/roman_numbers'
describe 'roman numbers test' do
  conversor=RomanConversor.new
  it 'xxx' do

    expect(conversor.conversor_unidades 5).to eq('V')
  end
   it 'xxx2' do

      expect(conversor.conversor_unidades 14).to eq('IV')
   end

  it 'xxx3' do

    expect(conversor.conversor_unidades 158).to eq('VIII')
  end

  it 'xxx4' do

    expect(conversor.conversor_unidades 0).to eq('')
  end

  it 'xxx5' do

    expect(conversor.conversor_unidades 55).to eq('V')
  end

  it 'xxx6' do

    expect(conversor.conversor_decenas 30).to eq('XXX')
  end

  it 'xxx7' do

    expect(conversor.conversor_decenas 45).to eq('XL')
  end
  it 'xxx8' do

    expect(conversor.conversor_decenas 170 ).to eq('LXX')
  end

  it 'xxx9' do

    expect(conversor.conversor_centenas 5 ).to eq('')
  end

  it 'xxx10' do

    expect(conversor.conversor_centenas 20 ).to eq('')
  end

  it 'xxx11' do

    expect(conversor.conversor_centenas 300 ).to eq('CCC')
  end

  it 'xxx12' do

    expect(conversor.conversor_centenas 550 ).to eq('D')
  end

  it 'xxx13' do

    expect(conversor.conversor_millares 5 ).to eq('')
  end

  it 'xxx14' do

    expect(conversor.conversor_millares 50 ).to eq('')
  end

  it 'xxx15' do

    expect(conversor.conversor_millares 550 ).to eq('')
  end

  it 'xxx16' do

    expect(conversor.conversor_millares 2000 ).to eq('MM')
  end

  it 'xxx17' do

    expect(conversor.conversor_millares 550 ).to eq('')
  end
  it 'xxx18' do

    expect(conversor.conversor_a_romano 2534).to eq('MMDXXXIV')
  end
end