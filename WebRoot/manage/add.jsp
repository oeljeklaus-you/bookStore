<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="${pageContext.request.contextPath}/CategoryServlet?op=add" method="POST">
        <input type="hidden" name="id" value="${category.id}"/>
        <table border="1"  cellspacing="0" cellpadding="10" width="500" height="500"><!--cellspacing 表示每个小方格的距离-->
	   <tr>
	      <th  colspan="2">添加分类</th>
	   </tr>
	   <tr>
		  <td>分类名称</td>
		  <td><input type="text" name="cname" value="${category.cname }"/></td>
	   </tr>
	   <tr>
	      <td>分类描述</td>
		  <td><input type="text" name="cdesc" value="${category.cdesc }"/></td>
	   </tr>
	   
	   <tr>
	      <th  colspan="2"><input type="submit" value="提交"/></th>
	   </tr>
	</table>
    </form>
  </body>
</html>
