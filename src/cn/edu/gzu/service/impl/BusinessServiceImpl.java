package cn.edu.gzu.service.impl;

import java.util.List;
import java.util.UUID;

import cn.edu.gzu.dao.BookDao;
import cn.edu.gzu.dao.CategoryDao;
import cn.edu.gzu.dao.OrdersDao;
import cn.edu.gzu.dao.impl.BookDaoImpl;
import cn.edu.gzu.dao.impl.CategoryDaoImpl;
import cn.edu.gzu.dao.impl.OrdersDaoImpl;
import cn.edu.gzu.domain.Admin;
import cn.edu.gzu.domain.Book;
import cn.edu.gzu.domain.Category;
import cn.edu.gzu.domain.Orders;
import cn.edu.gzu.service.BusinessService;
import cn.edu.gzu.web.bean.Page;

public class BusinessServiceImpl implements BusinessService {
	CategoryDao categoryDao=new CategoryDaoImpl();
    BookDao dao=new BookDaoImpl();
	private OrdersDao ordersDao=new OrdersDaoImpl();
	@Override
	public void addCategory(Category category) {
		category.setId(UUID.randomUUID().toString());
		categoryDao.add(category);
	}

	@Override
	public void deleteCategory(String id) {
		categoryDao.delete(id);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.update(category);
	}

	@Override
	public Category findCategory(String id) {
		return categoryDao.find(id);
	}

	@Override
	public List<Category> findCategoryList() {
		return categoryDao.findCategoryList();
	}

	@Override
	public void addBook(Book book) {
		book.setId(UUID.randomUUID().toString());
		dao.addBook(book);	
	}

	@Override
	public void deleteBook(String id) {
		dao.deleteBook(id);
	}

	@Override
	public void updateBook(Book book) {
		dao.updateBook(book);
	}

	@Override
	public Book findBookByID(String id) {
		return dao.findBookByID(id);
	}

	@Override
	public Page findPageBook(int pageNum) {
		int totalRecords=dao.findBookRecords();
		Page page=new Page();
		page.setPageNum(pageNum);
		page= dao.findPageBook(page.getIndex(),page.getPageSize());
		page.setTotalRecords(totalRecords);
		page.setPageNum(pageNum);
		return page;
	}

	public Page findPageBookAndCid(int pageNum, String cid) {
		int totalRecords=dao.findBookRecordsByCid(cid);
		Page page=new Page();
		page.setPageNum(pageNum);
		page= dao.findPageBookByCid(page.getIndex(),page.getPageSize(),cid);
		page.setTotalRecords(totalRecords);
		page.setPageNum(pageNum);
		return page;
	}
	
	public Admin findAdmin(String name,String pwd)
	{
		return categoryDao.findAdmin(name,pwd);
	}
	
	public void saveOrders(Orders order)
	{
		ordersDao.saveOrders(order);
	}
	
	public Orders findOrdersById(String id)
	{
		return ordersDao.findOrdersById(id);
	}
}
