package junit.test;

import org.junit.Test;

import cn.edu.gzu.domain.Book;
import cn.edu.gzu.domain.Category;
import cn.edu.gzu.service.impl.BusinessServiceImpl;

public class BookServiceTest {
	BusinessServiceImpl service=new BusinessServiceImpl();
	@Test
	public void testAddBook()
	{
		Book book=new Book();
		Category category=new Category();
		category.setId("335d50f2-2f89-4573-93ad-647525ca7dd8");
		book.setAuthor("白岩松");
		book.setBname("白说");
		book.setDescription("一本好书");
		book.setNewImageName("hhsd");
		book.setOldImageName("4334");
		book.setPageNum(30);
		book.setPath("43434");
	    book.setPrice(70);
	    book.setPath("4234234");
	    book.setCategory(category);
	    service.addBook(book);
	}
	@Test
	public void testFindBook()
	{
		Book book=service.findBookByID("840a8c2c-b661-4ba1-8987-9faf9a4143a2");
		System.out.println(book);
	}
}
