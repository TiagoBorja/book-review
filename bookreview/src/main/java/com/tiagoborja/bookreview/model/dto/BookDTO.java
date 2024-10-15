package com.tiagoborja.bookreview.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String title;
    private int publicationYear;
    private String isbn;
    private Long authorId;
}
