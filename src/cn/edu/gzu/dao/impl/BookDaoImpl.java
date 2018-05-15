package cn.edu.gzu.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.edu.gzu.dao.BookDao;
import cn.edu.gzu.domain.Book;
import cn.edu.gzu.domain.Category;
import cn.edu.gzu.utils.JdbcUtils;
import cn.edu.gzu.web.bean.Page;

public class BookDaoImpl implements BookDao {
    QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
	@Override
	public void addBook(Book book) {
		String sql="insert into book(id,bname,author,pagenum,price,description,path,oldImageName,newImageName,cid)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		try {
			runner.update(sql,new Object[]{book.getId(),book.getBname()
					,book.getAuthor(),book.getPageNum(),book.getPrice(),
					book.getDescription(),book.getPath(),book.getOldImageName(),
					book.getNewImageName(),book.getCategory().getId()});
		} catch (SQLException e) {
			throw new RuntimeException("Ìí¼ÓÊ§°Ü");
		}
	}

	@Override
	public void deleteBook(String id) {
		String sql="delete from book where id=?";
		try {
			runner.update(sql, id);
		} catch (SQLException e) {
			throw new RuntimeException("É¾³ýÊ§°Ü");
		}
		
	}

	@Override
	public void updateBook(Book book) {
		
	}

	@Override
	public Book findBookByID(String id) {
        String sql="select * from book where id=?";
        try {
			Book book=(Book) runner.query(sql, id, new BeanHandler(Book.class));
			return book;
		} catch (SQLException e) {
			throw new RuntimeException("²éÑ¯Òì³£");
		}
		
	}

	@Override
	public int findBookRecords() {
		String sql="select count(*) from book";
		try {
			Number number=(Number) runner.query(sql, new ScalarHandler());
			return number.intValue();
		} catch (SQLException e) {
			throw new RuntimeException("²éÑ¯×Ü¼ÇÂ¼ÊýÊ§°Ü");
		}
		
	}

	@Override
	public Page findPageBook(int index,int pageSize) {
		String sql="select *from book limit ?,?";
		try {
			List<Book> books=(List<Book>) runner.query(sql, new BeanListHandler(Book.class),new Object[]{index,pageSize});
			//System.out.println(books.size());
		    for(Book book:books)
		    {
		    	//System.out.println(book.getPrice());
		    	sql="select *from category where id=(select cid from book where id=?)";
		    	Category category=(Category) runner.query(sql,book.getId(), new BeanHandler(Category.class));
		    	book.setCategory(category);
		    }
			Page page=new Page();
			
		    page.setRecords(books);
		    return page;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("²éÑ¯Ê§°Ü");
		}
		
	}

	@Override
	public int findBookRecordsByCid(String cid) {
		String sql="select count(*) from book where cid=?";
		try {
			Number number=(Number) runner.query(sql,cid, new ScalarHandler());
			return number.intValue();
		} catch (SQLException e) {
			throw new RuntimeException("²éÑ¯×Ü¼ÇÂ¼ÊýÊ§°Ü");
		}
	
	}

	@Override
	public Page findPageBookByCid(int index, int pageSize, String cid) {
		String sql="select *from book where cid=? limit ?,? ";
		try {
			List<Book> books=(List<Book>) runner.query(sql, new BeanListHandler(Book.class),new Object[]{cid,index,pageSize});
			//System.out.println(books.size());
		    for(Book book:books)
		    {
		    	System.out.println(book.getPrice());
		    	sql="select *from category where id=?";
		    	Category category=(Category) runner.query(sql,cid, new BeanHandler(Category.class));
		    	book.setCategory(category);
		    }
			Page page=new Page();
			
		    page.setRecords(books);
		    return page;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("²éÑ¯Ê§°Ü");
		}
	}



}
