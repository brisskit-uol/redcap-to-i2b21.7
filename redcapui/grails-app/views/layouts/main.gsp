<html>
<head>
<title><g:layoutTitle default="Grails" /></title>
<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
<link rel="shortcut icon"
	href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
<g:layoutHead />
<g:javascript library="application" />
<g:javascript src="jQuery/jquery-1.7.js" />
</head>
<body>
  <div style="width: 100%; background-color: #00529B;">
		<img style="padding: 5px 5px;" src="${resource(dir:'images',file:'logoimage_reverse.gif')}" border="0" height="41" align="middle" />
		<span class="title" style="color: #eee;">REDCap to i2b2 Loader</span>
  </div>			
	<div id="spinner" class="spinner" style="display: none;">
		<img src="${resource(dir:'images',file:'spinner.gif')}"
			alt="${message(code:'spinner.alt',default:'Loading...')}" />
	</div>
	<g:layoutBody />
</body>
</html>