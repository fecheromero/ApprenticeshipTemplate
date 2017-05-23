class CreateCalendarioDeFeriados < ActiveRecord::Migration[5.1]
  def change
    create_table :calendario_de_feriados do |t|

      t.timestamps
    end
  end
end
