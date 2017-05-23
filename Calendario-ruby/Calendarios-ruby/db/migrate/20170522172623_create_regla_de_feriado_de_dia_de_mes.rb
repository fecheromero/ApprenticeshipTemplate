class CreateReglaDeFeriadoDeDiaDeMes < ActiveRecord::Migration[5.1]
  def change
    create_table :regla_de_feriado_de_dia_de_mes do |t|
      t.integer :mes
      t.integer :dia_de_mes
      t.timestamps
    end
  end
end
