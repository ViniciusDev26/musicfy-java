package com.github.viniciusdev26.musicfy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, unique = true)
    private String email;

    public User(String name, LocalDate birthDate, String email) {
        validateName(name);
        validateEmail(email);
        validateBirthDate(birthDate);

        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
    }

    public void changeName(String name) {
        validateName(name);
        this.name = name;
    }

    public void changeEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    public int getAge() {
        if (birthDate == null) {
            return 0;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (name.trim().length() < 3) {
            throw new IllegalArgumentException("Name must have at least 3 characters");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email must contain @");
        }
    }

    private void validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }
        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date must be in the past");
        }

        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 13) {
            throw new IllegalArgumentException("User must be at least 13 years old");
        }
    }
}
