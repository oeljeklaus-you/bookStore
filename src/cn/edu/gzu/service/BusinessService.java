package cn.edu.gzu.service;

import java.util.List;

import cn.edu.gzu.domain.Admin;
import cn.edu.gzu.domain.Book;
import cn.edu.gzu.domain.Category;
import cn.edu.gzu.web.bean.Page;

public interface BusinessService {
      //��ӷ���	
      void addCategory(Category cateogry);
      //ɾ������
      void deleteCategory(String id);
      //���·���
      void updateCategory(Category category);
      //���ҷ���
      Category findCategory(String id);
      //��ѯ���з���
      List<Category> findCategoryList();
      //���ͼ��
      void addBook(Book book);
      //ɾ��ͼ��
      void deleteBook(String id);
      //����ͼ��
      void updateBook(Book book);
      //����ͼ��
      Book findBookByID(String id);
      //��ѯ���е�ͼ��
      Page findPageBook(int pageNum);
      Page findPageBookAndCid(int pageNum, String cid);
	 Admin findAdmin(String name, String pwd);
}
