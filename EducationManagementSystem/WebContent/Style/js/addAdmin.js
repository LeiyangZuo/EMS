/**
 * check the process of adding administrator
 */

$(document).ready(function(){
        $('form').submit(function(event){
                if(!access())
                	event.preventDefault();
            }
        )
    }
);

function access(){
	var adminId = $('#adminId').val();
	var password1 = $('#password').val();
	var password2 = $('#password2').val();
	
	var errorMsg = "";
	var isPermit = true;
	if(!adminId || adminId === ""){
		errorMsg += "Please enter admin id <br \>";
		isPermit = false;
	}
	else if(!adminId.match(/^\d+$/)){
		errorMsg += "The format of this ID is wrong<br \>";
		isPermit = false;
	}
	
	if(!password1){
		errorMsg += "Please enter password <br \>";
		isPermit = false;
	}
	else if(!password2){
		errorMsg += "Please confirm password <br \>";
		isPermit = false;
	}
	else if(password1 !== password2){
		errorMsg += "The two password are not the same <br \>";
		isPermit = false;
	}
	
	if(isPermit === false){
		$('#errorContainer').show();
		$('#errorContainer').html(errorMsg);
	}
	
	return isPermit;
}