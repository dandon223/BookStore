package com.bookstore.book;

import com.bookstore.BookStoreApplication;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookStoreApplicationTests {

	@Autowired
	private BookStoreApplication bookStoreApplication;
	@Test
	public void contextLoads() throws Exception {
		assertThat(bookStoreApplication).isNotNull();
	}

}
