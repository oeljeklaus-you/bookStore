package cn.edu.gzu.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.edu.gzu.domain.Book;
import cn.edu.gzu.domain.Category;
import cn.edu.gzu.service.impl.BusinessServiceImpl;
import cn.edu.gzu.web.bean.Cart;
import cn.edu.gzu.web.bean.Page;

public class BookServlet extends HttpServlet {
	 BusinessServiceImpl service=new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       
		String op=request.getParameter("op");
		if("addBook".equals(op))
		{	
			addBook(request,response);		
		}
		if("addBookUI".equals(op))
		{
			List<Category> categories=service.findCategoryList();
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("/manage/addBook.jsp").forward(request, response);
		}
		if("listAll".equals(op))
		{
			listAllBook(request, response);
		}
		if("listAllBookByCid".equals(op))
		{
			listAllBookByCid(request, response);
		}
		if("MyCart".equals(op))
		{
			MyCart(request, response);
		}
		if("deleteBookFromCart".equals(op))
		{
			deleteBookFromCart(request, response);
		}
		if("UpdateBookFromCart".equals(op))
		{
			updateBookFromCart(request, response);
		}
	}


	private void updateBookFromCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		    String bid=request.getParameter("bid");
		    String num=request.getParameter("num");
	        HttpSession session=request.getSession();
			Cart myCart=(Cart) session.getAttribute("myCart");
			myCart.UpdateBookFromCart(bid,num);
			response.getWriter().write("<script type='text/javascript'>alert('更新成功')</script>");
			request.getRequestDispatcher("/myCart.jsp").forward(request, response);
	}


	private void deleteBookFromCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
        String bid=request.getParameter("bid");
        HttpSession session=request.getSession();
		Cart myCart=(Cart) session.getAttribute("myCart");
		myCart.deleteBookFromCart(bid);
		response.getWriter().write("<script type='text/javascript'>alert('删除成功')</script>");
		request.getRequestDispatcher("/myCart.jsp").forward(request, response);
	}


	private void MyCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//判断session里面是否有购物车
		HttpSession session=request.getSession();
		Cart myCart=(Cart) session.getAttribute("myCart");
		if(myCart==null)
		{
			myCart=new Cart();
		}
		//获取想要加入购物车的商品
		String bid=request.getParameter("bid");
		Book book=service.findBookByID(bid);
		//加入购物车
		myCart.addBook(book);
		//放入session中
		session.setAttribute("myCart", myCart);
		System.out.println(myCart.getTotalNum());
		//提示加入购物车成功
		response.getWriter().write("<script type='text/javascript'>alert('添加成功')</script>");
		request.getRequestDispatcher("/main.jsp").forward(request, response);
	}


	private void listAllBookByCid(HttpServletRequest request,
			HttpServletResponse response) {
		int pageNum=1;
		String cid=request.getParameter("cid");
		if(request.getParameter("pageNum")!=null||"".equals(request.getParameter("pageNum")))
		{
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}
		Page page=service.findPageBookAndCid(pageNum,cid);
		request.setAttribute("page", page);
		try {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			
		} catch (Exception e) {
            throw new RuntimeException("转发失败");
		}
	}


	private void listAllBook(HttpServletRequest request,
			HttpServletResponse response) {
		int pageNum=1;
		if(request.getParameter("pageNum")!=null||"".equals(request.getParameter("pageNum")))
		{
			pageNum=Integer.parseInt(request.getParameter("pageNum"));
		}
		Page page=service.findPageBook(pageNum);
		request.setAttribute("page", page);
		try {
			if(request.getParameter("url")!=null)
			{
				request.getRequestDispatcher("/main.jsp").forward(request, response);
				return ;
			}
			request.getRequestDispatcher("/manage/listAllBook.jsp").forward(request, response);
			
		} catch (Exception e) {
            throw new RuntimeException("转发失败");
		}
	}


	private void addBook(HttpServletRequest request,
			HttpServletResponse response) {
		Book book=new Book();
		UploadPrepared(request, book);
		//response.getWriter().write("<script type='text/javascript'>alert('添加成功')<script>");
		try {
			service.addBook(book);
			response.getWriter().write("<script type='text/javascript'>alert('添加成功')</script>");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("服务器出故障了");
		}
	}


	private void UploadPrepared(HttpServletRequest request, Book book) {
		//1.判断文件类型是否正确
		boolean b=ServletFileUpload.isMultipartContent(request);
		if(!b)
			throw new RuntimeException("文件类型不正确");
		//2.获取diskFileItem的工厂 设置缓存的大小和存放的区域
		DiskFileItemFactory factory =new DiskFileItemFactory();
		//3.创建ServletFileUpload 核心解析类
		ServletFileUpload upload=new ServletFileUpload(factory);
		//获取表单提交的各个输入域
		try {
			//获取全部的请求输入
			List<FileItem> items=upload.parseRequest(request);
			for(FileItem item:items)
			{
				if(item.isFormField())
					//处理一般的是输入
					processFormField(item,book);
				else
					//处理上传的输入
					processUploadField(item,book);
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new RuntimeException("获取各个输入域失败");
		}
	}


	private void processUploadField(FileItem item, Book book) {
		String fileName=item.getName();
		String oldImageName=fileName;
		if(fileName.indexOf("\\")>=1)
		{
			oldImageName=fileName.substring(fileName.lastIndexOf("\\")+1);
		}
		String fileType=fileName.substring(fileName.lastIndexOf(".")+1);
		String newImageName=UUID.randomUUID().toString()+"."+fileType;
		book.setOldImageName(oldImageName);
		book.setNewImageName(newImageName);
		String storeDirectory = getServletContext().getRealPath("/images");//存放目录的真实路径
		String newPath =makeNewPath(storeDirectory,newImageName);
		File file = new File(storeDirectory+"/"+newPath+"/"+newImageName);
		book.setPath(newPath);
		try {
			/*//构建输入流
			InputStream input=item.getInputStream();
			//构建输出流
			OutputStream out=new FileOutputStream(newPath);
		    int len=0;
		    byte[] b=new byte[1024];
		    while((len=input.read(b))>0)
		    {
		    	out.write(b, 0, len);
		    }
		    input.close();
		    out.close();*/
			
			item.write(file);
		} catch (Exception e) {
			throw new RuntimeException("上传遇到一些问题!");
		}
		
	}


	private void processFormField(FileItem item, Book book) {
		        //获取输入的名字
				String name=item.getFieldName();
				//获取输入域的值
				String value="";
				
				try {
					value = item.getString("UTF-8");
					
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				try {
					if(name.equals("price"))
					{
						book.setPrice(Float.parseFloat(value));
						//System.out.println(book.getPrice());
					}
					if(name.equals("category"))
					{
						Category category=new Category();
						category.setId(value);
						book.setCategory(category);
					}
					else
					{
						BeanUtils.setProperty(book, name, value);
					    
					}
					
				} catch (Exception e) {
					throw new RuntimeException("设置bean的属性值失败");
				}
	}


	private String makeNewPath(String storeDirectory, String newImageName) {
		int hashCode = newImageName.hashCode();
		int dir1 = hashCode&0xf;
		int dir2 = (hashCode&0xf0)>>4;
		
		String path = dir1+"/"+dir2;
		File file = new File(storeDirectory+"/"+path);
		if(!file.exists()){
			file.mkdirs();
		}
		return path;
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
