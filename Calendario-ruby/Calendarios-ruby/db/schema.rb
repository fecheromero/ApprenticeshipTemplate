# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20170522195407) do

  create_table "calendario_de_feriados", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "regla_de_feriados", force: :cascade do |t|
    t.date "fecha"
    t.date "inicio"
    t.date "fin"
    t.integer "regla_de_feriado_id"
    t.string "regla_de_feriado_type"
    t.integer "mes"
    t.integer "dia_de_mes"
    t.string "dia_de_semana"
    t.string "type"
    t.integer "calendario_de_feriado_id"
    t.integer "regla_de_feriado_con_periodo_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["calendario_de_feriado_id"], name: "index_regla_de_feriados_on_calendario_de_feriado_id"
    t.index ["regla_de_feriado_con_periodo_id"], name: "index_regla_de_feriados_on_regla_de_feriado_con_periodo_id"
  end

end
