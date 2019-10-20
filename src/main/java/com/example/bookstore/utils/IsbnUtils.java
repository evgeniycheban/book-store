package com.example.bookstore.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class IsbnUtils {

    private final Pattern NOT_DIGITS_OR_NOT_X = Pattern.compile("[^\\dX]");

    public String format(String isbn) {
        if (isbn == null) {
            return null;
        }

        return NOT_DIGITS_OR_NOT_X.matcher(isbn).replaceAll("");
    }

}
