package cn.edu.gzu.dao;

import cn.edu.gzu.domain.Book;
import cn.edu.gzu.web.bean.Page;

public interface BookDao {

	void addBook(Book book);

	void deleteBook(String id);

	void updateBook(Book book);

	Book findBookByID(String id);

	int findBookRecords();

	Page findPageBook(int index,int pageSize);

	int findBookRecordsByCid(String cid);

	Page findPageBookByCid(int index, int pageSize, String cid);


}
