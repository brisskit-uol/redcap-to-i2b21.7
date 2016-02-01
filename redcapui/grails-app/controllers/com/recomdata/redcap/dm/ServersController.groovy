package com.recomdata.redcap.dm

import grails.plugins.springsecurity.Secured;

import com.recomdata.i2b2.I2B2ODMStudyHandlerCMLClient;

@Secured(['IS_AUTHENTICATED_FULLY'])
class ServersController {

	def odmService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [serversInstanceList: Servers.list(params), serversInstanceTotal: Servers.count()]
    }

    def create = {
        def serversInstance = new Servers()
        serversInstance.properties = params
        return [serversInstance: serversInstance]
    }

    def save = {
        def serversInstance = new Servers(params)
        if (serversInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'servers.label', default: 'Servers'), serversInstance.id])}"
            redirect(action: "show", id: serversInstance.id)
        }
        else {
            render(view: "create", model: [serversInstance: serversInstance])
        }
    }

    def show = {
        def serversInstance = Servers.get(params.id)
        if (!serversInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'servers.label', default: 'Servers'), params.id])}"
            redirect(action: "list")
        }
        else {
            [serversInstance: serversInstance]
        }
    }

    def edit = {
        def serversInstance = Servers.get(params.id)
        if (!serversInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'servers.label', default: 'Servers'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [serversInstance: serversInstance]
        }
    }

    def update = {
        def serversInstance = Servers.get(params.id)
        if (serversInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (serversInstance.version > version) {
                    
                    serversInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'servers.label', default: 'Servers')] as Object[], "Another user has updated this Servers while you were editing")
                    render(view: "edit", model: [serversInstance: serversInstance])
                    return
                }
            }
            serversInstance.properties = params
            if (!serversInstance.hasErrors() && serversInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'servers.label', default: 'Servers'), serversInstance.id])}"
                redirect(action: "show", id: serversInstance.id)
            }
            else {
                render(view: "edit", model: [serversInstance: serversInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'servers.label', default: 'Servers'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def serversInstance = Servers.get(params.id)
        if (serversInstance) {
            try {
                serversInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'servers.label', default: 'Servers'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'servers.label', default: 'Servers'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'servers.label', default: 'Servers'), params.id])}"
            redirect(action: "list")
        }
    }
	
	def importOdm = {
		odmService.importOdm()
		redirect(action:"list")
	}
}
