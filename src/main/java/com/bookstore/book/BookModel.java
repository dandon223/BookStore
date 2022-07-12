package com.bookstore.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BookModel {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String author;
  private int publishYear;

  public BookModel() {
  }

  public BookModel(Long id, String name, String author, int publishYear) {
    this.id = id;
    this.name = name;
    this.author = author;
    this.publishYear = publishYear;
  }

  public BookModel(String name, String author, int publishYear) {
    this.name = name;
    this.author = author;
    this.publishYear = publishYear;
  }
}
