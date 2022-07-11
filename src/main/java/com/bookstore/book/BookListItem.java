package com.bookstore.book;

import lombok.Getter;
import lombok.Setter;

@Getter
public class BookListItem {
  private Long id;
  private String name;

  public BookListItem() {
  }

  public BookListItem(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
