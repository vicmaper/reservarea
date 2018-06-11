package ulsazona.edu.mx

import grails.test.mixin.*
import spock.lang.*

@TestFor(SolicitudController)
@Mock(Solicitud)
class SolicitudControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.solicitudList
            model.solicitudCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.solicitud!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def solicitud = new Solicitud()
            solicitud.validate()
            controller.save(solicitud)

        then:"The create view is rendered again with the correct model"
            model.solicitud!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            solicitud = new Solicitud(params)

            controller.save(solicitud)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/solicitud/show/1'
            controller.flash.message != null
            Solicitud.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def solicitud = new Solicitud(params)
            controller.show(solicitud)

        then:"A model is populated containing the domain instance"
            model.solicitud == solicitud
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def solicitud = new Solicitud(params)
            controller.edit(solicitud)

        then:"A model is populated containing the domain instance"
            model.solicitud == solicitud
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/solicitud/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def solicitud = new Solicitud()
            solicitud.validate()
            controller.update(solicitud)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.solicitud == solicitud

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            solicitud = new Solicitud(params).save(flush: true)
            controller.update(solicitud)

        then:"A redirect is issued to the show action"
            solicitud != null
            response.redirectedUrl == "/solicitud/show/$solicitud.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/solicitud/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def solicitud = new Solicitud(params).save(flush: true)

        then:"It exists"
            Solicitud.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(solicitud)

        then:"The instance is deleted"
            Solicitud.count() == 0
            response.redirectedUrl == '/solicitud/index'
            flash.message != null
    }
}
