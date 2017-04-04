class Class
  def subclasses
    ObjectSpace.each_object(Class).select { |klass| klass < self }
  end

end
class ValorRomano
  def self.transformadorRomanoPara(unNumero)
  subclasses.detect{|transformador| transformador.puedoTransformarA(unNumero)}
  end
  def self.valorRomanoMaximo
    return 3000
  end
  end
class ValorRomanoInvalido<ValorRomano
  def self.primerValor(numeroRomano)
  raise 'numero romano invalido'
  end
  def self.puedoTransformarA(numeroRomano)
    !ValorRomanoValido.puedoTransformarA(numeroRomano)
  end
end
class ValorRomanoValido<ValorRomano
  @valorMatematico
  @caracter
  @@valoresRomanos=[]
  def self.agregarValor(unValor)
    @@valoresRomanos.push(unValor)
  end
  def initialize(valorMatematico,valorRomano)
    @valorMatematico=valorMatematico
    @caracter=valorRomano
    ValorRomanoValido.agregarValor(self)
  end

  def self.primerValor(numeroRomano)
    @@valoresRomanos.detect{|valorRomano| valorRomano.esMiCaracter(numeroRomano)}
  end
  def self.puedoTransformarA(numero)
  return   numero.split('').all?{|caracter| @@valoresRomanos.any?{|valorRomano| valorRomano.esMiCaracter(caracter)}}
  end
  def reducirNumeroRomano(numeroRomano,valorMatematicoAnterior)
    @numeroReducido=numeroRomano.strip[1,numeroRomano.length]
    return AcomodadorDeValoresMatematicos.acomodadorPara(@valorMatematico,valorMatematicoAnterior).acomodar(valorMatematicoAnterior)+@valorMatematico+ValorRomanoValido.primerValor(@numeroReducido).reducirNumeroRomano(@numeroReducido, @valorMatematico)
    end
  def
  esMiCaracter(numeroRomano)
    return numeroRomano.strip[0,1]== @caracter
  end
end
class AcomodadorDeValoresMatematicos
def self.acomodadorPara(valorMatematico,valorMatematicoAnterior)
  self.subclasses.detect{|acomodador| acomodador.puedoAcomodarA(valorMatematico,valorMatematicoAnterior)}
end
end
class AcomodadorDeRestas<AcomodadorDeValoresMatematicos
  def self.puedoAcomodarA(valorMatematico,valorMatematicoAnterior)
  return valorMatematico>valorMatematicoAnterior
  end
  def self.acomodar(valorMatematicoAnterior)
  return valorMatematicoAnterior*(-2)
  end
end
class AcomodadorDeSumas<AcomodadorDeValoresMatematicos
  def self.puedoAcomodarA(valorMatematico,valorMatematicoAnterior)
    return valorMatematico<=valorMatematicoAnterior
  end
  def self.acomodar(valorMatematicoAnterior)
    return 0
  end

end
RomanNull=ValorRomanoValido.new(0, '')
RomanNull.define_singleton_method(:reducirNumeroRomano)do
  |numeroRomano,valorRomanoSiguiente|
  return 0
end
RomanNull.define_singleton_method(:esMiCaracter)do
|numeroRomano|
  return numeroRomano==''
end
I=ValorRomanoValido.new(1, 'I')
V=ValorRomanoValido.new(5, 'V')
X=ValorRomanoValido.new(10, 'X')
L=ValorRomanoValido.new(50, 'L')
C=ValorRomanoValido.new(100, 'C')
D=ValorRomanoValido.new(500, 'D')
M=ValorRomanoValido.new(1000, 'M')
class String
  def to_digit
     ValorRomano.transformadorRomanoPara(self).primerValor(self).reducirNumeroRomano(self, ValorRomano.valorRomanoMaximo)
  end
end