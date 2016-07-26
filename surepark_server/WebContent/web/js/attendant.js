var domainText = "192.168.1.6:8080";


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
        url: "http://"+domainText+"/surepark_server/sensor/getStatus.do",
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
	updateLED();
	init();
	send_message();
	UpdateSlots();
	
	
	selectLog = document.getElementById("logSelect");
	
	var objOption = document.createElement("option");        
	objOption.text = "Parking Lot Monitor Started	" + getLogTime();
	objOption.value = "aaaa";
	    
	selectLog.options.add(objOption);
	
	
		
	
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
		UpdateSlots();
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
	}
}
