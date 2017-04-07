
"I,II,III,IV,V,VI,VII,VIII,IX"
class RomanConversor
  @primer_caracter_del_rango
  @caracter_medio_del_rango
  @caracter_final_del_rango
  @valor_modulo
  @@conversores=[]
  def initialize(primer_Caracter,caracter_medio,caracter_final,valor_mudulo)
  @primer_caracter_del_rango=primer_Caracter
    @caracter_medio_del_rango=caracter_medio
    @caracter_final_del_rango=caracter_final
    @valor_modulo=valor_mudulo
    @@conversores.push(self)
  end
  def valor_modulo()
    return @valor_modulo
    end
  def roman_partial_conversion(numero)
    representaciones=['',@primer_caracter_del_rango,@primer_caracter_del_rango*2,@primer_caracter_del_rango*3,
    @primer_caracter_del_rango+@caracter_medio_del_rango,@caracter_medio_del_rango,@caracter_medio_del_rango+@primer_caracter_del_rango,
    @caracter_medio_del_rango+(@primer_caracter_del_rango*2),@caracter_medio_del_rango+(@primer_caracter_del_rango*3),
    @primer_caracter_del_rango+@caracter_final_del_rango]
  return  representaciones[ numero.modulo(@valor_modulo).div(@valor_modulo/10).truncate ]
  end
  def self.conversor_a_romano(numero)
  numeroEnRomano=@@conversores.sort{|convesor1| convesor1.valor_modulo()}.map{|conversor1| conversor1.roman_partial_conversion(numero)}.inject(''){|conversionParcial1,conversionParcial2|conversionParcial1+conversionParcial2}
  return numeroEnRomano
  end
  end
RomanConversor.new('I','V','X',10)
RomanConversor.new('X','L','C',100)
RomanConversor.new('C','D','M',1000)
RomanConversor.new('M','.','.',10000)
