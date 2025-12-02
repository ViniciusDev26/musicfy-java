package com.github.viniciusdev26.musicfy.soap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CreateUserRequest")
@XmlType(name = "CreateUserRequest", propOrder = {"name", "birthDate", "email"})
public class CreateUserRequest {

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private LocalDate birthDate;

    @XmlElement(required = true)
    private String email;
}
