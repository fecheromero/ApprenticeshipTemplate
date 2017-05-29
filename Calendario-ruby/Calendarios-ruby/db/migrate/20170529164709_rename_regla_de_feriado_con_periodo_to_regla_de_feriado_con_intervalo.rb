class RenameReglaDeFeriadoConPeriodoToReglaDeFeriadoConIntervalo < ActiveRecord::Migration[5.1]
  def change
    rename_table :regla_de_feriado_con_periodos, :regla_de_feriado_con_intervalos
  end
end
