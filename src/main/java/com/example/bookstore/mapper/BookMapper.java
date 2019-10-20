package com.example.bookstore.mapper;

import com.example.bookstore.domain.Book;
import com.example.bookstore.dto.BookDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto map(Book book);

    Book map(BookDto bookDto);

}
