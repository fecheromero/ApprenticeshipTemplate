
class RomanConversor
  @primer_caracter_del_rango
  @caracter_medio_del_rango
  @caracter_final_del_rango
   @conversores=[]
    def self.conversores
           return @conversores
    end


  def initialize(primer_Caracter,caracter_medio,caracter_final,valor_mudulo)
  @primer_caracter_del_rango=primer_Caracter
    @caracter_medio_del_rango=caracter_medio
    @caracter_final_del_rango=caracter_final
    @valor_modulo=valor_mudulo
    self.class.conversores.push(self)
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


  def self.convertir_a_romano(numero)
    if(numero>0 && numero<=3000)
  numeroEnRomano=self.conversores.sort{|conversor1,conversor2| conversor2.
      valor_modulo()<=>conversor1.valor_modulo()}.map{|conversor1| conversor1.roman_partial_conversion(numero)}.
      inject(''){|conversionParcial1,conversionParcial2|conversionParcial1+conversionParcial2}
  return numeroEnRomano
    else
      raise 'numero invalido para convertir a romano'
    end
  end


end


RomanConversor.new('I','V','X',10)
RomanConversor.new('X','L','C',100)
RomanConversor.new('C','D','M',1000)
RomanConversor.new('M','.','.',10000)
