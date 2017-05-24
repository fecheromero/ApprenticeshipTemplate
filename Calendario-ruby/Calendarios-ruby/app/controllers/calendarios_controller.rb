class CalendariosController < ApplicationController
  def hello
    render json: ReglaDeFeriadoFecha.new(fecha: Date.today)
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
    #params.require(:nombre)
    #params.require(:reglas)
    nombre=params[:nombre]
    jsonArray=params[:reglas] ||= []
    reglas=jsonArray.map { |regla| ReglasDeFeriadoDeserializer.deserialize regla}
        nuevoCalendario=CalendarioDeFeriado.new(nombre: nombre)
    reglas.each { |regla| nuevoCalendario.agregar_regla_de_feriado regla }
    nuevoCalendario.save!
  end

  #put /calendarios/:id
  def modificar_calendario
    id=params[:id]
    nombre=params[:nombre]
    reglas=params[:reglas].map { |regla| ReglasDeFeriadoDeserializer.deserialize regla}
    calendarioAModificar=CalendarioDeFeriado.find(id)
    reglas.each { |regla| calendarioAModificar.agregar_regla_de_feriado regla }
    calendarioAModificar.save!
  end

  #get /calendarios/:id/feriados
  #TODO ARREGLAR EL TEMA DE FECHAS STRPTIME
  def obtener_feriados
    id=params[:id]
    calendario=CalendarioDeFeriado.find id
    fechaDesdeDefault= '1/1/'+Date.today.year.to_s
    fechaHastaDefault= '31/12/'+Date.today.year.to_s
    fechaDesde=params[:desde]||fechaDesdeDefault
    fechaHasta=params[:hasta] ||fechaHastaDefault
    render json: calendario.feriados_entre(Date.parse(fechaDesde),Date.parse(fechaHasta))
  end

  #post /calendarios/:id/reglas_de_feriado
  def agregar_regla
    id=params[:id]
    calendario=CalendarioDeFeriado.find id
    regla=request.raw_post.to_json
    calendario.agregar_regla_de_feriado(ReglasDeFeriadoDeserializer.deserialize regla)
  end

end
