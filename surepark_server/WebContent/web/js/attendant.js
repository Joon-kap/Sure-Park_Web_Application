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
    var nowTime = now.getHours(2) + ":" + now.getMinutes(2);
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
window.onload = function() {                         // 페이지가 로딩되면 실행
	updateTimeHHMM();
	updateLED();
}

function webSocketRequest() {
	
}





