package com.bookstore.book;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
public class BookRequest {
        @NotEmpty(message = "field.canNotBeEmpty")
        private String name;
        @NotEmpty(message = "field.canNotBeEmpty")
        private String author;
        @Max(value=9999,message ="Max year is 9999.")
        private int publishYear;

        public BookRequest(){}
        public BookRequest(String name, String author, int publishYear) {
            this.name = name;
            this.author = author;
            this.publishYear = publishYear;
        }

        @Override
        public String toString() {
            return "BookRequest{" +
                    "name='" + name + '\'' +
                    ", author='" + author + '\'' +
                    ", publishYear=" + publishYear +
                    '}';
        }
}
