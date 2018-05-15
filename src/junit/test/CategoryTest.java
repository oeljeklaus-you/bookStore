package junit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.edu.gzu.dao.impl.CategoryDaoImpl;
import cn.edu.gzu.domain.Category;

public class CategoryTest {
    CategoryDaoImpl dao=new CategoryDaoImpl();
    Category category=new Category();
	
	@Test
	public void testAdd() {
		category.setId("1");
		category.setCname("test");
		category.setCdesc("我要test");
		dao.add(category);
	}

	@Test
	public void testDelete() {
		dao.delete("1");
	}

	@Test
	public void testUpdate() {
		category.setId("1");
		category.setCname("test");
		category.setCdesc("这是一个update");
		dao.update(category);
	}

	@Test
	public void testFind() {
		System.out.println(dao.find("1").getCname());
	}

	@Test
	public void testFindCategoryList() {
		System.out.println(dao.findCategoryList().get(0).getCname());
	}

}
