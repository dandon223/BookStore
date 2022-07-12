package com.bookstore.book;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
  @NotEmpty(message = "field.canNotBeEmpty")
  private String name;
  @NotEmpty(message = "field.canNotBeEmpty")
  private String author;
  @Max(value = 9999, message = "field.tooBig")
  private int publishYear;

  public BookRequest() {
  }

  public BookRequest(String name, String author, int publishYear) {
    this.name = name;
    this.author = author;
    this.publishYear = publishYear;
  }

  @Override
  public String toString() {
    return "BookRequest{"
        + "name='" + name + '\''
        + ", author='" + author + '\''
        + ", publishYear=" + publishYear
        + '}';
  }
}
