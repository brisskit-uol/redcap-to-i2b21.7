var Ajax;
if (Ajax && (Ajax != null)) {
	Ajax.Responders.register({
	  onCreate: function() {
        if($('spinner') && Ajax.activeRequestCount>0)
          Effect.Appear('spinner',{duration:0.5,queue:'end'});
	  },
	  onComplete: function() {
        if($('spinner') && Ajax.activeRequestCount==0)
          Effect.Fade('spinner',{duration:0.5,queue:'end'});
	  }
	});
}

/**
 * Register onClick handlers for all checkboxes.
 */
/*window.onload = function(){
	$('.projectId').each(function(index){
		this.onclick = toggleProjectSelection;
	});
	
	$('.projectTokens').each(function(index){
		this.onblur = persistToken;
	});
}*/

var toggleProjectSelection = function(){
	var projectId = this.id.substr(7) ;
	var serverId = $('#server'+projectId).val();
	$.post("../save", {projectId:projectId, serverId:serverId}, function(data){
	});
}

var persistToken = function(){
	var projectId = this.id.substr(5);
	var serverId = $('#server'+projectId).val();
	var token = this.value;
	$.post("../saveToken", {projectId:projectId, serverId:serverId, token:token}, function(data){
		
	});
}
