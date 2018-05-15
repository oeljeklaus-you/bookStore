<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listAllBook.jsp' starting page</title>
    
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
 	<table border="1" bordercolor="blue" id="t1" cellspacing="0" cellpadding="10" width="500" height="500"><!--cellspacing 表示每个小方格的距离-->
	   <tr>
	      <th>序号</th>
	      <th>书籍id</th>
	      <th>书籍名称</th>
	      <th>书籍作者</th>
	      <th>书籍页数</th>
	      <th>书籍价格</th>
	      <th>书籍描述</th>
	      <th>所属分类</th>
	      <th>书籍图片</th>
	      <th>操作</th>
	   </tr>
	  <c:forEach items="${page.records}" var="record" varStatus="num" >
	   <tr>
	      <td nowrap="nowrap">${num.index}</td>
	      <td nowrap="nowrap">${record.id}</td>
	      <td nowrap="nowrap">${record.bname}</td>
	      <td nowrap="nowrap">${record.author}</td>
	      <td nowrap="nowrap">${record.pageNum}</td>
	      <td nowrap="nowrap">${record.price}</td>
	      <td nowrap="nowrap">${record.description}</td>
	      <td nowrap="nowrap">${record.category.cname}</td>
	      <td nowrap="nowrap"><img  src="${pageContext.request.contextPath}/images/${record.path}/${record.newImageName}"></td>
	      <td nowrap="nowrap">
	        [<a href="${pageContext.request.contextPath}/CategoryServlet?op=deleteCategory&id=${c.id}">删除</a>]
	        [<a href="${pageContext.request.contextPath}/CategoryServlet?op=updateCategory&id=${c.id}">修改</a>]
	      </td>
	   </tr>
	  </c:forEach>
	</table>
         共${page.totalPage}页 &nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/BookServlet?op=listAll&pageNum=${page.pageNum-1>0?(page.pageNum-1):page.pageNum}">上一页</a>    <a href="${pageContext.request.contextPath}/BookServlet?op=listAll&pageNum=${page.pageNum+1>page.totalPage?page.totalPage:(page.pageNum+1)}">下一页</a>  
  </body>
</html>
