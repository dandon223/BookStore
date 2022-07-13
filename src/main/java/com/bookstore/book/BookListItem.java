package com.bookstore.book;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Data
@NoArgsConstructor
public class BookListItem {
  private Long id;
  private String name;

  public BookListItem(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
