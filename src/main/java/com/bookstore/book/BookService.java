package com.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class BookService {

    private final BookDataBase bookDataBase;

    @Autowired
    public BookService(BookDataBase bookDataBase) {
        this.bookDataBase = bookDataBase;
    }

    public List<BookListItem> getBooks() {
        List<BookModel> books = bookDataBase.getBooks();
        List<BookListItem> result = new ArrayList<>();
        for( BookModel book : books){
            result.add(new BookListItem(book.getId(),book.getName()));
        }
        return result;
    }

    public Long addBook(BookModel book) {
        return bookDataBase.addBook(book);
    }

    public boolean deleteBook(Long id) {
        return bookDataBase.deleteBook(id);
    }

    public boolean updateBook(Long id, BookModel book) {
        return bookDataBase.updateBook(id,book);
    }
}
