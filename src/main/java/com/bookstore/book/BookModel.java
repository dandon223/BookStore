package com.bookstore.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@Table(name = "BOOK_MODEL")
public class BookModel {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "author")
  private String author;
  @Column(name = "publishYear")
  private int publishYear;

  public BookModel(String name, String author, int publishYear) {
    this.name = name;
    this.author = author;
    this.publishYear = publishYear;
  }
}
