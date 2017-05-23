class WrapperDeReglaDeFeriado < ApplicationRecord
  belongs_to :regla, polymorphic: true
  belongs_to :calendario_de_feriado
end
