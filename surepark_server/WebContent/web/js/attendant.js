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
            	}
            	
            	if(k=="fail"){
            		alert("실패");
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