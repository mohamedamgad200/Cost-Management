package com.example.COST.MANAGEMENT.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
public class NotFoundException extends RuntimeException {
    private final String message;
}
