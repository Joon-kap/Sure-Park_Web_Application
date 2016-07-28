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

function dataPickerToString(picker){
	var strArr = picker.split("/");
	var fromStr = strArr[2] + strArr[0] + strArr[1];
	return fromStr;
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
	var i=0;
	
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
        		//console.log(v);
        		console.log(v['P_STAY_TIME']);
            	/*if(k=="TEST"){
            		alert("Success!!!");
            		console.log(v);
            		console.log(v['P_RESER_ID']);
            	}*/
        		if(v['P_STAY_TIME'] != null){
        			console.log("aaaa");
        			totalHour = totalHour + parseInt(v['P_STAY_TIME']);
        		}
            	
            	if(k=="fail"){
            		alert("실패");
            	}
            	console.log(i);
            	var hour = document.getElementById("totalHour");
            	hour.innerText = totalHour/60;
            	
        	});
        }
    });
	console.log(totalHour);
	
	
	
	$.ajax({
        type: "POST",
        url: "http://"+domainText+"/surepark_server/owner/getReserInfo.do",
        callback: "callbak",
		dataType: "jsonp",
        success: function(data){
        	$.each(data, function(k,v){
        		
        		console.log(k);
        		console.log(v);
        		console.log(v['P_STAY_TIME']);
            	/*if(k=="TEST"){
            		alert("Success!!!");
            		console.log(v);
            		console.log(v['P_RESER_ID']);
            	}*/
        		if(v['P_STAY_TIME'] != null){
        			console.log("aaaa");
        			totalHour = totalHour + parseInt(v['P_STAY_TIME']);
        		}
            	
            	if(k=="fail"){
            		alert("실패");
            	}
            	console.log(i);
            	var hour = document.getElementById("totalHour");
            	hour.innerText = totalHour/60;
            	
        	});
        }
    });
	
}


var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
var months = ["January","February","March","April","May","June","July", "August", "September", "October", "November", "December"];
var barChart = null;
var barChartData = {
	labels : ["00~03","03~06","06~09","09~12","12~15","15~18","18~21","21~24"],
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
			data : [randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor()]
		}
	]

};

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

