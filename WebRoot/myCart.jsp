<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/top.jsp"></jsp:include>
    <table border="1" cellspacing="0" cellpadding="10" width="500">
        <tr>
           <td>序号</td>
           <td>商品名称</td>
           <td>商品单价</td>
           <td>商品数量</td>
           <td>小计</td>
           <td>操作</td>
        </tr>
       
           <c:forEach items="${sessionScope.myCart.items}" var="cartItem" varStatus="index">
        	  <tr>
        		<td>${index.count}</td>
           		<td>${cartItem.value.book.bname }</td>
           		<td>${cartItem.value.book.price }</td>
           		<td><input type="text" size="4" id="num" value="${cartItem.value.num }" onchange="changeNum('${cartItem.key}','${cartItem.value.num }')"/></td>
           		<td>${cartItem.value.price }</td>
           		<td><a href="javascript:deleteBook('${cartItem.key}')">删除</a></td>
           	  <tr>
           </c:forEach>
        <tr  style="text-align:right">
            <td colspan="6">总计:${sessionScope.myCart.totalPrice}&nbsp;<a href="">结算</a></td>
        </tr>
    </table>
	<script type="text/javascript">
	     function deleteBook(id)
	     {
	    	var sure= window.confirm("确定要删除该项吗？");
	    	if(sure)
	    	  {
	    		 window.location.href="${pageContext.request.contextPath}/BookServlet?op=deleteBookFromCart&bid="+id;
	    	  }
	     }
	     function changeNum(id,oldnum)
	     {
	    	 var sure= window.confirm("确定要修改数量吗？");
	    	 if(sure)
             {
	    		 var num=document.getElementById("num").value;
	    		 window.location.href="${pageContext.request.contextPath}/BookServlet?op=UpdateBookFromCart&bid="+id+"&num="+num;
             }
	    	 else
	         {
	    		 document.getElementById("num").value=oldnum;
	         }
	     }
	</script>
	
  </body>
</html>
