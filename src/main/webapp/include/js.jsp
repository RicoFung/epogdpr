<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${statics}/res/jquery/jquery.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/third/ztree/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/third/loadingoverlay/loadingoverlay.min.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/third/loadingoverlay/loadingoverlay_progress.min.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/third/confirm/jquery-confirm.min.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/customize/fn/jquery.customize.fn.resize.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/customize/plugin/jquery.customize.plugin.DropDownSelect.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/customize/plugin/jquery.customize.plugin.ListSelectModal.js"></script>
<script type="text/javascript" src="${statics}/res/jquery/customize/plugin/jquery.customize.plugin.ListSelectField.js"></script>
<script type="text/javascript" src="${statics}/res/bs/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${statics}/res/bs/js/bootstrap-table.js"></script>
<script type="text/javascript" src="${statics}/res/bs/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="${statics}/res/bs/js/bootstrap-table-fixed-columns.js"></script>
<script type="text/javascript" src="${statics}/res/bs/js/bootstrap-editable.min.js"></script>
<script type="text/javascript" src="${statics}/res/bs/js/bootstrap-table-editable.min.js"></script>
<script type="text/javascript" src="${statics}/res/bs/js/bootstrap-table-contextmenu.min.js"></script>
<script type="text/javascript" src="${statics}/res/bs/js/bootstrap-table-multiple-sort.js"></script>

<!-- date-range-picker -->
<script src="${statics}/res/AdminLTE/plugins/daterangepicker/moment.min.js"></script>
<script src="${statics}/res/AdminLTE/plugins/daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="${statics}/res/AdminLTE/plugins/datepicker/bootstrap-datepicker.js"></script>

<script type="text/javascript" src="${statics}/res/chok/js/chok.base.js"></script>
<script type="text/javascript" src="${statics}/res/chok/js/chok.validator.js"></script>
<script type="text/javascript" src="${statics}/res/chok/js/chok.form.js"></script>
<script type="text/javascript" src="${statics}/res/chok/js/chok.view.js"></script>
<script type="text/javascript" src="${statics}/res/chok/js/chok.nav.js"></script>
<script type="text/javascript">
var token = $("meta[name='_csrf']").attr("content");  
var header = $("meta[name='_csrf_header']").attr("content");  
$.ajaxSetup({  
    beforeSend: function (xhr) {  
        if(header && token ){  
            xhr.setRequestHeader(header, token);  
        }  
    }}  
); 
</script>