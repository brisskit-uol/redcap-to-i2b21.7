<div class="nav" >		
	<span class="menuButton" style="float: left">
		<g:if test="${page == 'servers/list'}">
			<g:link class="create" action="create"><g:message code="default.new.label" args="['Server']" /></g:link>
		</g:if>
		<g:elseif test="${page.startsWith('projects/')}">
			<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
			<g:if test="${page == 'projects/show' || page == 'projects/edit'}">
				<g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link>
			</g:if>
			<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
		</g:elseif>
		<g:else>
			<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
		</g:else>		
	</span>												
	<span class="menuButton" style="float: right">		
		<g:link controller="password" >Change Password</g:link>		
		<g:link controller="logout">Log off</g:link>				
	</span>				
	<span class="menuButton" ></span>
</div>