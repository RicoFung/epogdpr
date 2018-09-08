<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="chok.util.PropertiesUtil" %>
<%
String ctx = request.getContextPath();
request.setAttribute("ctx", ctx);
request.setAttribute("statics", ctx+"/static");
request.setAttribute("jspstatics", ctx+"/jspstatic");
%>
<script type="text/javascript">
var $ctx="${ctx}";
</script>