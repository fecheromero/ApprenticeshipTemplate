class CreateReglaDeFeriadoFechas < ActiveRecord::Migration[5.1]
  def change
    create_table :regla_de_feriado_fechas do |t|
      t.date :fecha
      t.integer :calendario_de_feriado_id
      t.timestamps
    end
  end
end
