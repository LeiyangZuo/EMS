/**
 * check the process of adding teacher
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
	var teacherId = $('#teacherId').val();
	var password1 = $('#password').val();
	var password2 = $('#password2').val();
	var name = $('#teacherName').val();
	var errorMsg = "";
	var isPermit = true;
	if(!teacherId || teacherId === ""){
		errorMsg += "Please enter teacher id <br \>";
		isPermit = false;
	}
	else if(!teacherId.match(/^\d+$/)){
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
	
	if(!name){
		errorMsg += "Please enter teacher name <br \>";
		isPermit = false;
	}
	
	if(isPermit === false){
		$('#errorContainer').show();
		$('#errorContainer').html(errorMsg);
	}
	
	return isPermit;
}