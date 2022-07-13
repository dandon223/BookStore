package com.bookstore.book;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class Book {
  private Long id;
  private String name;
  private String author;
  private int publishYear;

  public Book(String name, String author, int publishYear) {
    this.name = name;
    this.author = author;
    this.publishYear = publishYear;
  }
}
