package com.alten.spaceships.entity.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ErrorResponse {
    private List<Error> errors;
}
