<%@ page import="com.recomdata.redcap.dm.Servers" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'changePassword.label', default: 'Change Password')}" />
        <title><g:message code="default.list.label" args="['Change Password']" /></title>
    </head>
    <body>
    	<g:render template="/layouts/navigationbar" model="['page':'password/changepassword']" />    	
        <!--<div class="nav">
           <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="['Server']" /></g:link></span>
           <span class="menuButton"><g:link controller="logout">Log off</g:link></span>		   
        </div>-->
        <div class="body">
            <h1><g:message code="default.change.password.label" args="['Change Password']" default="Change Password"/> </h1>
            <g:if test="${flash.message}">
            <div class="errors">${flash.message}</div>
            </g:if>
			<g:form>
			<table>
				<tbody>
					<tr  class="prop">
						<td class="name">Current Password: </td>
						<td class="value"><input type='password' class='text_' name='currentPassword' id='currentPassword'/></td>						
					</tr>
					<tr  class="prop"> 
						<td class="name">New Password: </td>
						<td class="value"><input type='password' class='text_' name='newPassword' id='newPassword'/></td>
					</tr>
					<tr>
						<td>Confirm New Password: </td>
						<td><input type='password' class='text_' name='confirmNewPassword' id='confirmNewPassword'/></td>
					</tr>
				</tbody>
			</table>
			
            <div class="buttons">
            	<span style="align=right;" class="button">            		
            		<g:actionSubmit value="Change" controller="password" action="save" />            		
            	</span>
            </div>
			</g:form>
        </div>
    </body>
</html>
