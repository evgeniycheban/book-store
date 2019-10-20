package com.example.bookstore.service;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookFilterDto;
import org.springframework.data.domain.Page;

public interface BookService {

    void save(BookDto bookDto);

    void delete(long id);

    BookDto get(long id);

    Page<BookDto> getAll(BookFilterDto filter);

}
