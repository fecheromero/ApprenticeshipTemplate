require 'rspec'
require_relative '../src/fibonacci.rb'
context 'fibonacci.rb  ' do
  specify 'fibonacci de la posicion 0 devuelve el primer numero
de la posicion de fibonacci respondiendo a un caso base ' do

    expect(0.fibonacci).to eq(1)
  end
  specify 'fibonacci de la posicion 1 devuelve el segundo numero de la
posicion de fibonacci respondiendo a un caso base' do
    expect(1.fibonacci).to eq(1)
  end
  specify 'fibonacci de un numero n>1 devuelve el valor correspondiente a la posicion n en la sucesion
    respondiendo al caso general' do
    n=8
    expect(n.fibonacci).to eq((n-1).fibonacci+(n-2).fibonacci)
  end

  specify 'fibonacci de un numero negativo lanza la excepcion correspondiente ' do

    expect { -8.fibonacci }.to raise_error("invalid fibonacci position" )
  end

end