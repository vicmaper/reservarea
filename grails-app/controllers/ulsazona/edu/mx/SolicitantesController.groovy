package ulsazona.edu.mx

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import ulsazona.edu.mx.*

@Secured('ROLE_ADMIN')
@Transactional(readOnly = false)


class SolicitantesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Solicitantes.list(params), model:[solicitantesCount: Solicitantes.count()]
    }

    def show(Solicitantes solicitantes) {
        respond solicitantes
    }

    def create() {
        respond new Solicitantes(params)
    }

    @Transactional
    def save(Solicitantes solicitantes) {
        if (solicitantes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (solicitantes.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond solicitantes.errors, view:'create'
            return
        }

        solicitantes.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'solicitantes.label', default: 'Solicitantes'), solicitantes.id])
                redirect solicitantes
            }
            '*' { respond solicitantes, [status: CREATED] }
        }
    }

    def edit(Solicitantes solicitantes) {
        respond solicitantes
    }

    @Transactional
    def update(Solicitantes solicitantes) {
        if (solicitantes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (solicitantes.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond solicitantes.errors, view:'edit'
            return
        }

        solicitantes.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitantes.label', default: 'Solicitantes'), solicitantes.id])
                redirect solicitantes
            }
            '*'{ respond solicitantes, [status: OK] }
        }
    }

    @Transactional
    def delete(Solicitantes solicitantes) {

        if (solicitantes == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        solicitantes.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitantes.label', default: 'Solicitantes'), solicitantes.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitantes.label', default: 'Solicitantes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
