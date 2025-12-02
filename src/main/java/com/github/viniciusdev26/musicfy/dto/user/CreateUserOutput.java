package com.github.viniciusdev26.musicfy.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserOutput {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String email;
    private Integer age;
}
