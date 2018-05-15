<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addBook.jsp' starting page</title>
    
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
    <form action="${pageContext.request.contextPath}/BookServlet?op=addBook" method="POST" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${book.id}"/>
        <table border="1"  cellspacing="0" cellpadding="10" width="500" height="500"><!--cellspacing 表示每个小方格的距离-->
	   <tr>
	      <th  colspan="2">添加书籍</th>
	   </tr>
	   <tr>
		  <td>书籍名称</td>
		  <td><input type="text" name="bname" value="${book.bname }"/></td>
	   </tr>
	   <tr>
		  <td>书籍作者</td>
		  <td><input type="text" name="author" value="${book.author }"/></td>
	   </tr>
	   <tr>
	      <td>书籍页数</td>
		  <td><input type="text" name="pageNum" value="${book.pageNum }"/></td>
	   </tr>
	   <tr>
	      <td>书籍目录</td>
		  <td>
		      <select name="category">
		           <c:forEach items="${categories}" var="category">
		                <option  value="${category.id}">${category.cname}</span></option>  
		           </c:forEach>
		      </select>
		  </td>
	   </tr>
	   <tr>
	      <td>书籍价格</td>
		  <td><input type="text" name="price" value="${book.price}"/></td>
	   </tr>
	    <tr>
	      <td>书籍描述</td>
		  <td><input type="text" name="description" value="${book.description}"/></td>
	   </tr>
	   <tr>
	      <td>书籍图片</td>
		  <td><input type="file" name="image"/></td>
	   </tr>
	   <tr>
	      <th  colspan="2"><input type="submit" value="提交"/></th>
	   </tr>
	</table>
    </form>
  </body>
</html>
