package com.bootcampzup.mercadolivre.shared;

public class FieldErrorOutputDto {
    private String name;
    private String message;

    public FieldErrorOutputDto(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public FieldErrorOutputDto() {
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
