window.onload = function(){     
	onLoad();
};

function onLoad(){
	var mainForm = document.forms[0];
	
	if(typeof(mainForm)==='undefined'){
		return;
	}
	if(mainForm.name == 'navForm' || mainForm.name == 'loginform'){
		mainForm = document.forms[1];
		if(typeof(mainForm)!='undefined'){
			//setFocus(mainForm);
		}
	}else{
		
		//setFocus(mainForm);
	}
}

function setFocus(formName){
	var firstInput= formName.elements[1];
	try{
		if(firstInput.name.indexof('beginningDate_input')!=-1){
			return;
		}
	}catch(er){

	}
	if(firstInput.type=="text" || firstInput.type=="textarea" )
	{
		try{
			firstInput.focus();
			var val = firstInput.value;
			firstInput.value="";
			firstInput.value = val;
		}catch(er){

		}
	}
}

function getDiv(formname,divname){
	return document.getElementById(formname+":"+divname);
}
function localloading(divname,msDivname){
	document.getElementById(divname).style.display = "block";
	document.getElementById(msDivname).innerHTML="";
}

function unlocalloading(divname){
	document.getElementById(divname).style.display = "none";
}


function loadDialog(formname,dialog){
	
	var mainDiv = getDiv(formname,"mainDiv");
	var processDiv = getDiv(formname,"processDiv");
	var statusDiv = getDiv(formname,"statusDiv");

	mainDiv.style.display = "none";
	processDiv.style.display = "block";
	statusDiv.style.display = "none";
	
	if(typeof(dialog)==='undefined'){
		eval(formname+"Dialog.show()");
	}
}

function changeDialog(formname,prediv,nextdiv){
	var preMainDiv = getDiv(formname,prediv);
	var nextMainDiv = getDiv(formname,nextdiv);	
	var processDiv = getDiv(formname,"processDiv");
	var statusDiv = getDiv(formname,"statusDiv");
	
	nextMainDiv.style.display = "block";
	preMainDiv.style.display = "none";
	processDiv.style.display = "none";
	statusDiv.style.display = "none";
	
	setFocus(document.getElementById(formname));
}


function openDialog(formname,dialog){
	var mainDiv = getDiv(formname,"mainDiv");
	var processDiv = getDiv(formname,"processDiv");
	var statusDiv = getDiv(formname,"statusDiv");
	
	mainDiv.style.display = "block";
	processDiv.style.display = "none";
	statusDiv.style.display = "none";
	
	if(typeof(dialog)==='undefined'){
		eval(formname+"Dialog.show()");
	}

	setFocus(document.getElementById(formname));
}


function openOverlay(formname,dialog){
	eval(formname+"Overlay.show()");
}

function closeOverlay(xhr, status, args,formname){
	eval(formname+"Overlay.hide()");
	
	if(!args.validationFailed){
		statusDialog(formname);
	}
}

function processDialog(formname,dialog){
	

	var mainDiv = getDiv(formname,"mainDiv");
	var processDiv = getDiv(formname,"processDiv");
	var statusDiv = getDiv(formname,"statusDiv");
	
	mainDiv.style.display = "none";
	processDiv.style.display = "block";
	statusDiv.style.display = "none";
	if(typeof(dialog)!='undefined'){
		mainDiv = getDiv(formname,dialog);
		mainDiv.style.display = "none";
	}

}

function statusDialog(formname,dialog){

	var mainDiv = getDiv(formname,"mainDiv");
	var processDiv = getDiv(formname,"processDiv");
	var statusDiv = getDiv(formname,"statusDiv");
	
	mainDiv.style.display = "none";
	processDiv.style.display = "none";
	statusDiv.style.display = "block";
	
	if(typeof(dialog)!='undefined'){
		mainDiv = getDiv(formname,dialog);
		mainDiv.style.display = "none";
	}	

}

function closeDialog(formname,dialog){
	
	var mainDiv = getDiv(formname,"mainDiv");
	var processDiv = getDiv(formname,"processDiv");
	var statusDiv = getDiv(formname,"statusDiv");
	
	mainDiv.style.display = "none";
	processDiv.style.display = "none";
	statusDiv.style.display = "none";
	
	if(typeof(dialog)==='undefined'){
		eval(formname+"Dialog.hide()");
	}
	
}

function handleAjaxRequest(xhr, status, args){             
	var xmlDoc = xhr.responseXML;            
	errorNodes = xmlDoc.getElementsByTagName('error-name'); 
	alert(status);
	if (errorNodes.length == 0) return;             
	errorName = errorNodes[0].childNodes[0].nodeValue;         
	
	switch (errorName) {                 
	case 'class javax.faces.application.ViewExpiredException':                      
		alert ('Session expired, redirecting to login page!');                      
		window.location.href = 'login.xhtml';                
		break;             
	}         
}
