package ulsazona.edu.mx

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import org.grails.web.json.JSONElement
import groovy.json.JsonOutput

@Secured(['ROLE_ADMIN','ROLE_OPERADOR','ROLE_USUARIO'])
@Transactional(readOnly = false)
class ConsumidorRestController {

	static final String urlRest = "http://localhost:8181/"

    def index() { 
    	def el_id = 0; //obtener el ultimo id
    	List<Estado> listEstado = new ArrayList<Estado>();
    	//GET - WS Rest
    	RestResponse rResponse = new RestBuilder().get(urlRest + "Estado")
    	if ( rResponse.statusCode.value() == 200 && rResponse.json ) {
    		System.out.println rResponse.json
    		def m = rResponse.json
    		for(je in m) {
    			Estado k = new Estado()
    			k.id = je.id
    			k.nombre = je.nombre
    			listEstado.add(k)
    			el_id = je.id
    		}
    		el_id = el_id + 1
    		System.out.println "id final = " + el_id
    	}else { 
    		System.println "error en restful"
    	}
    	for(Estado k : listEstado) {
    		System.out.println k.id + "-" + k.nombre
    	}
    	[miLista : listEstado, el_id: el_id]
    }
    @Transactional
    def guardar(){
    	RestResponse rResponse = new RestBuilder().post(urlRest + "Estado"){
    		accept('application/json')
    		contentType('application/json')
    		json(JsonOutput.toJson([id : params.el_id, nombre: params.nombre]))
    	}
    	if (rResponse.statusCode.value() == 201){
    		System.out.println "Estado creado en el servidor"
    	}
    	redirect(controller: "ConsumidorRestController", action: "index")
    }
    @Transactional
	def eliminar() {
		String urlDelete = urlRest + "Estado/" + params.id
		System.out.println urlDelete
		RestResponse rResponse = new RestBuilder().delete(urlDelete)
		System.out.println rResponse.statusCode.value()
		if(rResponse.statusCode.value == 204){
			System.out.println "Estado eliminado en el servidor"
		}
		redirect(controller: "ConsumidorRestController", action: "index")
	}
	def editar(){
		RestResponse rResponse = new RestBuilder().get(urlRest + "Estado/" + params.id)
		if ( rResponse.statusCode.value() == 200 && rResponse.json ) {
    		System.out.println rResponse.json
    		def m = rResponse.json
    		System.out.println m.id + " " + m.nombre
    			
    	}else { 
    		System.println "error en restful"
    	}
	}
}
