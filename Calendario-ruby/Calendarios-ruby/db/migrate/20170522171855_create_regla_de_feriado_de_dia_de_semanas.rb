class CreateReglaDeFeriadoDeDiaDeSemanas < ActiveRecord::Migration[5.1]
  def change
    create_table :regla_de_feriado_de_dia_de_semanas do |t|
      t.integer :dia_de_semana
      t.integer :calendario_de_feriado_id
      t.timestamps
    end
  end
end
