package com.tiagoborja.bookreview.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessDTO {

    private String token;

    public AccessDTO(String token) {
        super();
        this.token = token;
    }
}
