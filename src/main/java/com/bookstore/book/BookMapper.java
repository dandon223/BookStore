package com.bookstore.book;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
  BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );

  BookListItem mapToBookListItem(BookModel bookModel);
  BookModel mapToBookModel(Book book);
  Book mapToBook(BookRequest bookRequest);
}
