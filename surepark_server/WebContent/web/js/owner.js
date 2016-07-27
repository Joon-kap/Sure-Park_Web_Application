var domainText = "localhost:8080";


function conf1Apply(){
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

$(function() {
    var startDate;
    var endDate;
    
    var selectCurrentWeek = function() {
        window.setTimeout(function () {
            $('.week-picker').find('.ui-datepicker-current-day a').addClass('ui-state-active')
        }, 1);
    }
    
    $('#weekpicker').datepicker( {
        showOtherMonths: true,
        selectOtherMonths: true,
        onSelect: function(dateText, inst) { 
            var date = $(this).datepicker('getDate');
            startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay());
            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 6);
            var dateFormat = inst.settings.dateFormat || $.datepicker._defaults.dateFormat;
            
/*            $('#weekpicker').text($.datepicker.formatDate( dateFormat, startDate, inst.settings ) + " - " +
            		$.datepicker.formatDate( dateFormat, endDate, inst.settings );*/
            $('#startDate').text($.datepicker.formatDate( dateFormat, startDate, inst.settings ));
            $('#endDate').text($.datepicker.formatDate( dateFormat, endDate, inst.settings ));
            
            selectCurrentWeek();
        },
        beforeShowDay: function(date) {
            var cssClass = '';
            if(date >= startDate && date <= endDate)
                cssClass = 'ui-datepicker-current-day';
            return [true, cssClass];
        },
        onChangeMonthYear: function(year, month, inst) {
            selectCurrentWeek();
        }
    });
    
    $('.week-picker .ui-datepicker-calendar tr').live('mousemove', function() { $(this).find('td a').addClass('ui-state-hover'); });
    $('.week-picker .ui-datepicker-calendar tr').live('mouseleave', function() { $(this).find('td a').removeClass('ui-state-hover'); });
});

$( function() {
  $( "#datepicker" ).datepicker();
} );
