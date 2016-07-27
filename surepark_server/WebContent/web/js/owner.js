var domainText = "localhost:8080";


function conf1Apply(){
	
	//var graceperi = confGracePeriod.innerHTML;
	var graceperi = document.getElementById("confGracePeriod");
	
	var fee25min = confFeeper15min.innerHTML;
	var fee25min = document.getElementById("confFeeper15min");

	var params = "P_GRACEPERI_MIN="+graceperi.value;//+"&testdata="+testData;
	params+="&P_F00="	+fee25min.value;
	params+="&P_F01="	+fee25min.value;
	params+="&P_F02="	+fee25min.value;
	params+="&P_F03="	+fee25min.value;
	params+="&P_F04="	+fee25min.value;
	params+="&P_F05="	+fee25min.value;
	params+="&P_F06="	+fee25min.value;
	params+="&P_F07="	+fee25min.value;
	params+="&P_F08="	+fee25min.value;
	params+="&P_F09="	+fee25min.value;
	params+="&P_F10="	+fee25min.value;
	params+="&P_F11="	+fee25min.value;
	params+="&P_F12="	+fee25min.value;
	params+="&P_F13="	+fee25min.value;
	params+="&P_F14="	+fee25min.value;
	params+="&P_F15="	+fee25min.value;
	params+="&P_F16="	+fee25min.value;
	params+="&P_F17="	+fee25min.value;
	params+="&P_F18="	+fee25min.value;
	params+="&P_F19="	+fee25min.value;
	params+="&P_F20="	+fee25min.value;
	params+="&P_F21="	+fee25min.value;
	params+="&P_F22="	+fee25min.value;
	params+="&P_F23="	+fee25min.value;

	
	$.ajax({
        type: "POST",
        url: "http://"+domainText+"/surepark_server/owner/updateConf.do",
        callback: "callbak",
		dataType: "jsonp",
		data:params,
        success: function(data){
        	$.each(data, function(k,v){
        		console.log(k);
        		console.log(v);
            	/*if(k=="TEST"){
            		alert("Success!!!");
            		console.log(v);
            		console.log(v['P_RESER_ID']);
            	}*/
            	
            	if(k=="fail"){
            		alert("실패");
            	}
        		
        	});
        	        	
        }
    });
}

function conf2Apply(){
	
	//var graceperi = confGracePeriod.innerHTML;
	var confEntrygate = document.getElementById("confEntrygate");
	var confExitgate = document.getElementById("confExitgate");
	var confpakringslots = document.getElementById("confpakringslots");



	var params = "P_ENTRY_QTY="+confEntrygate.value;//+"&testdata="+testData;
	params+="&P_EXIT_QTY="	+confExitgate.value;
	params+="&P_SLOT_QTY="	+confpakringslots.value;


	$.ajax({
        type: "POST",
        url: "http://"+domainText+"/surepark_server/owner/updatefacil.do",
        callback: "callbak",
		dataType: "jsonp",
		data:params,
        success: function(data){
        	$.each(data, function(k,v){
        		console.log(k);
        		console.log(v);
            	/*if(k=="TEST"){
            		alert("Success!!!");
            		console.log(v);
            		console.log(v['P_RESER_ID']);
            	}*/
            	
            	if(k=="fail"){
            		alert("실패");
            	}
        		
        	});
        	        	
        }
    });
}
$( function() {
	$( "#datepickerfrom" ).datepicker();
} );


$( function() {
  $( "#datepickerto" ).datepicker();
  //$('#pickertype').text("Select Day");
} );

function DoAnalyze(){
	var confpakringslots = document.getElementById("confpakringslots");
}

