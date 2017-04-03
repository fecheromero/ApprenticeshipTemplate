
"I,II,III,IV,V,VI,VII,VIII,IX"
class RomanConversor
  '  def roman_conversor(primer_caracter_del_rango,caracter_medio_del_rango,caracter_final_del_rango,valor_modulo,numero)
    representaciones=['',primer_caracter_del_rango,primer_caracter_del_rango*2,primer_caracter_del_rango*3,
    primer_caracter_del_rango+caracter_medio_del_rango,caracter_medio_del_rango+primer_caracter_del_rango,
    caracter_medio_del_rango+(primer_caracter_del_rango*2),caracter_medio_del_rango+(primer_caracter_del_rango*3),
    primer_caracter_del_rango+caracter_final_del_rango]
    representaciones[numero.modulo (valor_modulo).div(valor_modulo/10).truncate ]
    conversor_centenas+conversor_decenas+conversor_unidades
  end'
  def conversor_unidades(a_number)
representaciones=['','I','II','III','IV','V','VI','VII','VIII','IX']

  representaciones[a_number.modulo 10]
  end

  def conversor_decenas(a_number)
    representaciones=['','X','XX','XXX','XL','L','LX ','LXX','LXXX','XC']

    representaciones[a_number.modulo(100).div(10).truncate]
  end
  def conversor_centenas(a_number)
    representaciones=['','C','CC','CCC','CD','D','DC ','DCC','DCCC','CM']

    representaciones[a_number.modulo(1000).div(100).truncate]
  end
  def conversor_millares(a_number)
    representaciones=['','M','MM','MMM']

    representaciones[a_number.modulo(10000).div(1000).truncate]
  end
  def conversor_a_romano(a_number)
    conversor_millares(a_number)+conversor_centenas(a_number)+conversor_decenas(a_number)+conversor_unidades(a_number)
  end
end