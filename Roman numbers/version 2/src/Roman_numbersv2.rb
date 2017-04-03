class Class
  def subclasses
    ObjectSpace.each_object(Class).select { |klass| klass < self }
  end

end

class ProveedorDeTransformadoresQuinario
 def self.transformadorPara(numero)
  return  self.subclasses.detect{|handler|handler.puedoParsearNumeroENIntervaloQuinario(numero)}
 end
end
class TransformadorQuinarioBase<ProveedorDeTransformadoresQuinario
  def self.parsearNumeroEnIntervaloQuinario (caracterBase,caracterUnario,caracterLimite,numero)
    return  caracterBase+(caracterUnario*numero.modulo(10).modulo(5))
  end
  def self.puedoParsearNumeroENIntervaloQuinario(numero)
    return numero.modulo(5)<4
  end
end
class TransformadorQuinarioLimite<ProveedorDeTransformadoresQuinario
  def self.parsearNumeroEnIntervaloQuinario (caracterBase,caracterUnario,caracterLimite,numero)
    return  caracterUnario+caracterLimite
  end

  def self.puedoParsearNumeroENIntervaloQuinario(numero)
    return numero.modulo(5)==4
  end
  end
class ProveedorDeTransformadoresDeValorPosicional
  def self.transformadorPara(numero)
    return  self.subclasses.detect{|handler|handler.puedoParsearDecimalSegunTrinidadDeCaracteres(numero.modulo(10))}
  end
end
class TransformadorPosicionalDelIntervaloMenor<ProveedorDeTransformadoresDeValorPosicional
  def self.parsearDecimalSegunTrinidadDeCaracteres(caracterUnario,caracterQuinario,caracterDecimal,numero)
    return  ProveedorDeTransformadoresQuinario.transformadorPara(numero).parsearNumeroEnIntervaloQuinario('', caracterUnario, caracterQuinario, numero)

  end

  def self.puedoParsearDecimalSegunTrinidadDeCaracteres(numero)
    return 0<=numero && numero<5
  end
  end
class TransformadorPosicionalDelintervaloMayor<TransformadorPosicionalDelIntervaloMenor
  def self.parsearDecimalSegunTrinidadDeCaracteres(caracterUnario,caracterQuinario,caracterDecimal,numero)
    return ProveedorDeTransformadoresQuinario.transformadorPara(numero).parsearNumeroEnIntervaloQuinario(caracterQuinario, caracterUnario, caracterDecimal, numero)
  end

  def self.puedoParsearDecimalSegunTrinidadDeCaracteres(numero)
    return 5<=numero && numero<10
  end
  end
class ProveedorDeConversoresRomanos
  def self.conversorPara(numero)
    return self.subclasses.detect{|conversor| conversor.puedoConvertirARomano(numero)}
  end
end
class ConversorDeNumerosValidos<ProveedorDeConversoresRomanos

  def self.convertirARomano(numero)
   return (ProveedorDeTransformadoresDeValorPosicional.transformadorPara(numero/1000).parsearDecimalSegunTrinidadDeCaracteres('M', 'V', 'X', numero/1000)+ProveedorDeTransformadoresDeValorPosicional.transformadorPara(numero/100).parsearDecimalSegunTrinidadDeCaracteres('C', 'D', 'M', numero/100) +ProveedorDeTransformadoresDeValorPosicional.transformadorPara(numero/10).parsearDecimalSegunTrinidadDeCaracteres('X', 'L', 'C', numero/10)+ProveedorDeTransformadoresDeValorPosicional.transformadorPara(numero).parsearDecimalSegunTrinidadDeCaracteres('I', 'V', 'X', numero))
 end
  def self.puedoConvertirARomano(numero)
    return numero>0
  end
end
class ConversorDeNumerosInvalidos<ProveedorDeConversoresRomanos
  def self.convertirARomano(numero)
    raise 'numero invalido para convertir a romano'
    end
  def self.puedoConvertirARomano(numero)
    return numero<=0
  end
end
class Fixnum

  def romano
    return ProveedorDeConversoresRomanos.conversorPara(self).convertirARomano(self)
  end
  end