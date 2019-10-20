package com.example.bookstore.validation.validator;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.validation.annotation.UniqueIsbn;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueIsbnValidator implements ConstraintValidator<UniqueIsbn, BookDto> {

    private final BookRepository repository;

    private String message;

    @Override
    public void initialize(UniqueIsbn constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(BookDto bookDto, ConstraintValidatorContext context) {
        String isbn = bookDto.getIsbn();
        if (isbn == null) {
            return true;
        }

        boolean present = repository.findByIsbn(isbn)
                .filter(book -> !book.getId().equals(bookDto.getId()))
                .isPresent();

        if (present) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode("isbn")
                    .addConstraintViolation();
        }

        return !present;
    }

}
