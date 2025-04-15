package br.com.fiap.alarme.dto;

import java.util.List;
import java.util.ArrayList;

public class ApiError {
    private String message;
    private List<String> errors;

    public ApiError(String message) {
        this.message = message;
        this.errors = new ArrayList<>();
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
