class CreateReglaDeFeriados < ActiveRecord::Migration[5.1]
  def change
    create_table :regla_de_feriados do |t|
      t.date :fecha
      t.date :inicio
      t.date :fin
      t.integer :regla_de_feriado_id
      t.string :regla_de_feriado_type
      t.integer :mes
      t.integer :dia_de_mes
      t.string :dia_de_semana
      t.string :type
      t.belongs_to :calendario_de_feriado, index: true
      t.belongs_to :regla_de_feriado_con_periodo, index: true
      t.timestamps
    end
  end
end
