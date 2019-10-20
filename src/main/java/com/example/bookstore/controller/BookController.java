package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookFilterDto;
import com.example.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService service;

    @GetMapping("/add")
    public String add(Model model) {
        BookDto bookDto = new BookDto();
        bookDto.setYear(2000);
        model.addAttribute("bookDto", bookDto);

        return "book/save";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("bookDto", service.get(id));

        return "book/save";
    }

    @PostMapping("/save")
    public String save(@Valid BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return "book/save";
        }

        service.save(bookDto);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        service.delete(id);

        return "redirect:/";
    }

    @GetMapping
    public String getAll(BookFilterDto filter, Model model) {
        model.addAttribute("page", service.getAll(filter));

        return "index";
    }

}
