package cn.edu.gzu.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.gzu.domain.Admin;
import cn.edu.gzu.domain.Category;
import cn.edu.gzu.service.BusinessService;
import cn.edu.gzu.service.impl.BusinessServiceImpl;
import cn.edu.gzu.utils.TypeConversion;

public class CategoryServlet extends HttpServlet {

	BusinessService service=new  BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String op=request.getParameter("op");
        if("add".equals(op))
        	addCategory(request,response);
        if("listAllCategory".equals(op))
        	listAllCategory(request,response);
        if("deleteCategory".equals(op))
        	deleteCategory(request,response);
        if("updateCategory".equals(op))
        	updateCategory(request,response);
        if("login".equals(op))
        	findAdmin(request,response);
	}

	
	private void findAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		Admin admin =service.findAdmin(name,pwd);
		if(admin!=null){
			request.getRequestDispatcher("/manage/index.jsp").forward(request, response);
			return ;
		}
		request.getRequestDispatcher("/manage/login.jsp").forward(request, response);
	}


	private void updateCategory(HttpServletRequest request,
			HttpServletResponse response) {
		String id=request.getParameter("id");
		Category category=service.findCategory(id);
		request.setAttribute("category", category);
		try {
			request.getRequestDispatcher("/manage/add.jsp").forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException("转发失败");
		}
	}


	private void deleteCategory(HttpServletRequest request,
			HttpServletResponse response) {
		String id=request.getParameter("id");
		service.deleteCategory(id);
		try {
			response.getWriter().write("<script type='text/javascript'>alert('添加成功')</script>");
			//request.getRequestDispatcher("/CategoryServlet?op=listAllCategory").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void listAllCategory(HttpServletRequest request,
			HttpServletResponse response) {
		List<Category> categories=service.findCategoryList();
		request.setAttribute("categories", categories);
		
		try {
			String url=request.getParameter("url");
			if(url!=null)
			{
				request.getRequestDispatcher("/main.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("/manage/listCategory.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("转发异常");
		} 
		
	}


	private void addCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		   Category category=TypeConversion.convert(request, Category.class);
		   if(null==category.getId()||"".equals(category.getId()))
		   {
			   service.addCategory(category);
		   }
		   else
		   {
			   service.updateCategory(category);
			   
		   }
		   
		   response.getWriter().write("<script type='text/javascript'>alert('添加成功')</script>");
		  // response.sendRedirect(request.getContextPath()+"/index.jsp");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
