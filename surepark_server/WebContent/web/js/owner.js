var domainText = "localhost:8080";
var T = new Array();
var T_MAX = 24;

function initData(){
	$.ajax({
        type: "POST",
        url: "http://"+domainText+"/surepark_server/owner/getConf.do",
        callback: "callbak",
		dataType: "jsonp",
        success: function(data){
        	console.log(data['P_EXIT_QTY']);
        	console.log(data['P_ENTRY_QTY']);
        	console.log(data['P_PERIOD_TIME']);
        	console.log(data['P_SLOT_QTY']);
        	        	
        	
        	var gp = document.getElementById("confGracePeriod");
        	var entry = document.getElementById("entry");
        	var exit = document.getElementById("exit");
        	var slot = document.getElementById("slot");
        	gp.value = data['P_PERIOD_TIME'];
        	entry.value = data['P_ENTRY_QTY'];
        	exit.value = data['P_EXIT_QTY'];
        	slot.value = data['P_SLOT_QTY'];
        }
    });
}

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

function dataPickerToString(picker){
	var strArr = picker.split("/");
	var fromStr = strArr[2] + strArr[0] + strArr[1];
	return fromStr;
}

function setConfig(){
	
	
	var gp = document.getElementById("confGracePeriod");
	var entry = document.getElementById("entry");
	var exit = document.getElementById("exit");
	var slot = document.getElementById("slot");
	
	var params = "gp="+gp.value+"&entry="+entry.value+"&exit="+exit.value+"&slot="+slot.value;
	
	
	$.ajax({
        type: "POST",
        url: "http://"+domainText+"/surepark_server/owner/setgp.do",
        callback: "callbak",
		dataType: "jsonp",
		data:params,
        success: function(data){
        	$.each(data, function(k,v){
        		console.log(v);
        	});
        	
        }
    });
	
	
}

function setFee(){
	var from = document.getElementById("selGpStartHour");
	var to = document.getElementById("selGpEndHour");
	
	var fromStr = dataPickerToString(from.value);
	var toStr = dataPickerToString(to.value);
	
	
	
	
	var params = "fromStr="+fromStr+"&toStr="+toStr;
	$.ajax({
        type: "POST",
        url: "http://"+domainText+"/surepark_server/owner/setFee.do",
        callback: "callbak",
		dataType: "jsonp",
		data:params,
        success: function(data){
        	$.each(data, function(k,v){
        		console.log(v);
        	});
        	
        }
    });
}

function DoAnalyze(){
	var from = document.getElementById("datepickerfrom");
	var to = document.getElementById("datepickerto");
	
	
	var fromStr = dataPickerToString(from.value);
	var toStr = dataPickerToString(to.value);
	var diff = toStr - fromStr;
	var wholeHour = diff * 24 * 60 * 4;
	console.log(wholeHour);
	
	var params = "fromDay="+fromStr+"&toDay="+toStr;
	var totalHour = 0;
	var totalPay = 0;
	var i=0;
	var spot_num1 = 0;
	var spot_num2 = 0;
	var spot_num3 = 0;
	var spot_num4 = 0;
	
	
	var spot1_time = 0;
	var spot2_time = 0;
	var spot3_time = 0;
	var spot4_time = 0;
	
	var j=0;
	var k=0;
	
	for(j=0; j<24; j++) {
		T[j] = 0;
	}
	
	
	$.ajax({
        type: "POST",
        url: "http://"+domainText+"/surepark_server/owner/getReserInfo.do",
        callback: "callbak",
		dataType: "jsonp",
		data:params,
        success: function(data){
        	$.each(data, function(k,v){
        		i++;
        		//console.log(k);
        		console.log(v);
        		//console.log(v['P_STAY_TIME']);
            	/*if(k=="TEST"){
            		alert("Success!!!");
            		console.log(v);
            		console.log(v['P_RESER_ID']);
            	}*/
        		if(v['P_STAY_TIME'] != null){
        		//	console.log("aaaa");
        			totalHour = totalHour + parseInt(v['P_STAY_TIME']);
        			if(v['P_SPOT_NUMBER'] == '1'){
            			spot1_time += parseInt(v['P_STAY_TIME']);
        			}else if(v['P_SPOT_NUMBER'] == '2'){
            			spot2_time += parseInt(v['P_STAY_TIME']);
        			}else if(v['P_SPOT_NUMBER'] == '3'){
            			spot3_time += parseInt(v['P_STAY_TIME']);
        			}else if(v['P_SPOT_NUMBER'] == '4'){
            			spot4_time += parseInt(v['P_STAY_TIME']);
        			}
        		}
        		
        		if(v['P_PAYMENT'] != null){
        			totalPay = totalPay + parseInt(v['P_PAYMENT']);
        		}
        		
        		if(v['P_SPOT_NUMBER'] != null){
        			if(v['P_SPOT_NUMBER'] == '1'){
        				spot_num1 = spot_num1+1;
        			}
        			if(v['P_SPOT_NUMBER'] == '2'){
        				spot_num2++;
        			}
        			if(v['P_SPOT_NUMBER'] == '3'){
        				spot_num3++;
        			}
        			if(v['P_SPOT_NUMBER'] == '4'){
        				spot_num4++;
        			}
        		}
        		//console.log(v['P_ENTER_TIME'][8]);
        		
        		if((v['P_ENTER_TIME']!=null)&&(v['P_EXIT_TIME']!=null)) {
        			
        			console.log(v['P_ENTER_TIME'][8]+v['P_ENTER_TIME'][9]);
        			var EThour = parseInt(v['P_ENTER_TIME'][8]+v['P_ENTER_TIME'][9]);	
                	var ETmin = parseInt(v['P_ENTER_TIME'][10]+v['P_ENTER_TIME'][11]);
                	
                	var EXhour = parseInt(v['P_EXIT_TIME'][8]+v['P_EXIT_TIME'][9]);
                	var EXmin = parseInt(v['P_EXIT_TIME'][10]+v['P_EXIT_TIME'][11]);
                	//for(var day=fromStr; day<toStr; day++) {
            		
            		//}
                	console.log(EThour);
                	console.log(EXhour);
                	
                	for(k=EThour; k<=EXhour; k++) {
                	
                		if(EThour==EXhour) {
                			T[k] += EXmin - ETmin; 
                		} else if(k == EThour) {
                			T[k] += 60 - ETmin;
                		} else if(k == EXhour) {
                			T[k] += EXmin;
                		} else {
                			T[k] += 60;
                		}
                		console.log("T["+k+"] : " + T[k]);
                	}
                		
        			
        		}
        		
        		
            	
            	        		
            	if(k=="fail"){
            		alert("실패");
            	}
            	console.log(i);
            	var hour = document.getElementById("totalHour");
            	hour.innerText = totalHour/60;
            	
            	var pay = document.getElementById("pay");
            	pay.innerText = totalPay;
            	
            	
            	
            	
        	});
        	
        	var slot1 = document.getElementById("slot1");
        	var slot2 = document.getElementById("slot2");
        	var slot3 = document.getElementById("slot3");
        	var slot4 = document.getElementById("slot4");
        	slot1.innerText = spot_num1;
        	slot2.innerText = spot_num2;
        	slot3.innerText = spot_num3;
        	slot4.innerText = spot_num4;
        	var slotTotal = document.getElementById("slotTotal");
        	slotTotal.innerText = spot_num1+spot_num2+spot_num3+spot_num4;
        	
        	var wholeDay = wholeHour/4;
        	
        	console.log(spot1_time);
        	console.log(spot2_time/wholeDay);
        	console.log(spot3_time/wholeDay);
        	console.log(spot4_time/wholeDay);
        	console.log(wholeDay);
        	
        	
        	var slot1 = document.getElementById("slot1Occ");
        	var slot2 = document.getElementById("slot2Occ");
        	var slot3 = document.getElementById("slot3Occ");
        	var slot4 = document.getElementById("slot4Occ");
        	
        	slot1.innerText = roundXL(spot1_time/wholeDay, 2);
        	slot2.innerText = roundXL(spot2_time/wholeDay, 2);
        	slot3.innerText = roundXL(spot3_time/wholeDay, 2);
        	slot4.innerText = roundXL(spot4_time/wholeDay, 2);
        	
        	console.log("T=" + T);
        	
        	for(i=0; i<24; i++){
        		var t1 = document.getElementById("t" + i);
        		
        		t1.innerText = T[i];
        	}
        	
        	
        }
    });
	
	
	
	
	
}

function roundXL(n, digits) {
	  if (digits >= 0) 
		  return parseFloat(n.toFixed(digits)); // 소수부 반올림

	  digits = Math.pow(10, digits); // 정수부 반올림
	  var t = Math.round(n * digits) / digits;

	  return parseFloat(t.toFixed(0));
}
	



var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
var test = function(i){ return T[i];}
var months = ["January","February","March","April","May","June","July", "August", "September", "October", "November", "December"];
var barChart = null;
var barChartData = {
//	labels : ["00~03","03~06","06~09","09~12","12~15","15~18","18~21","21~24"],
//	labels : ["00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"],
	labels : ["18", "19", "20", "21", "22", "23"],
	datasets : [
	/*
		{
			fillColor : "rgba(220,220,220,0.5)",
			strokeColor : "rgba(220,220,220,0.8)",
			highlightFill: "rgba(220,220,220,0.75)",
			highlightStroke: "rgba(220,220,220,1)",
			data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
		}
		,*/
		{
			fillColor : "rgba(151,187,205,0.5)",
			strokeColor : "rgba(151,187,205,0.8)",
			highlightFill : "rgba(151,187,205,0.75)",
			highlightStroke : "rgba(151,187,205,1)",
//			data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
//			data : [T[0],T[1],T[2],T[3],T[4],T[5],T[6],T[7],T[8],T[9],T[10],T[11],T[12],T[13],T[14],T[15],T[16],T[17],T[18],T[19],T[20],T[21],T[22],T[23]]
			data : [154,125,30,0,0,0]

		}
	]

};

function displayChart(){

	var ctx = document.getElementById("canvas").getContext("2d");
	barChart = new Chart(ctx).Bar(barChartData, {
		//Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
		scaleBeginAtZero : false,
		//Boolean - Whether grid lines are shown across the chart
		scaleShowGridLines : true,
		//String - Colour of the grid lines
		scaleGridLineColor : "rgba(0,0,0,0.05)",
		//Number - Width of the grid lines
		scaleGridLineWidth : 1,
		//Boolean - If there is a stroke on each bar
		barShowStroke : false,
		//Number - Pixel width of the bar stroke
		barStrokeWidth : 2,
		//Number - Spacing between each of the X value sets
		barValueSpacing : 5,
		//Number - Spacing between data sets within X values
		barDatasetSpacing : 1,
		onAnimationProgress: function() {
			console.log("onAnimationProgress");
		},
		onAnimationComplete: function() {
			console.log("onAnimationComplete");
		}
	});
}
/*
$(function() {
	var ctx = document.getElementById("canvas").getContext("2d");
	barChart = new Chart(ctx).Bar(barChartData, {
		//Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
		scaleBeginAtZero : false,
		//Boolean - Whether grid lines are shown across the chart
		scaleShowGridLines : true,
		//String - Colour of the grid lines
		scaleGridLineColor : "rgba(0,0,0,0.05)",
		//Number - Width of the grid lines
		scaleGridLineWidth : 1,
		//Boolean - If there is a stroke on each bar
		barShowStroke : false,
		//Number - Pixel width of the bar stroke
		barStrokeWidth : 2,
		//Number - Spacing between each of the X value sets
		barValueSpacing : 5,
		//Number - Spacing between data sets within X values
		barDatasetSpacing : 1,
		onAnimationProgress: function() {
			console.log("onAnimationProgress");
		},
		onAnimationComplete: function() {
			console.log("onAnimationComplete");
		}
	});
});
*/
/*
$("input#btnAdd").on("click", function() {
	barChart.addData(
		[randomScalingFactor(),randomScalingFactor()], 
		months[(barChart.datasets[0].bars.length)%12]
	);
});
 
$("canvas").on("click", function(e) {
	var activeBars = barChart.getBarsAtEvent(e);
	console.log(activeBars);

	for(var i in activeBars) {
		console.log(activeBars[i].value);
	}
});
*/


