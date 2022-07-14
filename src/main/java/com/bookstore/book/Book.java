package com.bookstore.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Book {
  private Long id;
  private String name;
  private String author;
  private int publishYear;

}
