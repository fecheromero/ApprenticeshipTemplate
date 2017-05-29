require 'rails_helper'


describe CalendariosController, type: :controller do
  let(:unaFecha){Date.new(2017,5,13)}
  let(:calendarioDeArgentina){CalendarioDeFeriado.new(nombre:'Argentina')}
  let(:calendarioDeChile){CalendarioDeFeriado.new(nombre:'Chile')}
  let(:reglaDeFeriadoFecha){ReglaDeFeriadoFecha.new(fecha: unaFecha)}
  let(:reglaDeFeriadoDeDiaDeMes){ReglaDeFeriadoDeDiaDeMes.new(mes: 12, dia_de_mes: 22)}
  let(:reglaDeFeriadoDeDiaDeSemana){ReglaDeFeriadoDeDiaDeSemana.new(dia_de_semana: 7)}

  def json_response
    (JSON.parse response.body)
  end

  def expectJsonIgualACalendario jsonCalendario,calendario
    expect(jsonCalendario['id']).to be(calendario.id)
    expect(jsonCalendario['nombre']).to eq(calendario.nombre)
  end

  it 'test get /calendarios trae todos los calendarios' do
      expect(CalendarioDeFeriado.count).to be(0)
      expect{ calendarioDeArgentina.save!}.to change { CalendarioDeFeriado.count}.by(1)
     get :buscar_calendarios
    expect(response).to have_http_status :ok
     expectJsonIgualACalendario(json_response[0], calendarioDeArgentina)

  end



  it 'test get /calendarios?nombre trae los calendarios que contengan el nombre' do
    calendarioDeArgentina.save!
    calendarioDeChile.save!
    get :buscar_calendarios,  {params: {nombre: 'Argentina'}}
    expect(response).to have_http_status :ok
    expectJsonIgualACalendario(json_response[0], calendarioDeArgentina)
    expect(json_response.count).to be(1)
  end


  it 'test get /calendarios/id trae el calendario que responda a ese id' do
    calendarioDeArgentina.save!
    get :buscar_calendario, params: {id: calendarioDeArgentina.id}
    expect(response).to have_http_status :ok
    expectJsonIgualACalendario(json_response, calendarioDeArgentina)
  end
  #TODO CALENDARIO QUE NO EXISTE


  it 'test post /calendarios crea un calendario' do
    post :crear_calendario, params: {nombre:'Argentina',reglasDeFeriado:[]}
    expect(CalendarioDeFeriado.count).to eq(1)
    expect((CalendarioDeFeriado.first).reglas_de_feriado.count).to be(0)
    expect(CalendarioDeFeriado.first.nombre).to eq('Argentina')
  end

  it 'test put /calendarios/id modificar un calendario existente' do
    unasReglasJsoneadas=[ActiveModelSerializers::SerializableResource.new(reglaDeFeriadoDeDiaDeMes).as_json]
    calendarioDeArgentina.save!
    id=calendarioDeArgentina.id
    expect {
      put :modificar_calendario,
          params: {id: id, nombre: 'calendario de argentina modificado', reglasDeFeriado: unasReglasJsoneadas}
    }.not_to change{ CalendarioDeFeriado.count }

    calendarioDeArgentina.reload
    expect(calendarioDeArgentina.reglas_de_feriado.count).to eq(unasReglasJsoneadas.count)

    reglaEnCalendario=calendarioDeArgentina.reglas_de_feriado.first
    expect(reglaEnCalendario.mes).to be (reglaDeFeriadoDeDiaDeMes.mes)
    expect(reglaEnCalendario.dia_de_mes).to be(reglaDeFeriadoDeDiaDeMes.dia_de_mes)
  end


  it 'test get /calendarios/id/feriados?desde= & hasta= retorna los feriados entre esas 2 fechas para ese calendario'do
      calendarioDeArgentina.agregar_regla_de_feriado reglaDeFeriadoDeDiaDeSemana
      calendarioDeArgentina.save!
      cantidadDeFeriadosEntreEl1DeEneroYEl1DeMayo=calendarioDeArgentina.feriados_entre(Date.new(2017,1,1),Date.new(2017,5,1)).to_a.count
      get :obtener_feriados, params: {id: calendarioDeArgentina.id, desde: '1/1/2017', hasta: '1/5/2017'}
       expect(response).to have_http_status :ok                                                                         
       expect(json_response.count).to be(cantidadDeFeriadosEntreEl1DeEneroYEl1DeMayo)
      expect(json_response.all? { |jsonDia| calendarioDeArgentina.es_feriado? Date.strptime(jsonDia,'%Y-%m-%d')}).to be_truthy
  end


  it 'test get /calendarios/id/feriados retorna los feriados de este año'do
    unaReglaConPeriodo=ReglaDeFeriadoConIntervalo.new()
    unaReglaConPeriodo.intervalo=Range.new(Date.new(2017,3,1),Date.new(2017,6,1))
    unaReglaConPeriodo.regla=reglaDeFeriadoDeDiaDeSemana
    calendarioDeArgentina.agregar_regla_de_feriado unaReglaConPeriodo
    calendarioDeArgentina.save!
    cantidadDeDomingosEntre1DeEneroY1deMayo=calendarioDeArgentina.feriados_entre(Date.new(2017,1,1),Date.new(2017,12,31)).to_a.count
    get :obtener_feriados, params: {id: calendarioDeArgentina.id}
    expect(response).to have_http_status :ok
    expect(json_response.count).to be(cantidadDeDomingosEntre1DeEneroY1deMayo)
  end

  it 'test get /calendarios/id/reglas_de_feriado agrega una regla'do
     calendarioDeArgentina.save!
    post :agregar_regla, params:ActiveModelSerializers::SerializableResource.new(reglaDeFeriadoDeDiaDeMes).as_json.merge({id:calendarioDeArgentina.id})
    calendarioDeArgentina.reload
    reglas_de_feriado = calendarioDeArgentina.reglas_de_feriado
    expect(reglas_de_feriado.count).to be(1)
    reglaEnCalendario=calendarioDeArgentina.reglas_de_feriado.first
    expect(reglaEnCalendario.mes).to eq(reglaDeFeriadoDeDiaDeMes.mes)
    expect(reglaEnCalendario.dia_de_mes).to eq(reglaDeFeriadoDeDiaDeMes.dia_de_mes)
  end

  it 'test get /calendarios/es_feriado? con una fecha retorna los calendarios donde esa fecha es feriado'do
    calendarioDeArgentina.agregar_regla_de_feriado reglaDeFeriadoFecha
    calendarioDeArgentina.save!
    calendarioDeChile.save!
    post :calendarios_donde_es_feriado?, params: {fecha:'13/05/2017'}
    expect(json_response.count).to be(1)
    expectJsonIgualACalendario(json_response[0],calendarioDeArgentina)
  end

  it 'test get /calendarios/es_feriado? sin una fecha retorna los calendarios donde hoy es feriado'do
    calendarioDeArgentina.agregar_regla_de_feriado reglaDeFeriadoFecha
    calendarioDeArgentina.save!
    calendarioDeChile.save!
    post :calendarios_donde_es_feriado?
    expect(json_response.count).to be(0)
  end

end