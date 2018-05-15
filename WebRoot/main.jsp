<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/top.jsp"%>
  <table border="0" width="400px"> 
    	<tr>
    		<c:forEach items="${page.records}" var="b">
    			<td>
    				<img src="${pageContext.request.contextPath}/images/${b.path}/${b.newImageName}"/><br/>
    				书名：${b.bname}<br/>
    				作者：${b.author}<br/>
    				售价：${b.price}<br/>
    				<a href="${pageContext.request.contextPath}/BookServlet?op=MyCart&bid=${b.id}">放入购物车</a>
    			</td>
    		</c:forEach>
    	</tr>
    	  共${page.totalPage}页 &nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/BookServlet?op=listAllBookByCid&cid=${page.records[0].category.id}&pageNum=${page.pageNum-1>0?(page.pageNum-1):page.pageNum}">上一页</a>    <a href="${pageContext.request.contextPath}/BookServlet?op=listAllBookByCid&cid=${page.records[0].category.id}&pageNum=${page.pageNum+1>page.totalPage?page.totalPage:(page.pageNum+1)}">下一页</a>  
  </table>
    
  </body>
</html>
