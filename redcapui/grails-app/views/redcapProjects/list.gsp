
<%@ page import="com.recomdata.redcap.ref.RedcapProjects" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'redcapProjects.label', default: 'RedcapProjects')}" />
        <title><g:message code="default.projects.label" args="${redcapProjectsInstanceList.serverName }" /></title>
    </head>
    <body>
    	<script>
	    	/**
	    	 * Register onClick handlers for all checkboxes.
	    	 */
	    	window.onload = function(){
	    		$('.projectId').each(function(index){
	    			this.onclick = toggleProjectSelection;
	    		});
	    		
	    		$('.projectTokens').each(function(index){
	    			this.onblur = persistToken;
	    		});
	    	}    	    		
    	</script>
    	<g:render template="/layouts/navigationbar" model="['page':'redcapProjects/list']" />
    	 <!-- <div class="nav" >		
			<span class="menuButton" style="float: left">
				<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
			</span>												
			<span class="menuButton" style="float: right">
				<g:link controller="password" >Change Password</g:link>
				<g:link controller="logout">Log off</g:link>				
			</span>				
			<span class="menuButton" ></span>		
        </div>-->
        <!--  
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link controller="logout">Log off</g:link></span>
        </div> -->
        <div class="body">
            <h1><a style="text-decoration:underline" href="../../servers/list">Redcap Servers List</a>  >  <g:message code="default.projects.label" args="${redcapProjectsInstanceList.serverName }" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            
            <g:form id='projectForm' action="process" method="post" >
            <div class="list">
                <table>
                    <thead>
                        <tr>       
                            <g:sortableColumn property="enabled" title="${message(code: 'redcapProjects.enabled.label', default: 'Choose Project')}" />
                            <g:sortableColumn property="projectID" title="${message(code: 'redcapProjects.projectID.label', default: 'Project ID')}" />
                            <g:sortableColumn property="projectName" title="${message(code: 'redcapProjects.projectName.label', default: 'Project Name')}" />
                            <g:sortableColumn property="token" title="${message(code: 'redcapProjects.token.label', default: 'Token')}" />
                            <g:sortableColumn property="status" title="${message(code: 'redcapProjects.status.label', default: 'Status')}" />
                            <g:sortableColumn property="lastProcessDate" title="${message(code: 'redcapProjects.lastProcessDate.label', default: 'Last Processed')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${redcapProjectsInstanceList}" status="i" var="redcapProjectsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        	<td><g:checkBox name="project${redcapProjectsInstance.projectID}" value="${redcapProjectsInstance.isEnabled}" class="projectId"></g:checkBox></td>
                                             
                            <td>${fieldValue(bean: redcapProjectsInstance, field: "projectID")}</td>
                      
                            <td>${fieldValue(bean: redcapProjectsInstance, field: "projectName")}</td>
                            
                            <td><g:textField name="token${redcapProjectsInstance.projectID}" value="${redcapProjectsInstance.token}" class="projectTokens"/></td>
                            <%--${fieldValue(bean: redcapProjectsInstance, field: "token") }--%>
                            
                            <td>${fieldValue(bean: redcapProjectsInstance, field: "status") }</td>
                            
                            <td>${fieldValue(bean: redcapProjectsInstance, field: "lastProcessDate") }</td>
                            
                            <g:hiddenField name="server${redcapProjectsInstance.projectID}" 
                            	value="${redcapProjectsInstance.projServerId}" />
                            
                        </tr>
                    </g:each>
                    <g:hiddenField name="serverId" value="${redcapProjectsInstanceList[0].projServerId}" />
                    </tbody>
                </table>
            </div>
             <div class="buttons">
                    <span style="align:left;" class="button"><g:submitButton name="submit" class="save" value="${message(code: 'default.button.submit.label', default: 'Export to i2b2')}" /></span>
             </div>
            </g:form>
        </div>
    </body>
</html>
