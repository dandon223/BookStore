package com.bookstore.demo;

public class BookRequest {
        private String name;
        private String author;
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
