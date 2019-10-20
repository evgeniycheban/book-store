package com.example.bookstore.repository.specification;

import com.example.bookstore.domain.Book;
import com.example.bookstore.dto.BookFilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BookFilterSpecification implements Specification<Book> {

    private final BookFilterDto filter;

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        String isbn = filter.getIsbn();
        if (isbn != null) {
            predicates.add(criteriaBuilder.like(root.get(Book.Fields.isbn), "%" + isbn + "%"));
        }

        String name = filter.getName();
        if (name != null) {
            predicates.add(criteriaBuilder.like(root.get(Book.Fields.name), "%" + name + "%"));
        }

        String author = filter.getAuthor();
        if (author != null) {
            predicates.add(criteriaBuilder.like(root.get(Book.Fields.author), "%" + author + "%"));
        }

        Integer fromYear = filter.getFromYear();
        if (fromYear != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Book.Fields.year), fromYear));
        }

        Integer toYear = filter.getToYear();
        if (toYear != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Book.Fields.year), toYear));
        }

        Double fromPrice = filter.getFromPrice();
        if (fromPrice != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(Book.Fields.price), fromPrice));
        }

        Double toPrice = filter.getToPrice();
        if (toPrice != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(Book.Fields.price), toPrice));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
