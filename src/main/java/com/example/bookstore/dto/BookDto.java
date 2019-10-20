package com.example.bookstore.dto;

import com.example.bookstore.utils.IsbnUtils;
import com.example.bookstore.validation.annotation.UniqueIsbn;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@UniqueIsbn(message = "ISBN must be unique")
public class BookDto {

    private Long id;

    @ISBN
    private String isbn;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Author must not be blank")
    private String author;

    @NotNull(message = "Year must not be null")
    private Integer year;

    @NotNull(message = "Price must not be null")
    private Double price;

    public void setIsbn(String isbn) {
        this.isbn = IsbnUtils.format(isbn);
    }

}
