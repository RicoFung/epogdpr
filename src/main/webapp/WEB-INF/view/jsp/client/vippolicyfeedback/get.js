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
	    var ua = navigator.userAgent.toLowerCase();                                    //取得浏览器的userAgent字符串
	    var isIE11 = ua.indexOf("trident/7.0") > -1 && !isChrome;                      //判断是否IE的11浏览器
	    var isEdge = ua.indexOf("edge") > -1;                                          //判断是否IE的Edge浏览器
	    var isIE = ua.indexOf("compatible") > -1 && ua.indexOf("msie") > -1 && !isOpera; //判断是否IE浏览器
	    var isFF = ua.indexOf("firefox") > -1;                                         //判断是否Firefox浏览器  
	    var isSafari = ua.indexOf("safari") > -1 && ua.indexOf("chrome") == -1; //判断是否Safari浏览器  
	    var isChrome = ua.indexOf("chrome") > -1 && ua.indexOf("safari") > -1;  //判断Chrome浏览器
	    var isOpera = ua.indexOf("opera") > -1;            //判断是否Opera浏览器
	    if(isIE){  
	        var reIE = new RegExp("msie (\\d+\\.\\d+);");  
	        reIE.test(ua);  
	        var fIEVersion = parseFloat(RegExp["$1"]);
	        if(fIEVersion == 7.0){ 
	            return "IE7";
	        }else if(fIEVersion == 8.0){ 
	            return "IE8";
	        }else if(fIEVersion == 9.0){ 
	            return "IE9";
	        }else if(fIEVersion == 10.0){ 
	            return "IE10";
	        }  
	    }else if (isIE11){
            return "IE11";
	    }else{
			var Sys={}; 
			var s; 
			(s=ua.match(/firefox\/([\d.]+)/))?Sys.firefox=s[1]: 
			(s=ua.match(/chrome\/([\d.]+)/))?Sys.chrome=s[1]: 
			(s=ua.match(/opera.([\d.]+)/))?Sys.opera=s[1]: 
			(s=ua.match(/version\/([\d.]+).*safari/))?Sys.safari=s[1]:0; 
			return s[0];
	    }
	}
});