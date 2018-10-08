<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/view-begin.jsp"%>
<!-- 主内容面板 -->
<div class="content-wrapper">
<section class="content-header">
	<h1>${param.menuName}</h1>
	<ol class="breadcrumb">
		<li><a href="${ctx}/index.jsp"><i class="fa fa-dashboard"></i> 首页</a></li>
		<li class="active">${param.menuName}</li>
	</ol>
</section>
<section class="content">
	<div class="row">
	<div class="col-md-12">
	<div class="box box-default">
	<div class="box-header with-border">
		<h3 class="box-title"><small><i class="glyphicon glyphicon-th-list"></i></small></h3>
	</div>
	<div class="box-body">
		<!-- toolbar
		======================================================================================================= -->
		<div id="toolbar">
		<button type="button" class="btn btn-default" id="bar_btn_query" pbtnId="pbtn_query2" data-toggle="modal" data-target="#modal_form_query"><i class="glyphicon glyphicon-search"></i></button>
		<button type="button" class="btn btn-default" id="bar_btn_exp" ><i class="glyphicon glyphicon-download"></i></button>
		<button type="button" class="btn btn-default" id="bar_btn_imp" ><i class="glyphicon glyphicon-upload"></i></button>
		<button type="button" class="btn btn-default" id="bar_btn_email" ><i class="glyphicon glyphicon-envelope"></i></button>
		<button type="button" class="btn btn-default" id="bar_btn_del"><i class="glyphicon glyphicon-remove"></i></button>
		</div>
		<!-- data list
		======================================================================================================= -->
		<table id="tb_list"></table>
		<!-- context menu
		======================================================================================================= -->
		<ul id="tb_ctx_menu" class="dropdown-menu">
		</ul>
	</div>
	</div>
	</div>
	</div>
</section>
</div>
<!-- query form modal
======================================================================================================= -->
<form id="form_query" style="overflow:scroll;" >
<div id="modal_form_query" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modal_label" aria-hidden="true">
<div class="modal-dialog">
<div class="modal-content">
	<div class="modal-header">
	   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	   <h4 class="modal-title" id="modal_label">筛选条件</h4>
	</div>
	<div class="modal-body">
		<div class="form-group">
			<label for="f_memberCode">memberCode：</label><input type="text" class="form-control input-sm" id="f_memberCode"/>
		</div>
		<div class="form-group">
			<label for="f_storeCode">storeCode：</label><input type="text" class="form-control input-sm" id="f_storeCode"/>
		</div>
		<div class="form-group">
			<label for="f_country">country：</label><select class="form-control input-sm" id="f_country"></select>
		</div>
		<div class="form-group">
			<label for="f_joinDateFm">joinDateFm：</label>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
				<input type="text" class="form-control pull-right" id="f_joinDateFm"/>
			</div>
		</div>
		<div class="form-group">
			<label for="f_joinDateTo">joinDateTo：</label>
			<div class="input-group">
				<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
				<input type="text" class="form-control pull-right" id="f_joinDateTo"/>
			</div>
		</div>
		<div class="form-group">
			<label for="f_sendStatus">sendStatus：</label>
			<select class="form-control input-sm" id="f_sendStatus">
				<option value="">全部</option>
				<option value="0">未发送</option>
				<option value="1">发送成功</option>
				<option value="-1">发送失败</option>
			</select>
		</div>
	</div>
	<div class="modal-footer">
	   <button type="reset" class="btn btn-default"><i class="glyphicon glyphicon-repeat"></i></button>
	   <button type="button" class="btn btn-primary" id="form_query_btn"><i class="glyphicon glyphicon-ok"></i></button>
	</div>
</div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
</form>
<%@ include file="/include/view-end.jsp"%>
<!-- ======================================================================================================= -->
<script type="text/javascript" src="${staticexternal}/res/chok/js/chok.auth.js"></script>
<script type="text/javascript" src="${staticexternal}/res/chok/js/chok.view.query2.js"></script>
<script type="text/javascript">
/**********************************************************/
/* 全局函数 */
/**********************************************************/
$(function() {
	$chok.view.fn.selectSidebarMenu("${param.menuId}","${param.menuPermitId}","${param.menuName}");
	$chok.view.query.init.toolbar();
	$chok.view.query.init.modalFormQuery();
	$chok.view.query.init.table("${queryParams.f_page}","${queryParams.f_pageSize}");
	$chok.auth.btn($chok.view.menuPermitId,$g_btnJson);
});
/**********************************************************/
/* 初始化配置 */
/**********************************************************/
$chok.view.query.config.setPreFormParams = function(){
	$("#f_memberCode").val(typeof("${queryParams.f_memberCode}")=="undefined"?"":"${queryParams.f_memberCode}");
	$("#f_storeCode").val(typeof("${queryParams.f_storeCode}")=="undefined"?"":"${queryParams.f_storeCode}");
	$("#f_country").val(typeof("${queryParams.f_country}")=="undefined"?"":"${queryParams.f_country}");
	$("#f_joinDateFm").val(typeof("${queryParams.f_joinDateFm}")=="undefined"?"":"${queryParams.f_joinDateFm}");
	$("#f_joinDateTo").val(typeof("${queryParams.f_joinDateTo}")=="undefined"?"":"${queryParams.f_joinDateTo}");
	$("#f_sendStatus").val(typeof("${queryParams.f_sendStatus}")=="undefined"?"":"${queryParams.f_sendStatus}");
};
$chok.view.query.config.formParams = function(p){
	p.memberCode = $("#f_memberCode").val();
	p.storeCode = $("#f_storeCode").val();
	p.country = $("#f_country").val();
	p.joinDateFm = $("#f_joinDateFm").val();
	p.joinDateTo = $("#f_joinDateTo").val();
	p.sendStatus = $("#f_sendStatus").val();
    return p;
};
$chok.view.query.config.urlParams = function(){
	return {
			f_memberCode : $("#f_memberCode").val(),
			f_storeCode : $("#f_storeCode").val(),
			f_country : $("#f_country").val(),
			f_joinDateFm : $("#f_joinDateFm").val(),
			f_joinDateTo : $("#f_joinDateTo").val(),
			f_sendStatus : $("#f_sendStatus").val()
	};
};
// config-定义表格列
$chok.view.query.config.tableColumns = 
[
    {title:"memberCode", field:"memberCode", align:"center", valign:"middle", sortable:true},
    {title:"email", field:"email", align:"center", valign:"middle", sortable:true},
    {title:"joinDate", field:"joinDate2", align:"center", valign:"middle", sortable:true},
    {title:"storeCode", field:"storeCode", align:"center", valign:"middle", sortable:true},
    {title:"country", field:"country", align:"center", valign:"middle", sortable:true},
    {title:"countryCn", field:"countryCn", align:"center", valign:"middle", sortable:false},
    {title:"sendTime", field:"sendTime", align:"center", valign:"middle", sortable:true},
    {title:"sendStatus", field:"sendStatus", align:"center", valign:"middle", sortable:true,
        formatter:function(value,row,index){
        	if (row.sendStatus=="0") {
        		return "未发送";
        	} else if (row.sendStatus=="1") {
        		return "发送成功";
        	} else if (row.sendStatus=="-1") {
        		return "发送失败";
        	} else {
        		return "";
        	}
        } 
    }
];
// config-是否显示复合排序
$chok.view.query.config.showMultiSort = true;
// config-默认排序字段
$chok.view.query.config.sortPriority = [{"sortName":"", "sortOrder":"asc"}];
// callback-加载数据成功后
$chok.view.query.callback.onLoadSuccess = function(){
	$chok.auth.btn($chok.view.menuPermitId,$g_btnJson);
};
// OVERWRITE-自定义工具栏
$chok.view.query.init.toolbar = function(){
	$("#bar_btn_exp").click(function(){
		$chok.view.query.fn.exp("exp", 
				                "vip_mem_info",
				                "", 
				                "member_code,email,join_date,store_code,country,send_time,send_status",
				                "memberCode,email,joinDate2,storeCode,countryCn,sendTime,sendStatus");
	});
	$("#bar_btn_imp").click(function(){
		location.href = "imp?"+$chok.view.query.fn.getUrlParams();
	});
	$("#bar_btn_email").click(function(){
		if($chok.view.query.fn.getSelections().length<1) {
			$.alert({title: "提示", type: "red", content: "没选择"});
			return;
		}
		$.confirm({
		    title: "提示",
		    content: "确认发送邮件？",
		    type: 'green',
		    typeAnimated: true,
		    buttons: {
		        ok: function() {
		        	var keys = ["memberCode", "email", "country", "sendStatus"];
		    		$.post("sendEmail", 
    				{
		    			jsonparams: JSON.stringify($chok.view.query.fn.getValSelectionsByKey2(keys))
    				},
    				function(result){
		    	        $chok.view.query.callback.delRows(result); // 删除行回调
		    	        if (result.success) 
	    	        		$.alert({title: "提示", type:"green", content: result.msg});
		    	        else
		    	        	$.alert({title: "提示", type:"red", content: result.msg});
		    	        $("#tb_list").bootstrapTable("refresh"); // 刷新table
		    		});
		        },
		        close: function () {
		        }
		    }
		});
	});
	$("#bar_btn_del").click(function(){
		if($chok.view.query.fn.getValSelectionsByKey("memberCode").length<1) {
			$.alert({title: "提示", type: "red", content: "没选择"});
			return;
		}
		$.confirm({
		    title: '提示',
		    content: "确认删除？",
		    type: 'green',
		    typeAnimated: true,
		    buttons: {
		        ok: function() {
			    		$.post("del",{memberCode:$chok.view.query.fn.getValSelectionsByKey("memberCode")},function(result){
			    	        $chok.view.query.callback.delRows(result); // 删除行回调
			    	        if(!result.success) {
			    	        	$.alert({title: "提示", type:"red", content: result.msg});
			    	        	return;
			    	        }
			    	        $.alert({title: "提示", type:"green", content: "删除成功！"});
			    	        $("#tb_list").bootstrapTable('refresh'); // 刷新table
			    		});
		        },
		        close: function () {
		        }
		    }
		});
	});
};
// OVERWRITE-表格第一二列
$chok.view.query.fn.getColumns = function(){
	var columns = 
		[
	     {checkbox:true, align:'center', valign:'middle'}
		];
	return $.merge(columns, $chok.view.query.config.tableColumns);
};
// 用户自定义
$chok.view.fn.customize = function(){
    $('#f_joinDateFm').datepicker({
    	format: 'yyyy-mm-dd'
    });
    $('#f_joinDateTo').datepicker({
    	format: 'yyyy-mm-dd'
    });
    
	var country_select = 
		$("#f_country").DropDownSelect({
			url:$ctx+"/dict/getCountrys"
		});
};
</script>