
<%@ page import="com.recomdata.redcap.dm.Servers" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'servers.label', default: 'Servers')}" />
        <title><g:message code="default.list.label" args="['Redcap Servers']" /></title>
    </head>
    <body>        
        <g:render template="/layouts/navigationbar" model="['page':'servers/list']" />
        <div class="body">
            <h1><g:message code="default.list.label" args="['Redcap Servers']" /> </h1>
            Click server name to go to projects list page
            <g:if test="${flash.message}">
            <div class="message">${flash.message} </div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'servers.id.label', default: 'Id')}" />
                            
                            <g:sortableColumn property="name" title="${message(code: 'servers.name.label', default: 'Name')}" />
                        
                            <g:sortableColumn property="baseURL" title="${message(code: 'servers.baseURL.label', default: 'Base URL')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${serversInstanceList}" status="i" var="serversInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${serversInstance.id}">${fieldValue(bean: serversInstance, field: "id")}</g:link></td>
                            
                            <td><g:link controller="redcapProjects" action="list" params="[id: serversInstance.id]">${fieldValue(bean: serversInstance, field: "name")} </g:link></td>
                            
                           <!--  <td>${fieldValue(bean: serversInstance, field: "name")}</td> -->
                        
                            <td>${fieldValue(bean: serversInstance, field: "baseURL")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${serversInstanceTotal}" />
            </div>
            <div class="buttons">
            	<g:link controller="odm" action="show">Import Odm Folder</g:link>
            																	
            	<!--  <span style="align=right;" class="button">
            		<g:form>
            			<g:actionSubmit value="Import Odm Folder" controller="odm" action="show" />
            		</g:form>
            	</span> -->
            </div>
        </div>
    </body>
</html>
