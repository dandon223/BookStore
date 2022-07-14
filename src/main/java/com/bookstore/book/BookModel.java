package com.bookstore.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "BOOK_MODEL")
public class BookModel {
  @Id
  @GeneratedValue
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "author", nullable = false)
  private String author;
  @Column(name = "publishYear", nullable = false)
  private int publishYear;
}
