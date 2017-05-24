require 'rails_helper'

def json_response
  (JSON.parse response.body)
end

describe CalendariosController, type: :controller do

  let(:calendarioDeArgentina){CalendarioDeFeriado.new(nombre:'Argentina')}
  let(:calendarioDeChile){CalendarioDeFeriado.new(nombre:'Chile')}
  let(:reglaDeFeriadoFecha){ReglaDeFeriadoFecha.new(fecha: Date.today)}
  let(:reglaDeFeriadoDeDiaDeMes){ReglaDeFeriadoDeDiaDeMes.new(mes: 12, dia_de_mes: 22)}
  let(:reglaDeFeriadoDeDiaDeSemana){ReglaDeFeriadoDeDiaDeSemana.new(dia_de_semana: 7)}

  it 'test get /calendarios trae todos los calendarios' do
     get :buscar_calendarios
    cantidadDeCalendariosAntesDelSave=json_response.count
    calendarioDeArgentina.save
    get :buscar_calendarios
    expect(response).to have_http_status :ok
    expect(json_response[0]['id']).to be(calendarioDeArgentina.id)
    expect(json_response.count).to be(cantidadDeCalendariosAntesDelSave+1)
  end



  it 'test get /calendarios?nombre trae los calendarios que contengan el nombre' do
    get :buscar_calendarios, params: {nombre: 'Argentina'}
    cantidadDeCalendariosAntesDelSave=json_response.count
    calendarioDeArgentina.save!
    calendarioDeChile.save!
    get :buscar_calendarios,  {params: {nombre: 'Argentina'}}
    expect(response).to have_http_status :ok
    expect(json_response[0]['id']).to be(calendarioDeArgentina.id)
    expect(json_response.count).to be(cantidadDeCalendariosAntesDelSave+1)
  end


  it 'test get /calendarios/id trae el calendario que responda a ese id' do
    calendarioDeArgentina.save!
    get :buscar_calendario, params: {id: calendarioDeArgentina.id}
    expect(response).to have_http_status :ok
    expect(json_response['id']).to be(calendarioDeArgentina.id)
  end
  #TODO CALENDARIO QUE NO EXISTE
  it 'test post /calendarios crea un calendario' do
    cantidadDeCalendariosPrevioAlPost=CalendarioDeFeriado.count
    post :crear_calendario, params: {nombre:'Argentina',reglas:[]}
    expect(CalendarioDeFeriado.count).to eq(cantidadDeCalendariosPrevioAlPost+1)
  end
  #TODO en ves de mostrar reglas mostrar reglas_de_feriado (ver si puedo jsonizar la respuesta al metodo)
  it 'test put /calendarios/id modificar un calendario existente' do
    unasReglasJsoneadas=[ActiveModelSerializers::SerializableResource.new(reglaDeFeriadoDeDiaDeMes).to_json]
    calendarioDeArgentina.save!
    id=calendarioDeArgentina.id
    #cantidadDeCalendariosPrevioAlPut=CalendarioDeFeriado.count
    expect {
      put :modificar_calendario, params: {id: id, nombre: 'calendario de argentina modificado', reglas: unasReglasJsoneadas}
      #expect(CalendarioDeFeriado.count).to eq(cantidadDeCalendariosPrevioAlPut)
    }.not_to change(CalendarioDeFeriado, :count)

    calendarioDeArgentina.reload
    expect(calendarioDeArgentina.reglas_de_feriado.count).to eq(unasReglasJsoneadas.count)
  end


  it 'test get /calendarios/id/feriados?desde= & hasta= retorna los feriados entre esas 2 fechas para ese calendario'do
    unasReglasJsoneadas=[ActiveModelSerializers::SerializableResource.new(reglaDeFeriadoDeDiaDeSemana).to_json]
      calendarioDeArgentina.agregar_regla_de_feriado reglaDeFeriadoDeDiaDeSemana
      calendarioDeArgentina.save!
      cantidadDeFeriadosEntreEl1DeEneroYEl1DeMayo=calendarioDeArgentina.feriados_entre(Date.new(2017,1,1),Date.new(2017,5,1)).to_a.count
      get :obtener_feriados, params: {id: calendarioDeArgentina.id, desde: '1/1/2017', hasta: '1/5/2017'}
       expect(response).to have_http_status :ok                                                                         
       expect(json_response.count).to be(cantidadDeFeriadosEntreEl1DeEneroYEl1DeMayo)
  end


  it 'test get /calendarios/id/feriados retorna los feriados de este año'do
    unaReglaConPeriodo=ReglaDeFeriadoConPeriodo.new()
    unaReglaConPeriodo.periodo=Range.new(Date.new(2017,3,1),Date.new(2017,6,1))
    unaReglaConPeriodo.regla=reglaDeFeriadoDeDiaDeSemana
    calendarioDeArgentina.agregar_regla_de_feriado unaReglaConPeriodo
    calendarioDeArgentina.save!
    cantidadDeDomingosEntre1DeEneroY1deMayo=calendarioDeArgentina.feriados_entre(Date.new(2017,1,1),Date.new(2017,12,31)).to_a.count
    get :obtener_feriados, params: {id: calendarioDeArgentina.id}
    expect(response).to have_http_status :ok
    expect(json_response.count).to be(cantidadDeDomingosEntre1DeEneroY1deMayo)
  end
  it 'test get /calendarios/id/reglas_de_feriado agrega una regla'do
    calendarioDeArgentina.agregar_regla_de_feriado reglaDeFeriadoFecha
    calendarioDeArgentina.save!
    cantidadDeReglasAntesDelPost=calendarioDeArgentina.reglas_de_feriado.count
    post :agregar_regla, body: reglaDeFeriadoDeDiaDeMes.as_json,params: {id:calendarioDeArgentina.id}
    calendarioDeArgentina.reload
    expect(calendarioDeArgentina.reglas_de_feriado.count).to be(cantidadDeReglasAntesDelPost+1)
  end
  #before :each do
   # algo(pepe)
  #end

#  let(:pepe) { "a" }

#  context '' do

#    let(:pepe) { "b" }

#    before :each do

 #   end

#    it '' do

#    end

 # end
end