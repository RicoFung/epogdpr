$(function(){
	var param = {
		browserAgent: getBrowserAgent(),
		clientToken: $clientToken,
		lang: $lang
	};
	$("#btn_submit").click(function(){
		var feedback = $("input[name='rd_feedback']:checked").val();
		$.get("http://ipinfo.io", function(response) {
			$.extend(param, {
				clientIp: response.ip,
				clientSendTime: getCurrentDate(new Date()),
				feedbackResult: feedback
			});
			$.post("feedback", param, function(result){
    	        if(!result.success) {
    	        	$.alert({title: $i18nAttention, type:"red", content: result.msg});
    	        	return;
    	        }
	        	$.alert({title: $i18nRemind, type:"green", content: result.msg});
			});
		}, "jsonp");
	});
	
	function getCurrentDate(date){
	    var y = date.getFullYear();
	    var m = date.getMonth()+1;
	    var d = date.getDate();
	    var h = date.getHours();
	    var min = date.getMinutes();
	    var s = date.getSeconds();
	    var str=y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+'  '+(h<10?('0'+h):h)+':'+(min<10?('0'+min):min)+':'+(s<10?('0'+s):s);
	    return str;
	}
	
	function getBrowserAgent() {
		var Sys={}; 
	    var ua=navigator.userAgent.toLowerCase(); 
	    var s; 
	    (s=ua.match(/msie ([\d.]+)/))?Sys.ie=s[1]: 
	    (s=ua.match(/firefox\/([\d.]+)/))?Sys.firefox=s[1]: 
	    (s=ua.match(/chrome\/([\d.]+)/))?Sys.chrome=s[1]: 
	    (s=ua.match(/opera.([\d.]+)/))?Sys.opera=s[1]: 
	    (s=ua.match(/version\/([\d.]+).*safari/))?Sys.safari=s[1]:0; 
		return s[0];
	}
});