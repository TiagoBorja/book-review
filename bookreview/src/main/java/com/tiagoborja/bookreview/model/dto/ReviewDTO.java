package com.tiagoborja.bookreview.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private Long userId;
    private Long bookId;
    private int rating;
    private String comment;
}

