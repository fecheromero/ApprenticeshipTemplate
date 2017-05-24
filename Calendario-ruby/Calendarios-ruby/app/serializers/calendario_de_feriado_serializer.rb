class CalendarioDeFeriadoSerializer < ActiveModel::Serializer
  attributes :id,:nombre
  has_many :reglas
end
