package com.shamstabrez16.restcrud.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Student {
    private long id;
    private String firstName;
    private String lastName;
}
