class ReglaDeFeriadoConPeriodoSerializer < ActiveModel::Serializer
  attributes :id,:inicio,:fin
  has_one :regla
end
