
/**
 * check the process of adding student
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
	var studentId = $('#studentId').val();
	var password1 = $('#password').val();
	var password2 = $('#password2').val();
	var studentName = $('#studentName').val();
	var errorMsg = "";
	var isPermit = true;
	if(!studentId || studentId === ""){
		errorMsg += "Please enter student id <br \>";
		isPermit = false;
	}
	else if(!studentId.match(/^\d+$/)){
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
	
	if(!studentName || studentName === null){
		errorMsg += "Please enter student name <br \>";
		isPermit = false;
	}
	
	if(isPermit === false){
		$('#errorContainer').show();
		$('#errorContainer').html(errorMsg);
	}
	
	return isPermit;
}