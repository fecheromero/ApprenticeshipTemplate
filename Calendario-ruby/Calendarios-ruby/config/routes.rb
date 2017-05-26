Rails.application.routes.draw do
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  get '/', to: 'calendarios#hello'
  get '/calendarios/', to:'calendarios#buscar_calendarios'
  get '/calendarios/es_feriado', to:'calendarios#calendarios_donde_es_feriado?'
  get '/calendarios/:id', to:'calendarios#buscar_calendario'
  post '/calendarios', to:'calendarios#crear_calendario'
  put '/calendarios/:id',to: 'calendarios#modificar_calendario'
  get '/calendarios/:id/feriados', to:'calendarios#obtener_feriados'
  post '/calendarios/:id/reglas_de_feriado', to:'calendarios#agregar_regla'
end
