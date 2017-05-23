class CreateReglaDeFeriadoConPeriodos < ActiveRecord::Migration[5.1]
  def change
    create_table :regla_de_feriado_con_periodos do |t|
      t.date :inicio
      t.date :fin
      t.integer :regla_id
      t.string :regla_type
      t.timestamps
    end
  end
end

