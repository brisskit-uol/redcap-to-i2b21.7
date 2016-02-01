
<%@ page import="com.recomdata.redcap.dm.Projects" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projects.label', default: 'Projects')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
    	<g:render template="/layouts/navigationbar" model="['page':'projects/list']" />
        <!-- <div class="nav">
            <span class="menuButton" style="float: left">
            	<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
            	<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
            </span>
            <span class="menuButton" style="float: right">
				<g:link controller="password" >Change Password</g:link>
				<g:link controller="logout">Log off</g:link>				
			</span>
        </div> -->
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'projects.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="projectID" title="${message(code: 'projects.projectID.label', default: 'Project ID')}" />
                            
                            <g:sortableColumn property="serverName" title="${message(code: 'projects.serverName.label', default: 'Web Service Server')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${projectsInstanceList}" status="i" var="projectsInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${projectsInstance.id}">${fieldValue(bean: projectsInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: projectsInstance, field: "projectID")}</td>
                            
                            <td>${fieldValue(bean: projectsInstance, field: "serverName")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <g:hiddenField name="bUrl" value="${fieldValue(bean:projectInstance, field:'projServer')}"/>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${projectsInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
