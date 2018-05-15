<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listCategory.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/listCategory.css">
	
  </head>
  
  <body>
     <table border="1" bordercolor="blue" id="t1" cellspacing="0" cellpadding="10" width="500" height="500"><!--cellspacing 表示每个小方格的距离-->
	   <tr>
	      <th>序号</th>
	      <th>分类id</th>
	      <th>分类名称</th>
	      <th>分类描述</th>
	      <th>操作</th>
	   </tr>
	  <c:forEach items="${categories }" var="c" varStatus="num" >
	   <tr  class="${num.count%2==0?'odd':'ji'}">
	      <td nowrap="nowrap">${num.index}</td>
	      <td nowrap="nowrap">${c.id}</td>
	      <td nowrap="nowrap">${c.cname}</td>
	      <td nowrap="nowrap">${c.cdesc}</td>
	      <td nowrap="nowrap">
	        [<a href="${pageContext.request.contextPath}/CategoryServlet?op=deleteCategory&id=${c.id}">删除</a>]
	        [<a href="${pageContext.request.contextPath}/CategoryServlet?op=updateCategory&id=${c.id}">修改</a>]
	      </td>
	   </tr>
	  </c:forEach>
	</table>
  </body>
</html>
