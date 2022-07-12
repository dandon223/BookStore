package com.bookstore.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
  private Long id;
  private String name;
  private String author;
  private int publishYear;

  public Book() {}

  public Book(String name, String author, int publishYear) {
    this.name = name;
    this.author = author;
    this.publishYear = publishYear;
  }

  public Book(Long id, String name, String author, int publishYear) {
    this.id = id;
    this.name = name;
    this.author = author;
    this.publishYear = publishYear;
  }

  @Override
  public String toString() {
    return "Book{"
        + "id=" + id
        + ", name='" + name + '\''
        + ", author='" + author + '\''
        + ", publishYear=" + publishYear
        + '}';
  }
}
