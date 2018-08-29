<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
/* js 全局变量 **********************************************************/
var $g_menuJson = [
	{"tc_code":"VIPMEMINFO","tc_order":"1","tc_app_id":1,"tc_permit_id":0,"pid":0,"id":1,"tc_name":"VIP会员Info","tc_url":"/admin/vipmeminfo/query"},
	{"tc_code":"VIPPOLICYFEEDBACK","tc_order":"2","tc_app_id":1,"tc_permit_id":1,"pid":0,"id":2,"tc_name":"VIP会员FeedBack","tc_url":"/admin/vippolicyfeedback/query"}
	];
var $g_btnJson = null;
/************************************************************************/
$(function(){
	// nav
	$chok.nav.init($g_menuJson);
	// 导航菜单查询
	/* $("#navSearchForm").submit(function(event) {
		event.preventDefault();
	}); */
});
</script>