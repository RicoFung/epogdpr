<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/ctx.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="stylesheet" href="${statics}/res/jquery/third/confirm/jquery-confirm.min.css" />
<script type="text/javascript" src="${statics}/res/jquery/jquery.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/third/confirm/jquery-confirm.min.js"></script>
<style type="text/css">
a{text-decoration:none;}
</style>
<script type="text/javascript">
$(function(){
	var param = {
		browserAgent: getBrowserAgent(),
		memberCode: "${memberCode}",
		lang: "fr_FR"
	};
	$("#btn_agree").click(function(){
		$.get("http://ipinfo.io", function(response) {
			$.extend(param, {
				clientIp: response.ip,
				clientSendTime: getCurrentDate(new Date()),
				feedbackResult: "A"
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
	$("#btn_reject").click(function(){
		$.get("http://ipinfo.io", function(response) {
			$.extend(param, {
				clientIp: response.ip,
				clientSendTime: getCurrentDate(new Date()),
				feedbackResult: "R"
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
<title></title>
</head>
<body>
	<h1 align="center">Politique de confidentialité MO&Co.</h1>
	<h4 align="center">Dernière mise à jour : 25 mai 2018</h4>
	<p>Nous nous engageons à protéger la confidentialité des données de nos clients. La présente politique de confidentialité (ci-après la « Politique de Confidentialité ») est destinée à vous informer de la manière dont nous recueillons et utilisons les données qui pourraient permettre de vous identifier, telles que votre nom, votre adresse électronique, votre adresse postale, votre date de naissance ou les autres informations que vous nous avez communiquées (ci-après vos « Données Personnelles »).</p>
	<p>Nous vous invitons à prendre le temps de lire attentivement cette Politique de Confidentialité.</p>
	<p>
		<ul>
			<li><a href="#section1">1.	Identité du responsable de traitement...................................1</a></li>
			<li><a href="#section2">2.	Mode de collecte de vos données.........................................1</a></li>
			<li><a href="#section3">3.	Description des traitements réalisés....................................1</a></li>
			<li><a href="#section4">4.	Durée de conservation de vos données....................................1</a></li>
			<li><a href="#section5">5.	Destinataires des données collectées....................................2</a></li>
			<li><a href="#section6">6.	Transferts de données hors UE...........................................2</a></li>
			<li><a href="#section7">7.	Vos droits..............................................................2</a></li>
			<li><a href="#section8">8.	Réclamations............................................................2</a></li>
			<li><a href="#section9">9.	Modification de la Politique de Confidentialité.........................2</a></li>
		</ul>
	</p>
	<div>
		<h4><a name="section1">1.	Identité du responsable de traitement</a></h4>
		<p>Le responsable de la collecte et du traitement de vos Données Personnelles est la société V&A Fashion, société par actions simplifiée, immatriculée au RCS de Paris sous le n°807631775, ayant son siège social 38 rue du Temple, 75004 Paris (ci-après « V&A Fashion »).</p>
		<p>En tant que responsable de traitement, nous nous conformons à la loi n° 78-17 du 6 janvier 1978 relative à l'informatique, aux fichiers et aux libertés, dans sa version actuelle (ci-après la « Loi Informatique et Liberté ») ainsi qu’au Règlement européen 2016/679 du 27 avril 2016 ou Règlement Général sur la Protection des Données (ci-après le « RGPD »).</p>
	</div>
	<div>
		<h4><a name="section2">2.	Mode de collecte de vos données</a></h4>
		<p>Nous ne collectons vos Données Personnelles que par l’intermédiaire du formulaire distribué dans les magasins et corners MO&Co., que vous avez rempli et remis à notre personnel (« ci-après « le Formulaire »).</p>
	</div>
	<div>
		<h4><a name="section3">3.	Description des traitements réalisés</a></h4>
		<p>Vos Données Personnelles sont collectées et traitées afin de :
			<ul>
				<li>Vous communiquer des informations relatives à notre marque et à nos produits, et notamment pour vous informer du lancement de nouveaux produits ou offres promotionnelles ;</li>
				<li>Vous inviter à participer à des événements tels que des ouvertures de boutiques ou des ventes privées ;</li>
				<li>Vous permettre d’adhérer à notre programme de fidélité ;</li>
				<li>Nous permettre de réaliser des sondages relatifs aux préférences de nos clients, afin d’améliorer la qualité de notre service.</li>
			</ul>
		</p>
		<p>Nous vous contacterons par email, par courrier, par SMS et/ou par des notifications sur votre smartphone, selon le choix que vous avez indiqué sur le Formulaire.
		</p>
		<p>Ce traitement se fonde sur le consentement que vous avez exprimé sur le Formulaire en choisissant le canal de communication.</p>
	</div>
	<div>
		<h4><a name="section4">4.	Durée de conservation de vos données</a></h4>
		<p>Vos Données Personnelles seront stockées dans notre fichier client aussi longtemps que nous avons une relation commerciale avec vous, autrement dit pour une durée de trois ans à compter de leur collecte ou de votre dernière interaction avec la marque MO&Co., par exemple de votre dernier achat en boutique ou sur un corner MO&Co.
		</p>
		<p>A l’expiration de ce délai, nous vous contacterons par email afin de savoir si vous souhaitez ou non continuer à faire partie de notre fichier client. Si vous exprimez le souhait de continuer à recevoir nos communications, vos Données Personnelles seront stockées pour une nouvelle période de trois ans.
		</p>
		<p>A l’expiration de cette période de trois ans, vos Données Personnelles pourront faire l’objet d’un archivage à titre probatoire pendant les durées de prescription légale.
		</p>
	</div>
	<div>
		<h4><a name="section5">5.	Destinataires des données collectées</a></h4>
		<p>Nous prenons la sécurité et la confidentialité de vos Données Personnelles très au sérieux et nous vous informons que nous ne vendrons jamais vos Données Personnelles, en aucune circonstance.
		</p>
		<ul>
			<li>Toutefois, nous pouvons communiquer vos Données Personnelles à des tiers sélectionnés, et notamment :</li>
			<li>
				<span>A l’une des sociétés de notre groupe, à des fins de gouvernance et de suivi de l’activité du groupe. Voici la liste des entités locales du groupe et leur adresse :
				</span>
				<ul>
					<li>V&A Fashion Inc. Limited : No.1 London Bridge, London, England SE1 9BG ;</li>
					<li>V&A Fashion Co., Limited : Suite 3716, Tower 2, Times Square, Causeway Bay, Hong Kong.</li>
				</ul>
			</li>
			<li>A des prestataires de services tiers, et notamment à notre prestataire informatique en qualité de sous-traitant pour gérer nos bases de données d’un point de vue technique.
			</li>
		</ul>
		<p>En tant que responsable de traitement, nous nous conformons à la loi n° 78-17 du 6 janvier 1978 relative à l'informatique, aux fichiers et aux libertés, dans sa version actuelle (ci-après la « Loi Informatique et Liberté ») ainsi qu’au Règlement européen 2016/679 du 27 avril 2016 ou Règlement Général sur la Protection des Données (ci-après le « RGPD »).</p>
	</div>
	<div>
		<h4><a name="section6">6.	Transferts de données hors UE</a></h4>
		<p>Vos Données Personnelles peuvent être transférées et stockées vers la Chine, à des fins de traitement de ces données par des tiers sélectionnés, afin de faciliter la conduite des activités de la société V&A Fashion. </p>
		<p>La Chine n’étant pas dotée de lois qui prévoient le même niveau de protection de vos Données Personnelles que les lois en vigueur dans l’EEE, en cas de transfert de vos Données Personnelles vers la Chine nous nous assurerons que des garanties appropriées ont été mises en place afin d’assurer la conformité de ce transfert aux lois applicables sur la protection des données.</p>
	</div>
	<div>
		<h4><a name="section7">7.	Vos droits</a></h4>
		<div>Conformément à la règlementation applicable en matière de données à caractère personnel, vous disposez d’un certain nombre de droits : </div>
		<ul>
			<li>Le droit d'obtenir la confirmation que vos Données Personnelles sont ou ne sont pas traitées et, lorsqu'elles le sont, l'accès à ces Données Personnelles ;</li>
			<li>Le droit d'obtenir la rectification des Données Personnelles vous concernant qui seraient inexactes ;</li>
			<li>Le droit d'obtenir l'effacement de vos Données Personnelles ;</li>
			<li>Le droit d'obtenir la limitation du traitement de vos Données Personnelles ;</li>
			<li>Le droit de vous opposer à tout moment, pour des raisons tenant à votre situation particulière, à un traitement de vos Données Personnelles fondé sur l'article 6, paragraphe 1, point e) ou f) du RGPD ;</li>
			<li>Le droit de recevoir les Données Personnelles que vous nous avez fournies dans un format structuré, couramment utilisé et lisible par machine, ainsi que le droit de transmettre ces Données Personnelles à un autre responsable du traitement ;</li>
		</ul>
		<p>Vous pouvez exercer ces droits en envoyant un email à l’adresse [DPO@mo-co.com]. Une photocopie d’un titre d’identité pourra vous être demandée.</p>
		<p>Vous pouvez également retirer votre consentement à tout moment en cliquant ici.</p>
		<p>Pour plus d’informations concernant l’exercice de vos droits, vous pouvez consulter <a href="https://www.cnil.fr/fr/comprendre-vos-droits">le site internet de la CNIL</a></p>
	</div>
	<div>
		<h4><a name="section8">8.	Réclamations</a></h4>
		<p>En cas de difficulté en lien avec la gestion de vos Données Personnelles, vous pouvez adresser une réclamation auprès de [DPO@mo-co.com] ou auprès de la CNIL ou de toute autre autorité compétente.</p>
	</div>
	<div>
		<h4><a name="section9">9.	Modification de la Politique de Confidentialité</a></h4>
		<p>Cette Politique de Confidentialité a été mise à jour pour la dernière fois le 25 mai 2018.</p>
		<p>Nous nous réservons le droit de modifier cette Politique de Confidentialité à tout moment, en totalité ou en partie. Vous serez averti à chaque fois que nous effectuerons une modification substantielle de ce document.</p>
	</div>
	<div align="center" style="margin-bottom: 100px">
		<a id="btn_reject" href="javascript:void(0)" style="font-family: Arial; font-size:30; margin: 10px;">${i18nReject}</a>
		<a id="btn_agree" href="javascript:void(0)" style="font-family: Arial; font-size:30; margin: 10px;">${i18nAgree}</a>
	</div>
</body>
</html>