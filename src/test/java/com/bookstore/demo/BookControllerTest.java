package com.bookstore.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnNewBookIdAfterAddingBook() throws Exception {
        this.mockMvc.perform(post("/books").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"name":"Szumilas",
                         "author":"Jacek",
                         "publishYear":1999}
                        """)).andExpect(status().isOk()).andExpect(content().string("3"));
    }
    @Test
    public void shouldReturnTrueAfterBookDelete() throws Exception{
        this.mockMvc.perform(delete("/books/2")).andExpect(status().isOk()).andExpect(content().string("true"));
    }

    @Test
    public void shouldReturnListOfBooks() throws Exception {
        this.mockMvc.perform(get("/books")).andExpect(status().isOk())
                .andExpect(content().json("""
                        [{"id":1,"name":"Narnia 1"},{"id":2,"name":"Cyberiada"}]
                        """));
    }
    @Test
    public void shouldReturnTrueAfterBookUpdate() throws Exception {
        this.mockMvc.perform(put("/books/2").contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"name":"Szumilas",
                         "author":"Jacek",
                         "publishYear":1999}
                        """)).andExpect(status().isOk()).andExpect(content().string("true"));
    }
}
