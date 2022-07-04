package com.BookStore.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BookDataBase {
    List<Book> books;
    public BookDataBase(){
        books = initBooks();
    }

    public List<Book> getBooks() {
        return books;
    }
    public Long addBook(Book book){
        Long id = getBiggestId();
        if (id< 1L)
            id=1L;
        book.setId(id+1);
        books.add(book);
        return id+1;
    }
    public boolean deleteBook(Long id){
        for(int i = 0 ; i < books.size(); i++){
            if(Objects.equals(books.get(i).getId(), id)){
                books.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean updateBook(Long id,Book book){
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
        for(int i = 0 ; i < books.size(); i++){
            if(books.get(i).getId() > result){
                result = books.get(i).getId();
            }
        }
        return result;
    }
    private List<Book> initBooks(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L,"Narnia 1","C.S Lewis",1953));
        books.add(new Book(2L,"Cyberiada","Stanisław Lem",1965));
        return books;
    }
}
