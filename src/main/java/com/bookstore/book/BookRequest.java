package com.bookstore.book;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookRequest {
  @NotEmpty(message = "field.canNotBeEmpty")
  private String name;
  @NotEmpty(message = "field.canNotBeEmpty")
  private String author;
  @Max(value = 9999, message = "field.tooBig")
  private int publishYear;
}
