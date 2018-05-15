<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
         a{
            text-decoration: none;
            text-align: center;
            padding-left:30px;
            font-size: 20px;
            font-weight: 50px;
            line-height:80px;
            color:blue;
         }
         a HOVER {
	        color: red;  
         }
    </style>
  </head>
  
  <body>
     <a href="${pageContext.request.contextPath}/manage/add.jsp" target="right">添加分类</a><br/>
     <a href="${pageContext.request.contextPath}/CategoryServlet?op=listAllCategory" target="right">查看分类</a><br/>
     <a href="${pageContext.request.contextPath}/BookServlet?op=addBookUI" target="right">添加书籍</a><br/>
     <a href="${pageContext.request.contextPath}/BookServlet?op=listAll" target="right">查找书籍</a><br/>
  </body>
</html>
