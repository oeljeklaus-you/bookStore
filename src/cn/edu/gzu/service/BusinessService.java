package cn.edu.gzu.service;

import java.util.List;

import cn.edu.gzu.domain.Admin;
import cn.edu.gzu.domain.Book;
import cn.edu.gzu.domain.Category;
import cn.edu.gzu.web.bean.Page;

public interface BusinessService {
      //添加分类	
      void addCategory(Category cateogry);
      //删除分类
      void deleteCategory(String id);
      //更新分类
      void updateCategory(Category category);
      //查找分类
      Category findCategory(String id);
      //查询所有分类
      List<Category> findCategoryList();
      //添加图书
      void addBook(Book book);
      //删除图书
      void deleteBook(String id);
      //更新图书
      void updateBook(Book book);
      //查找图书
      Book findBookByID(String id);
      //查询所有的图书
      Page findPageBook(int pageNum);
      Page findPageBookAndCid(int pageNum, String cid);
	 Admin findAdmin(String name, String pwd);
}
