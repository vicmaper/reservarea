package ulsazona.edu.mx

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import ulsazona.edu.mx.*

@Secured(['ROLE_ADMIN','ROLE_USUARIO'])
@Transactional(readOnly = false)


class SolicitudController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Solicitud.list(params), model:[solicitudCount: Solicitud.count()]
    }

    def show(Solicitud solicitud) {
        respond solicitud
    }

    def create() {
        respond new Solicitud(params)
    }

    @Transactional
    def save(Solicitud solicitud) {
        if (solicitud == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (solicitud.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond solicitud.errors, view:'create'
            return
        }

        solicitud.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitud.id])
                redirect solicitud
            }
            '*' { respond solicitud, [status: CREATED] }
        }
    }

    def edit(Solicitud solicitud) {
        respond solicitud
    }

    @Transactional
    def update(Solicitud solicitud) {
        if (solicitud == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (solicitud.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond solicitud.errors, view:'edit'
            return
        }

        solicitud.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitud.id])
                redirect solicitud
            }
            '*'{ respond solicitud, [status: OK] }
        }
    }

    @Transactional
    def delete(Solicitud solicitud) {

        if (solicitud == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        solicitud.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), solicitud.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
