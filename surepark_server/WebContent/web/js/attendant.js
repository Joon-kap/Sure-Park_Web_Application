var domainText = "localhost:8080";

function testButton(){
	var seq = "aaaa";
	var testData = "bbbb";
	var params = "seq="+seq+"&testdata="+testData;
	
	$.ajax({
        type: "POST",
        url: "http://"+domainText+"/surepark_server/att/test.do",
        callback: "callbak",
		dataType: "jsonp",
		data:params,
        success: function(data){
        	$.each(data, function(k,v){
            	if(k=="TEST"){
            		alert("Success!!!");
            		console.log(v);
            		console.log(v['P_RESER_ID']);
            	}
            	
            	if(k=="fail"){
            		alert("실패");
            	}
        		
        	});
        	        	
        }
    });
}




function GetSensorValue(){
	var params = "SENSORREQUEST="+"SENSORREQUEST";
	
	$.ajax({
        type: "GET",
        url: "http://"+domainText+"/surepark_server/sensor/changeStatus.do",
        callback: "callbak",
		dataType: "jsonp",
		data:params,
        success: function(data){
        	$.each(data, function(k,v){
               	if(k=="ENTRY"){
              		 var led = document.getElementById("slot_entry");  
              		 if(v=='1') {
              			 led.style.color="red";
	           			 led.innerHTML = "OPEN"
              		 } else {
               			 led.innerHTML = "BLOCKED"
              			 led.style.color="#00B050";
              		 }
              		console.log("LED1: " + v);
                  	}
        		if(k=="EXIT"){
           		 var led = document.getElementById("slot_exit");  
           		 if(v=='1') {
           			 led.style.color="red";
           			 led.innerHTML = "OPEN"
           		 } else {
           			 led.innerHTML = "BLOCKED"
           			 led.style.color="#00B050";
           		 }
           		console.log("LED1: " + v);
               	}
               	
            	if(k=="LED1"){
            		 var led = document.getElementById("slot1_led");  
            		 if(v=='1') {
            			 led.style.color="#00B050";
               			 led.innerHTML = "EMPTY";
            		 } else {
            			 led.style.color="black";
               			 led.innerHTML = "OCCUPIED";
            		 }
            		console.log("LED1: " + v);
            	}
            	if(k=="LED2"){
           		 	var led = document.getElementById("slot2_led");  
	           		 if(v=='1') {
	        			 led.style.color="#00B050";
	           			 led.innerHTML = "EMPTY";
	        		 } else {
	        			 led.style.color="black";
	           			 led.innerHTML = "OCCUPIED";
	        		 }
            		console.log("LED2: " + v);
            	}
            	if(k=="LED3"){
           		 	var led = document.getElementById("slot3_led");  
	           		 if(v=='1') {
	        			 led.style.color="#00B050";
	           			 led.innerHTML = "EMPTY";
	        		 } else {
	        			 led.style.color="black";
	           			 led.innerHTML = "OCCUPIED";
	        		 }
            		console.log("LED3: " + v);
            	}
            	if(k=="LED4"){
           		 	var led = document.getElementById("slot4_led");  
	           		 if(v=='1') {
	        			 led.style.color="#00B050";
	           			 led.innerHTML = "EMPTY";
	        		 } else {
	        			 led.style.color="black";
	           			 led.innerHTML = "OCCUPIED";
	        		 }
            		console.log("LED4: " + v);
            	}
        	});
        	        	
        }
    });
}









function meetRequest(buyCarSeq, sellCarSeq,  dealMain, userSeq){
	var params = "buyCarSeq="+buyCarSeq+"&sellCarSeq="+sellCarSeq+"&dealMain="+dealMain+"&userSeq="+userSeq;
	
	$.ajax({
        type: "POST",
        url: "http://"+doamainText+"/infobee_car_server/trasnaction/meetRequest.do",
        callback: "callbak",
		dataType: "jsonp",
		data:params,
        success: function(data){
        	$.each(data, function(k,v){
            	if(k=="success"){
            		$.ajax({
            			type: "get",
           	            url: "../../email/b_meet_email.html",
           	            callback: "callbak",
            	    	dataType: "html",
            	        success: function(html){
            	        	            	        	            	        	
            	        }
            			
            		});

            		$.openDOMWindow({         
                		windowSourceID:'#traConfirm',
                		height:200, 
                		width:500,
                		overlay:1,
                		borderSize:'0',
                		windowSource:'inline',
                		overlayColor:'#fff',
                		overlayOpacity:'55'
                	});
            	}
            	
            	if(k=="fail"){
            		alert("실패");
            	}
        		
        	});
        		
        	

        	
        	
        	
        }
    });
}