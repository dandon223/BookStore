package com.bookstore.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void shouldReturnNewBookIdAfterAddingBook() throws Exception {
        when(bookRepository.addBook(any())).thenReturn(3L);
        Long result = bookService.addBook(new Book("Szumilas","Jacek",1999));
        assertThat(result).isEqualTo(3L);
    }
    @Test
    public void shouldReturnTrueAfterBookDelete() throws Exception{
        when(bookRepository.deleteBook(2L)).thenReturn(true);
        Boolean result = bookService.deleteBook(2L);
        assertThat(result).isEqualTo(true);
    }
    @Test
    public void shouldReturnListOfBooks() throws Exception {
        when(bookRepository.getBooks()).thenReturn(
                Stream.of(
                                new BookModel(1L,"Narnia 1","Author 1",1999)
                                ,new BookModel(2L,"Cyberiada","Author 2",2001))
                        .collect(Collectors.toList()));
        List<BookListItem> result = bookService.getBooks();
        assertThat(result.get(0).getName()).isEqualTo("Narnia 1");
        assertThat(result.size()).isEqualTo(2);
    }
    @Test
    public void shouldReturnTrueAfterBookUpdate() throws Exception {
        when(bookRepository.updateBook(eq(2L),any())).thenReturn(true);
        Boolean result = bookService.updateBook(2L,new Book("Name 1","Author 1",1999));
        assertThat(result).isEqualTo(true);
    }
}
