//var domainText = "192.168.1.6:8080";
var domainText = "localhost:8080";
var fee15min = 0.25;		//15min per 25cent

var facilConf = new getConfigfromDB("22");
sleep(200);

var maxEntryGate = facilConf[0];
var maxExitGate = facilConf[1];
var maxPSlot = facilConf[2];

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
        type: "POST",
        url: "http://"+domainText+"/surepark_server/sensor/getStatus.do",
        callback: "callbak",
		dataType: "jsonp",
		async: "false",
		data:params,
        success: function(data){

        	$.each(data, function(k,v){
               	if(k=="ENTRY"){
              		 var led = document.getElementById("slot_entry");  
              	    console.log("led" + led);

            	    if(led==null) {
            	    	return;
            	    }
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
          	    console.log("led" + led);

        	    if(led==null) {
        	    	return;
        	    }
           		 if(v=='1') {
           			 led.style.color="red";
           			 led.innerHTML = "OPEN"
           			 var exitGate = document.getElementById("exit");
           			 exitGate.innerHTML = "CLOSED";
           		 } else {
           			 led.innerHTML = "BLOCKED";
           			 led.style.color="#00B050";
           		 }
           		console.log("LED1: " + v);
               	}	
               	
            	if(k=="LED1"){
            		 var led = document.getElementById("slot1_led");  
             	    console.log("led" + led);

            	    if(led==null) {
            	    	return;
            	    }
            	    
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
            	    console.log("led" + led);

            	    if(led==null) {
            	    	return;
            	    }
            	    
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
            	    console.log("led" + led);

            	    if(led==null) {
            	    	return;
            	    }
            	    
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
            	    console.log("led" + led);

            	    if(led==null) {
            	    	return;
            	    }
            	    
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



var now = new Date();
function updateTimeDD() {
    var clock = document.getElementById("clock_DD");            // 출력할 장소 선택
    var nowTime = ""+now.getDate();
    clock.innerHTML = nowTime;
}

var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "June",
                  "July", "Aug", "Sep", "Oct", "Nov", "Dec"];
function updateTimeMM() {
    var clock = document.getElementById("clock_MM");            // 출력할 장소 선택
    var nowTime = monthNames[now.getMonth()];
    clock.innerHTML = nowTime;           // 현재시간을 출력
//    clock.style.color = "green";
}

var dayofweek=["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

function updateTimeWEEK() {
    var clock = document.getElementById("clock_WEEK");            // 출력할 장소 선택
    var nowTime = dayofweek[now.getDay()];
    clock.innerHTML = nowTime;           // 현재시간을 출력
}

function updateTimeHHMM() {
    var clock = document.getElementById("clock_HHMM");            // 출력할 장소 선택
    now = new Date();                                                  // 현재시간
//  var nowTime = now.getFullYear() + "년" + (now.getMonth()+1) + "월" + now.getDate() + "일" + now.getHours() + "시" + now.getMinutes() + "분" + now.getSeconds() + "초";
    var hour = parseInt(now.getHours());
    var min = parseInt(now.getMinutes());
    if(hour < 10){
    	hour = "0"+hour;
    }
    if(min < 10){
    	min = "0"+min;
    }

    var nowTime = hour + ":" + min;
    clock.innerHTML = nowTime;           // 현재시간을 출력
    updateTimeDD();
    updateTimeMM();
    updateTimeWEEK();
    setTimeout("updateTimeHHMM()",60000);         // setTimeout(“실행할함수”,시간) 시간은1초의 경우 1000

    //ozit_interval_test();
}
/*aa
function updateLED()
*/

function updateLED() {
	GetSensorValue();

}
var selectLog;
window.onload = function() {                         // 페이지가 로딩되면 실행
	updateTimeHHMM();
	//getConfigfromDB("11");

	updateLED();
	init();
	send_message();
	UpdateSlots("onload");
	
	
	
	selectLog = document.getElementById("logSelect");
	
	var objOption = document.createElement("option");        
	objOption.text = "Parking Lot Monitor Started	" + getLogTime();
	objOption.value = "aaaa";
	    
	selectLog.options.add(objOption);
	
	
		
	
}

function ozit_interval_test(){
	$.ajax({
        type: "GET",
        url: "http://"+domainText+"/surepark_server/rev/updateGP.do",
        callback: "callbak",
		dataType: "jsonp",
	    success: function(data){
        	$.each(data, function(k,v){
               	
        	});
        	        	
        }
    });
	UpdateSlots("ozit_interval_test");
}

function webSocketRequest() {
	
}

function getLogTime(){
//21:00:11 07/20/2015
	
	var now = new Date(); // 현재시간
	var sec = parseInt(now.getSeconds());
	var min = parseInt(now.getMinutes());
	var hour = parseInt(now.getHours());
	var mon = parseInt((now.getMonth()+1));
	var day = parseInt(now.getDate());
	
	if(sec < 10)
		sec = "0" + sec;
	
	if(min < 10)
		min = "0" + min;
	
	if(hour < 10)
		hour = "0" + hour;
	
	if(mon < 10)
		mon = "0" + mon;
	
	if(day < 10)
		day = "0" + day;

    var nowTime = hour + ":" + min + ":" + sec +" " + mon + "/" + day + "/" + now.getFullYear();
    
    //	now.getFullYear() + "년" + (now.getMonth()+1) + "월" + now.getDate() + "일" + now.getHours() + "시" + now.getMinutes() + "분" +  + "초";
    return nowTime;
}

var wsUri = "ws://"+domainText+"/surepark_server/echo";

//var wsUri = "ws://128.237.200.150:8080/surepark_server/echo";

function init() {
    output = document.getElementById("output");
    
}
function send_message() {
    websocket = new WebSocket(wsUri);
    websocket.onopen = function(evt) {
        onOpen(evt)
    };
    websocket.onmessage = function(evt) {
        onMessage(evt)
    };
    websocket.onerror = function(evt) {
        onError(evt)
    };
}
function onOpen(evt) {
    writeToScreen("Connected to Endpoint!");
    doSend("test");
}
function onMessage(evt) {
	
//    writeToScreen("Message Received: " + evt.data);
	if(evt.data == '100'){
		GetSensorValue();
		
		
	}else if(evt.data == '001'){
		var entry = document.getElementById("entry");  
		entry.innerHTML = "OPENED";
	}else if(evt.data == '002'){
		var entry = document.getElementById("entry");  
		entry.innerHTML = "CLOSED";
	}else if(evt.data == '003'){
		var exit = document.getElementById("exit");  
		exit.innerHTML = "OPENED";
	}else if(evt.data == '004'){
		var exit = document.getElementById("exit");  
		exit.innerHTML = "CLOSED";
	}else if(evt.data == '200'){
		console.log(evt.data);
		UpdateSlots("onMessage");
	}
	
	updateLog(evt.data);

	
}
function onError(evt) {
    writeToScreen('ERROR: ' + evt.data);
}
function doSend(message) {
    writeToScreen("Message Sent: " + message);
    websocket.send(message);
    //websocket.close();
}
function writeToScreen(message) {
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
     
    //output.appendChild(pre);
}

function sleep(delay){
	var start = new Date().getTime();
	while(new Date().getTime() < start + delay);
}
//window.addEventListener("load", init, false);

function updateLog(evt){

	if(evt.indexOf("005") != -1){
		var str = evt.split("#");
		
		var objOption = document.createElement("option");        
		objOption.text = "Slot #"+str[1]+" Parked	" + getLogTime();
		objOption.value = "aaaa";
		    
		selectLog.options.add(objOption);
		
	}else if(evt.indexOf("006") != -1){
		var str = evt.split("#");
		
		var objOption = document.createElement("option");        
		objOption.text = "Slot #"+str[1]+" LEFT	" + getLogTime();
		objOption.value = "aaaa";
		    
		selectLog.options.add(objOption);
		
	}else if(evt == "007"){
		var objOption = document.createElement("option");        
		objOption.text = "Reallocated	" + getLogTime();
		objOption.value = "aaaa";
		    
		selectLog.options.add(objOption);
	}else if(evt == '008'){
		console.log(evt)
		var objOption = document.createElement("option");        
		objOption.text = "Reservation Canceled	" + getLogTime();
		objOption.value = "aaaa";
		
		selectLog.options.add(objOption);
		UpdateSlots("updateLog");
	}
}



function getConfigfromDB(temp){
	$.ajax({
        type: "POST",
        url: "http://"+domainText+"/surepark_server/attendant/selectAllConfigInfo.do",
        callback: "callbak",
		dataType: "jsonp",
		async: "false",
        success: function(data){
        	$.each(data, function(k,v){
            	if(k=="GETCONF"){
            		//alert("Success!!!");
            		maxEntryGate = v['P_ENTRY_QTY'];
            		maxExitGate = v['P_EXIT_QTY'];
            		maxPSlot = v['P_SLOT_QTY'];
            		console.log(temp);
            		console.log(maxEntryGate);
            		console.log(maxExitGate);
            		console.log(maxPSlot);

            		//SlotTableWrite(maxPSlot);
            		//P_ENTRY_QTY
            		//P_EXIT_QTY
        			//P_SLOT_QTY
            	    var rets = new Array();
            	    rets[0] = maxEntryGate;
            	    rets[1] = maxExitGate;
            	    rets[2] = maxPSlot;
            		
            		return rets;

            	}
            	
            	if(k=="fail"){
            		alert("실패");
            	}
        		
        	});
        	        	
        }
    });
}


function UpdateSlots(log){
	
	console.log(log);

	
	for(var i=1; i<5; i++){
		var stat = document.getElementById("slot"+i+"_stat");
	    var from = document.getElementById("slot"+i+"_from");
	    var during = document.getElementById("slot"+i+"_during");
	    var fee = document.getElementById("slot"+i+"_fee");
	    
	    console.log("maxPSlot" + maxPSlot);
	    if(maxPSlot==undefined) {
	    	return;
	    }
	    console.log("stat" + stat);

	    if(stat==null) {
	    	return;
	    }
	    stat.innerHTML = "# "+i+" : ";
	    from.innerHTML = "";
	    during.innerHTML = "";
	    fee.innerHTML = "";
	    
	}
	
	
	
	$.ajax({
        type: "POST",
        url: "http://"+domainText+"/surepark_server/rev/currentParkedStatus.do",
        callback: "callbak",
		dataType: "jsonp",
        success: function(data){
        	var slotnum=1;
        	$.each(data, function(k,v){
               	if(k=="LIST"){
               		for(i=0; i<v.length; i++) {
                  		console.log("LIST: " + v[0]['P_RESER_ID']);
                  	//	if((v[i]['P_CANCEL']!="N")&&(v[i]['P_PAYMENT']!="N")) {
                  			slotnum = v[i]['P_SPOT_NUMBER'];
                            var stat = document.getElementById("slot"+slotnum+"_stat");
                            var from = document.getElementById("slot"+slotnum+"_from");
                            var during = document.getElementById("slot"+slotnum+"_during");
                            var fee = document.getElementById("slot"+slotnum+"_fee");
                            
                            console.log("LIST len: " + "slot"+slotnum+"_stat");
                            console.log(v);
                            
                            //stat.innerHTML = "# "+slotnum+" : "+ v[i]['P_RESER_TELNO'];
                            stat.innerHTML = "# "+slotnum+" : ";

                            for(j=v[i]['P_RESER_TELNO'].length-4; j<v[i]['P_RESER_TELNO'].length; j++) {
                            	stat.innerHTML+=v[i]['P_RESER_TELNO'][j];
                            }
                            stat.style.fontWeight = "bold";

//                            from.innerHTML = "From : "+ v[i]['P_RESER_TIME'];
                            from.innerHTML = "From : ";
                            from.innerHTML+= v[i]['P_RESER_TIME'][4]+ v[i]['P_RESER_TIME'][5]+"/";
                            from.innerHTML+= v[i]['P_RESER_TIME'][6]+ v[i]['P_RESER_TIME'][7]+" ";
                           
                            from.innerHTML+= v[i]['P_RESER_TIME'][8]+ v[i]['P_RESER_TIME'][9]+":"+
                            					v[i]['P_RESER_TIME'][10]+ v[i]['P_RESER_TIME'][11];

                            from.style.fontWeight = "normal";

                            //var clock = ;            // 출력할 장소 선택
//                            during.innerHTML = "During : "+ v[i]['P_RESER_TIME'];
                            if(v[i]['P_ENTER_TIME'] == null) {
                            	during.innerHTML = "Reserved";
                                //during.innerHTML +=getDuring(v[i]['P_ENTER_TIME'])+" min";                           	
                            } else {
                            	during.innerHTML = "During : ";
                                during.innerHTML +=getDuring(v[i]['P_ENTER_TIME'])+" min";
                                //during.innerHTML += document.getElementById("clock_HHMM").innerHTML;
                                fee.innerHTML = "$ " + getFee(v[i]['P_ENTER_TIME']).toFixed(2);
                            }
                            during.style.fontWeight = "bold";
                  	//	}
               		}
              		console.log("LIST len: " + v.length);
					
              		/*
              		null({"LIST":[{"P_RESER_ID":114,"P_PAYMENT_YN":"N","P_CANCEL_YN":"N","P_SPOT_NUMBER":"3","P_ENTER_TIME":null,"P_UPDATE_DT":"2016-07-23 09:00:00","P_RESER_TIME":"201607231100","P_IDENTIFIER":"RT23223230ET201607231100","P_RESER_TELNO":"23223230","P_PAYMENT":null,"P_EXIT_TIME":null,"P_CREATE_DT":"2016-07-23 09:00:00"},
              		              {"P_RESER_ID":115,"P_PAYMENT_YN":"N","P_CANCEL_YN":"N","P_SPOT_NUMBER":"2","P_ENTER_TIME":null,"P_UPDATE_DT":null,"P_RESER_TIME":"201607231300","P_IDENTIFIER":"RT33223230ET201607231300","P_RESER_TELNO":"33223230","P_PAYMENT":null,"P_EXIT_TIME":null,"P_CREATE_DT":"2016-07-23 09:00:00"}]})*/
              		
                  	}
        	});
        	        	
        }
    });
}




function getDuring(fromTime) {
	var fromstr = fromTime[0]+fromTime[1]+fromTime[2]+fromTime[3]+"/"+	//YYYY
					fromTime[4]+fromTime[5]+"/"+	//MM
					fromTime[6]+fromTime[7]+" "+	//DD
					fromTime[8]+fromTime[9]+":"+	//HH
					fromTime[10]+fromTime[11];		//MM

	var diff = Math.abs(new Date(fromstr) - new Date());
		console.log("LIST len: " + fromTime + " : " + diff/60000+ " << " );
	return (diff/60000).toFixed(0);
}


function getFee(fromTime) {

	return (getDuring(fromTime)/15)*fee15min;
}



