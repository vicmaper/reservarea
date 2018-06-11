package ulsazona.edu.mx

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import ulsazona.edu.mx.*

@Secured('ROLE_ADMIN')
@Transactional(readOnly = false)


class SalonesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Salones.list(params), model:[salonesCount: Salones.count()]
    }

    def show(Salones salones) {
        respond salones
    }

    def create() {
        respond new Salones(params)
    }

    @Transactional
    def save(Salones salones) {
        if (salones == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (salones.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond salones.errors, view:'create'
            return
        }

        salones.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'salones.label', default: 'Salones'), salones.id])
                redirect salones
            }
            '*' { respond salones, [status: CREATED] }
        }
    }

    def edit(Salones salones) {
        respond salones
    }

    @Transactional
    def update(Salones salones) {
        if (salones == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (salones.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond salones.errors, view:'edit'
            return
        }

        salones.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'salones.label', default: 'Salones'), salones.id])
                redirect salones
            }
            '*'{ respond salones, [status: OK] }
        }
    }

    @Transactional
    def delete(Salones salones) {

        if (salones == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        salones.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'salones.label', default: 'Salones'), salones.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'salones.label', default: 'Salones'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
