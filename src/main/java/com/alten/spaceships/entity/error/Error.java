package com.alten.spaceships.entity.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Error {
    private String code;
    private String message;
}
