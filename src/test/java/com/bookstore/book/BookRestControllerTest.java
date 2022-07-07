package com.bookstore.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void shouldReturnNewBookIdAfterAddingBook() throws Exception {
        when(bookService.addBook(any())).thenReturn(3L);
        this.mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"name":"Szumilas",
                         "author":"Jacek",
                         "publishYear":1999}
                        """)).andExpect(status().isOk()).andExpect(content().string("3"));
    }
    @Test
    public void shouldReturnErrorAfterFailAddingBook() throws Exception {
        when(bookService.addBook(any())).thenReturn(3L);
        this.mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"name":"Szumilas",
                         "publishYear":1999}
                        """)).andDo(print()).andExpect(status().isBadRequest());
    }
    @Test
    public void shouldReturnTrueAfterBookDelete() throws Exception{
        when(bookService.deleteBook(2L)).thenReturn(true);
        this.mockMvc.perform(delete("/books/2")).andExpect(status().isOk()).andExpect(content().string("true"));
    }

    @Test
    public void shouldReturnListOfBooks() throws Exception {
        when(bookService.getBooks()).thenReturn(
                Stream.of(
                        new BookListItem(1L,"Narnia 1")
                                ,new BookListItem(2L,"Cyberiada"))
                .collect(Collectors.toList()));
        this.mockMvc.perform(get("/books")).andExpect(status().isOk())
                .andExpect(content().json("""
                        [{"id":1,"name":"Narnia 1"},{"id":2,"name":"Cyberiada"}]
                        """));
    }
    @Test
    public void shouldReturnTrueAfterBookUpdate() throws Exception {
        when(bookService.updateBook(eq(2L),any())).thenReturn(true);
        this.mockMvc.perform(put("/books/2").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"name":"Szumilas",
                         "author":"Jacek",
                         "publishYear":1999}
                        """)).andExpect(status().isOk()).andExpect(content().string("true"));
    }
}
