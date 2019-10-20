package com.example.bookstore.dto;

import lombok.Data;

@Data
public class BookFilterDto {

    private String isbn;
    private String name;
    private String author;

    private Integer fromYear;
    private Integer toYear;

    private Double fromPrice;
    private Double toPrice;

    private Integer page;
    private Integer size;

}
