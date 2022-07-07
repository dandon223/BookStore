package com.bookstore.book;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BookDataBase implements BookRepository{
    List<BookModel> books;
    public BookDataBase(){
        books = initBooks();
    }
    @Override
    public List<BookModel> getBooks() {
        return books;
    }
    @Override
    public Long addBook(BookModel book){
        Long id = getBiggestId();
        if (id< 1L)
            id=1L;
        book.setId(id+1);
        books.add(book);
        return id+1;
    }
    @Override
    public boolean deleteBook(Long id){
        for(int i = 0 ; i < books.size(); i++){
            if(Objects.equals(books.get(i).getId(), id)){
                books.remove(i);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean updateBook(Long id, BookModel book){
        for(int i = 0 ; i < books.size(); i++){
            if(Objects.equals(books.get(i).getId(), id)){
                book.setId(id);
                books.set(i,book);
                return true;
            }
        }
        return false;
    }
    private Long getBiggestId(){
        Long result = -1L;
        for (BookModel book : books) {
            if (book.getId() > result) {
                result = book.getId();
            }
        }
        return result;
    }
    private List<BookModel> initBooks(){
        List<BookModel> books = new ArrayList<>();
        books.add(new BookModel(1L,"Narnia 1","C.S Lewis",1953));
        books.add(new BookModel(2L,"Cyberiada","Stanisław Lem",1965));
        return books;
    }
}
