function validate(){
	let x = document.forms["myForm"]["code"].value;
	while(x != "[a-zA-z ]+"){
		alert("Check your input");
	}
}

function prova(){
	let x = document.forms["myForm"]["code"].value;
	
	dates.compare()
	
	let x = new Date()
	
	if(!/[a-zA-z ]+/.test(x)){
		alert("Check your input");
		
		return false;
	}
}

function test(){
	let x = document.forms["myForm"]["code"].value;
	x.prototype.isValid = function(){
		return /[a-zA-z ]+/.test(x);
	}
	
	document.getElementById('codeV').innerHTML = x.isValid();
}


function myFunc(){
	var start = document.getElementById('startDate').value;
	var end = document.getElementById('endDate').value;
	
	var strt = Date.parse(start);
	var nd = Date.parse(end);
	
	var yearStrt  = strt.getFullYear();
	var ndYear  = nd.getFullYear();
	
	if(yearStrt< ndYear){
		alert("Check dates!");
		return false;
	}
}