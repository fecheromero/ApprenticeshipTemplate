class CalendariosController < ApplicationController


  def transformarReglaActionControllerAHash reglaAC
    reglaAC.permit(
        :dia_de_semana, :dia_de_mes,:mes,:fecha,:inicio,:fin)
        .to_h
  end
  #get /calendarios
  def buscar_calendarios
    criterio=params[:nombre]
    if (criterio.blank?)
      render json: CalendarioDeFeriado.all
    else
      render json: CalendarioDeFeriado.where("nombre like ?","%"+criterio+"%")
    end
  end

  #get /calendarios/:id

  def buscar_calendario
    id=params[:id]
    render json: CalendarioDeFeriado.find(id)
  end

  #post /calendarios
  def crear_calendario
    nombre=params[:nombre]
    jsonArray=params[:reglas]||= []
    reglas=jsonArray.map { |regla|
      ReglasDeFeriadoDeserializer.hashDeserialize (transformarReglaActionControllerAHash regla)
    }
        nuevoCalendario=CalendarioDeFeriado.new(nombre: nombre)
    reglas.each { |regla| nuevoCalendario.agregar_regla_de_feriado regla }
    nuevoCalendario.save!
    render json: nuevoCalendario
  end

  #put /calendarios/:id
  def modificar_calendario
    id=params[:id]
    nombre=params[:nombre]
    reglas=params[:reglas].map { |regla|
      ReglasDeFeriadoDeserializer.hashDeserialize (transformarReglaActionControllerAHash regla)
                                }
    calendarioAModificar=CalendarioDeFeriado.find(id)
    reglas.each { |regla| calendarioAModificar.agregar_regla_de_feriado regla }
    calendarioAModificar.save!
    render json: calendarioAModificar
  end

  #get /calendarios/:id/feriados
  def obtener_feriados
    id=params[:id]
    calendario=CalendarioDeFeriado.find id
    fechaDesde=params[:desde]
    fechaHasta=params[:hasta]
    if(fechaDesde.nil?)
      fechaDesde= Date.new(Date.today.year,1,1)
    else
      fechaDesde=Date.strptime(fechaDesde,'%d/%m/%Y')
    end
    if(fechaHasta.nil?)
      fechaHasta=Date.new(Date.today.year,12,31)
    else
      fechaHasta=Date.strptime(fechaHasta,'%d/%m/%Y')
    end
    render json: calendario.feriados_entre(fechaDesde,fechaHasta)
  end


  #post /calendarios/:id/reglas_de_feriado
  def agregar_regla
    id=params[:id]
    calendario=CalendarioDeFeriado.find id
    regla=request.raw_post
    calendario.agregar_regla_de_feriado(ReglasDeFeriadoDeserializer.hashDeserialize regla)
    render json: regla
  end


  #GET /calendarios/es_feriado?fecha=unaFecha
  def calendarios_donde_es_feriado?
    fecha=params[:fecha]
    if(fecha.nil?)
      fecha=Date.today
    else
      fecha=Date.strptime(fecha,'%d/%m/%Y')
    end
    render json:CalendarioDeFeriado.all.select{|calendario| calendario.es_feriado? fecha}
  end

end