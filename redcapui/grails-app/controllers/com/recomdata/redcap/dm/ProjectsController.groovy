package com.recomdata.redcap.dm

import grails.plugins.springsecurity.Secured;
@Secured(['IS_AUTHENTICATED_FULLY'])
class ProjectsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [projectsInstanceList: Projects.list(params), projectsInstanceTotal: Projects.count()]
    }

    def create = {
        def projectsInstance = new Projects()
        projectsInstance.properties = params
        return [projectsInstance: projectsInstance]
    }

    def save = {
        def projectsInstance = new Projects(params)
        if (projectsInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'projects.label', default: 'Projects'), projectsInstance.id])}"
            redirect(action: "show", id: projectsInstance.id)
        }
        else {
            render(view: "create", model: [projectsInstance: projectsInstance])
        }
    }

    def show = {
        def projectsInstance = Projects.get(params.id)
        if (!projectsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projects.label', default: 'Projects'), params.id])}"
            redirect(action: "list")
        }
        else {
            [projectsInstance: projectsInstance]
        }
    }

    def edit = {
        def projectsInstance = Projects.get(params.id)
        if (!projectsInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projects.label', default: 'Projects'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [projectsInstance: projectsInstance]
        }
    }

    def update = {
        def projectsInstance = Projects.get(params.id)
        if (projectsInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (projectsInstance.version > version) {
                    
                    projectsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'projects.label', default: 'Projects')] as Object[], "Another user has updated this Projects while you were editing")
                    render(view: "edit", model: [projectsInstance: projectsInstance])
                    return
                }
            }
            projectsInstance.properties = params
            if (!projectsInstance.hasErrors() && projectsInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'projects.label', default: 'Projects'), projectsInstance.id])}"
                redirect(action: "show", id: projectsInstance.id)
            }
            else {
                render(view: "edit", model: [projectsInstance: projectsInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projects.label', default: 'Projects'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def projectsInstance = Projects.get(params.id)
        if (projectsInstance) {
            try {
                projectsInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'projects.label', default: 'Projects'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'projects.label', default: 'Projects'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'projects.label', default: 'Projects'), params.id])}"
            redirect(action: "list")
        }
    }
}
