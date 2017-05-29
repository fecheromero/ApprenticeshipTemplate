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

ActiveRecord::Schema.define(version: 20170523193412) do

  create_table "calendario_de_feriados", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.string "nombre"
  end

  create_table "regla_de_feriado_con_periodos", force: :cascade do |t|
    t.date "inicio"
    t.date "fin"
    t.integer "calendario_de_feriado_id"
    t.integer "regla_id"
    t.string "regla_type"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "regla_de_feriado_de_dia_de_mes", force: :cascade do |t|
    t.integer "mes"
    t.integer "dia_de_mes"
    t.integer "calendario_de_feriado_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "regla_de_feriado_de_dia_de_semanas", force: :cascade do |t|
    t.integer "dia_de_semana"
    t.integer "calendario_de_feriado_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "regla_de_feriado_fechas", force: :cascade do |t|
    t.date "fecha"
    t.integer "calendario_de_feriado_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

end
