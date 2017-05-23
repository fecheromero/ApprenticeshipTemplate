class CreateWrapperDeReglaDeFeriados < ActiveRecord::Migration[5.1]
  def change
    create_table :wrapper_de_regla_de_feriados do |t|
      t.integer :regla_id
      t.string :regla_type
      t.integer :calendario_de_feriado_id
      t.string :calendario_de_feriado_type
      t.timestamps
    end
  end
end
