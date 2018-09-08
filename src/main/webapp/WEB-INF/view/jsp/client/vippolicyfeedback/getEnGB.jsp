<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/ctx.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<title></title>
<link rel="stylesheet" href="${statics}/res/bs/css/bootstrap.css"/>
<link rel="stylesheet" href="${statics}/res/jquery/third/confirm/jquery-confirm.min.css" />
<script type="text/javascript" src="${statics}/res/jquery/jquery.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/third/confirm/jquery-confirm.min.js"></script>
<script type="text/javascript" src="${statics}/res/bs/js/bootstrap.min.js"></script>
<style type="text/css">
a{color:black; text-decoration:none; font-weight:bold;}
a:hover, a:visited, a:link, a:active {}
.title{height:20%; font-family:"Times New Roman",Times,serif; font-size:100px;}
.subtitle{height:10%; font-size:30px; padding:5px;}
.content{height:60%; padding:5px; overflow-x: hidden; overflow-y: auto; -webkit-overflow-scrolling: touch; border-top:solid 1px gray; border-bottom:solid 1px gray}
.catalog{list-style:none;}
.catalog li{margin-left:3px; margin-top:3px;}
.catalog-link {
	position: relative;
	display: block;
	width: 100%;
	background: transparent url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAlklEQVQ4T2NkoBAwUqifYTgbMGfOHKG/f/8GgcKImZl5XUpKyjts4YU1DKZOnSrBwsJygYGBQRyq6SUDA4NOenr6G3RDsBowc+bMHAYGhsloinPT09On0MeA6dOnKzAyMt5mZGRkAdn4////P////1fNzMx8QJQLQIrmzJkj//fvX1doIO5OSUl5SHQgkpI6h3NKJDYcAN1aLBFNKflzAAAAAElFTkSuQmCC") repeat-x center 80%; 
	overflow: hidden;
	background-size: 4px 4px;
 }
.catalog-text {
	float: left;
	background-color: white;
	padding-right: 5px;
}
.catalog-pages {
	float: right;
	background-color: white;
	padding: 0 20px 0 5px;
}
.bottom{height:10%; margin: 10px;}
.bottom label{margin-right:100px;}
#btn_confirm{width:100px; height:50px; font-size:20px; text-align:center;}
</style>
<script type="text/javascript">
$(function(){
	var param = {
		browserAgent: getBrowserAgent(),
		clientToken: "${clientToken}",
		lang: "en_GB"
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
    	        	$.alert({title: "${i18nAttention}", type:"red", content: result.msg});
    	        	return;
    	        }
	        	$.alert({title: "${i18nRemind}", type:"green", content: result.msg});
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
</script>
</head>
<body>
	<div align="center" class="title">MO&Co.</div>
	<div align="left" class="subtitle">Privacy Policy</div>
	<div class="content">
		<p>We are committed to protecting the privacy of our customers' data. This privacy policy (hereinafter the “Privacy Policy”) is intended to inform you how we collect and process any information that could be used to identify you, such as your name, your email address, your postal address, your date of birth or other information you have provided to us (hereinafter your “Personal Data”).</p>
		<p>We invite you to take the time to read this Privacy Policy carefully.</p>
		<p>
			<ul class="catalog">
				<li><a href="#section1" class="catalog-link"><span class="catalog-text">1.	Data controller identity</span><span class="catalog-pages">1</span></a></li>
				<li><a href="#section2" class="catalog-link"><span class="catalog-text">2.	How we collect your data</span><span class="catalog-pages">1</span></a></li>
				<li><a href="#section3" class="catalog-link"><span class="catalog-text">3.	Description of the processing performed</span><span class="catalog-pages">1</span></a></li>
				<li><a href="#section4" class="catalog-link"><span class="catalog-text">4.	How long your data will be stored</span><span class="catalog-pages">2</span></a></li>
				<li><a href="#section5" class="catalog-link"><span class="catalog-text">5.	Recipients of your data</span><span class="catalog-pages">2</span></a></li>
				<li><a href="#section6" class="catalog-link"><span class="catalog-text">6.	Data transfers outside the EU</span><span class="catalog-pages">2</span></a></li>
				<li><a href="#section7" class="catalog-link"><span class="catalog-text">7.	Your rights</span><span class="catalog-pages">2</span></a></li>
				<li><a href="#section8" class="catalog-link"><span class="catalog-text">8.	Complaints</span><span class="catalog-pages">2</span></a></li>
				<li><a href="#section9" class="catalog-link"><span class="catalog-text">9.	Modification of the Privacy Policy</span><span class="catalog-pages">2</span></a></li>
			</ul>
		</p>
		<div>
			<h4><a name="section1">1.	Data controller identity</a></h4>
			<p>The company in charge of the collection and processing of your Personal Data is V&A Fashion, a simplified joint stock company, registered with the Trade and Companies Register of Paris under number 807 631 775, having its registered office at 38 rue du Temple, 75004 Paris (hereinafter “V&A Fashion”).</p>
			<p>As data controller, we comply with the French Act No. 78-17 of 6 January 1978 on information technology, data files and civil liberties, in its current version (hereinafter the “French Data Protection Act”) as well as the European Regulation 2016/679 of 27 April 2016 or General Data Protection Regulation (hereinafter the “GDPR”).</p>
		</div>
		<div>
			<h4><a name="section2">2.	How we collect your data</a></h4>
			<p>We collect your Personal Data only through the form distributed in MO&Co stores and corners, which you have completed and given to our staff ("the Form").</p>
		</div>
		<div>
			<h4><a name="section3">3.	Description of the processing performed</a></h4>
			<p>Your Personal Data is collected and processed for various purposes, including:
				<ul>
					<li>Providing you with brand and product information, including news products or promotional notices;</li>
					<li>Inviting you to events, such as opening events or private sales;</li>
					<li>Providing you with membership loyalty programs;</li>
					<li>Carry out surveys on customers preferences in order to improve our quality of service.</li>
				</ul>
			</p>
			<p>We will contact you by postal mail, email, SMS and/or push notifications on your smartphone, depending on the choice you have made on the Form.</p>
			<p>This processing is based on the consent you expressed on the Form when choosing your preferred communication channel.</p>
		</div>
		<div>
			<h4><a name="section4">4.	How long your data will be stored</a></h4>
			<p>We will retain your Personal Data in our customer file as long as we have an ongoing relationship with you, ie for a period of three years from the date of its collection or your last interaction with MO&Co., for instance your last purchase in a MO&Co. boutique or corner.</p>
			<p>At the end of this period, or once you withdraw your consent, we will contact you by email in order to know if you wish to continue to be part of our customer file. If you express the wish to continue to receive our communications, your Personal Data will be stored for a further period of three years.</p>
			<p>At the end of this three-year period, your Personal Data may be stored for evidentiary purposes for the legal prescription periods.</p>
		</div>
		<div>
			<h4><a name="section5">5.	Recipients of your data</a></h4>
			<p>We take the security and confidentiality of your Personal Data very seriously and we inform you that we will never sell your Personal Data under any circumstances.</p>
			<ul>
				<li>However, we may disclose your Personal Data to selected third parties, including:</li>
				<li>
					<span>Other companies in our group, for governance purposes and to monitor the group's activity. Here is the list of the group's local entities and their addresses:</span>
					<ul>
						<li>V&A Fashion Inc. Limited: No.1 London Bridge, London, England SE1 9BG;</li>
						<li>V&A Fashion Co., Limited: Suite 3716, Tower 2, Times Square, Causeway Bay, Hong Kong.</li>
					</ul>
				</li>
				<li>To third party service providers, and in particular to our IT service provider to technically manage our databases, that will act as a data processor.</li>
			</ul>
			<p>En tant que responsable de traitement, nous nous conformons à la loi n° 78-17 du 6 janvier 1978 relative à l'informatique, aux fichiers et aux libertés, dans sa version actuelle (ci-après la « Loi Informatique et Liberté ») ainsi qu’au Règlement européen 2016/679 du 27 avril 2016 ou Règlement Général sur la Protection des Données (ci-après le « RGPD »).</p>
		</div>
		<div>
			<h4><a name="section6">6.	Data transfers outside the EU</a></h4>
			<p>Your Personal Data may be transferred to China to be processed by selected third parties in order to facilitate the conduct of V&A Fashion's business. </p>
			<p>Since China does not have laws that provide the same level of protection for your Personal Data as the one in force in the European Union, if your Personal Data is transferred to China we will make sure that appropriate safeguards are in place to ensure that such transfer complies with applicable data protection laws.</p>
		</div>
		<div>
			<h4><a name="section7">7.	Your rights</a></h4>
			<div>As a data subject, you have a number of rights: </div>
			<ul>
				<li>The right to obtain confirmation as to whether or not your Personal Data are being processed, and, where that is the case, access to your Personal Data;</li>
				<li>The right to obtain the rectification of inaccurate Personal Data;</li>
				<li>The right to obtain the erasure of your Personal Data;</li>
				<li>The right to obtain from Artefact restriction of processing;</li>
				<li>The right to object, on grounds relating to your particular situation, at any time to processing of your Personal Data which is based on point (e) or (f) of Article 6(1) of the GDPR;</li>
				<li>The right to receive the Personal Data you have provided to Artefact, in a structured, commonly used and machine-readable format and the right to transmit those data to another controller (data portability).</li>
			</ul>
			<p>You can exercise your rights at any time by contacting us at [DPO@mo-co.com]. A photocopy of an identity document may be requested. </p>
			<p>You can also withdraw your consent at any time by clicking here.</p>
			<p>For more information concerning the exercise of your rights, you can consult <a href="https://www.cnil.fr/fr/comprendre-vos-droits">the CNIL website</a>.</p>
		</div>
		<div>
			<h4><a name="section8">8.	Complaints</a></h4>
			<p>In case of difficulty in connection with the management of your Personal Data, you may address a complaint to [DPO@mo-co.com] or to the CNIL or any other relevant supervisory authority.</p>
		</div>
		<div>
			<h4><a name="section9">9.	Modification of the Privacy Policy</a></h4>
			<p>This Privacy Policy was last updated on May 25, 2018.</p>
			<p>We may change all or parts of this Privacy Policy from time to time. You will be notified of any significant changes to this document.</p>
		</div>
	</div>
	<div align="left" class="bottom">
		<input type="radio" name="rd_feedback" id="rd_feedback1" value="A"/><label for="rd_feedback1">${i18nAccept}</label>
		<input type="radio" name="rd_feedback" id="rd_feedback2" value="R" checked="checked"/><label for="rd_feedback2">${i18nReject}</label>
		<div align="center" class="btn_submit">
			<input type="button" id="btn_submit" value="${i18nSubmit}"/>
		</div>
	</div>
</body>
</html>