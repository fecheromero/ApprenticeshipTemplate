class AddNombreToCalendarioDeFeriados < ActiveRecord::Migration[5.1]
  def change
    add_column :calendario_de_feriados, :nombre, :string
  end
end
