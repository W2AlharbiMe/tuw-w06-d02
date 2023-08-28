package com.example.week05d02blog.Api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseWithData<T> {
    private String message;
    private T data;
}
