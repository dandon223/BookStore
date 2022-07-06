package com.bookstore.demo;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

public class BookRequest {
        @NotEmpty(message = "Name can not be empty.")
        private String name;
        @NotEmpty(message = "Author can not be empty.")
        private String author;
        @Max(value=9999,message ="Max year is 9999.")
        private int publishYear;

        public BookRequest(){}
        public BookRequest(String name, String author, int publishYear) {
            this.name = name;
            this.author = author;
            this.publishYear = publishYear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getPublishYear() {
            return publishYear;
        }

        public void setPublishYear(int publishYear) {
            this.publishYear = publishYear;
        }

}
