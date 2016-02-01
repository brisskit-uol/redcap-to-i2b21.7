package com.recomdata.redcap.ref

import com.recomdata.redcap.dm.Servers;
import grails.plugins.springsecurity.Secured;
import grails.converters.JSON

import com.recomdata.i2b2.I2B2ODMStudyHandlerCMLClient;
import com.recomdata.redcap.dm.Projects
import com.recomdata.redcap.dm.Servers;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Secured(['IS_AUTHENTICATED_FULLY'])
class RedcapProjectsController {

    def redcapProjectsService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
    	List<RedcapProjects> redcapProjectsInstanceList = redcapProjectsService.getAllRedcapProjects(params.id);
		if(redcapProjectsInstanceList==null || redcapProjectsInstanceList.size()==0) {
			println "Failed to retrieve Redcap projects!"
		} 
		
		return [redcapProjectsInstanceList: redcapProjectsInstanceList]
    }
	
	def process = {
		def serverId=params['serverId']
		redcapProjectsService.processRedcapProjects(serverId)
		redirect(action:"list", params:[id:serverId])
	}
	
	def restProcess = {
		def serverId=params['serverId']
		def xmlStatus=redcapProjectsService.processRedcapProjects(serverId)
		render xmlStatus as JSON
	}
	
	def save = {
		redcapProjectsService.toggleProjectSelection(params.projectId, params.serverId)
		render(text:"<status>success</status>",contentType:"application/xml",encoding:"UTF-8")
	}
	
	def saveToken = {
		redcapProjectsService.persistToken(params.projectId, params.serverId, params.token)
		render(text:"<status>success</status>",contentType:"application/xml",encoding:"UTF-8")
		
	}
	
}
