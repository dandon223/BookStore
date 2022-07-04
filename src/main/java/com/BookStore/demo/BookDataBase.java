package com.BookStore.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class BookDataBase {
    List<Book> books;
    public BookDataBase(){
        books = initBooks();
    }

    public List<Book> getBooks() {
        return books;
    }
    public void addBook(Book book){
        books.add(book);
    }
    public void deleteBook(String name){
        for(int i = 0 ; i < books.size(); i++){
            if(books.get(i).getName().equals(name)){
                books.remove(i);
                return;
            }
        }
    }
    public void updateBook(String name,Book book){
        for(int i = 0 ; i < books.size(); i++){
            if(books.get(i).getName().equals(name)){
                books.set(i,book);
                return;
            }
        }
    };
    private List<Book> initBooks(){
        List<Book> books = new ArrayList<>();
        books.add(new Book("Narnia 1","C.S Lewis",1953));
        books.add(new Book("Cyberiada","Stanisław Lem",1965));
        return books;
    }
}
