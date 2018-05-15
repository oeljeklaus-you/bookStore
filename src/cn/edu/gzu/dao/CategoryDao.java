package cn.edu.gzu.dao;

import java.util.List;

import cn.edu.gzu.domain.Admin;
import cn.edu.gzu.domain.Category;

public interface CategoryDao {
	 void add(Category cateogry);
     void delete(String id);
     void update(Category category);
     Category find(String id);
     List<Category> findCategoryList();
     Admin findAdmin(String name,String pwd);
}
