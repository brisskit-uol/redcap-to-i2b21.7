

<%@ page import="com.recomdata.redcap.dm.Projects" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'projects.label', default: 'Projects')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
    	<g:render template="/layouts/navigationbar" model="['page':'redcapProjects/list']" />
        <!-- <div class="nav">
            <span class="menuButton" style="float: left">
            	<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
            	<g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link>
            	<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
            </span>
            <span class="menuButton" style="float: right">
            	<g:link controller="password" >Change Password</g:link>
				<g:link controller="logout">Log off</g:link>
            </span>
            <span class="menuButton"></span>
        </div> -->
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${projectsInstance}">
            <div class="errors">
                <g:renderErrors bean="${projectsInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${projectsInstance?.id}" />
                <g:hiddenField name="version" value="${projectsInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="projectID"><g:message code="projects.projectID.label" default="Project ID" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: projectsInstance, field: 'projectID', 'errors')}">
                                    <g:textField name="projectID" value="${projectsInstance?.projectID}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
