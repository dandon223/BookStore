package com.bookstore.book;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
  BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );

  BookListItem bookModeltoBookListItem(BookModel bookModel);
  BookModel booktoBookModel(Book book);
  Book bookRequesttoBook(BookRequest bookRequest);
}
