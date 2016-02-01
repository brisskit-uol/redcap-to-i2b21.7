

<%@ page import="com.recomdata.redcap.dm.Servers" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'servers.label', default: 'Servers')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
    	<g:render template="/layouts/navigationbar" model="['page':'servers/create']" />    	 
        <!-- <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link controller="logout">Log off</g:link></span>
        </div>  -->
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${serversInstance}">
            <div class="errors">
                <g:renderErrors bean="${serversInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="baseURL"><g:message code="servers.baseURL.label" default="Base URL" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: serversInstance, field: 'baseURL', 'errors')}">
                                    <g:textField name="baseURL" value="${serversInstance?.baseURL}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="servers.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: serversInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${serversInstance?.name}" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
