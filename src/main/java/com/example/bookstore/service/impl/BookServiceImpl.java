package com.example.bookstore.service.impl;

import com.example.bookstore.domain.Book;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookFilterDto;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.specification.BookFilterSpecification;
import com.example.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    @Override
    public void save(BookDto bookDto) {
        Book book = mapper.map(bookDto);
        repository.save(book);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public BookDto get(long id) {
        return repository.findById(id)
                .map(mapper::map)
                .orElseThrow(() -> new IllegalStateException("Book with id: <" + id + "> not found"));
    }

    @Override
    public Page<BookDto> getAll(BookFilterDto filter) {
        int page = filter.getPage() == null ? 1 : filter.getPage();
        int size = filter.getSize() == null ? 10 : filter.getSize();

        return repository.findAll(new BookFilterSpecification(filter), PageRequest.of(page - 1, size))
                .map(mapper::map);
    }

}
