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
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
          h1{
             text-align: center;
             color:red
          }
          #one
          {
              text-align:right;
          }
          a{
              font-size:18px;
              font-weight: 30;
          }
    </style>
  </head>
  
  <body>
            <h1>
                 XXXX书城
            </h1>
            <div id="one">欢迎您，登录，注册
                 <a href="${pageContext.request.contextPath}/myCart.jsp">我的购物车</a>
            </div>
            <div>
                       所有分类:
            <c:forEach items="${categories}" var="category">
                     <a href="${pageContext.request.contextPath}/BookServlet?op=listAllBookByCid&cid=${category.id}">${category.cname}</a>
            </c:forEach>
            </div>
            <br/><br/>
            <br/><br/>

