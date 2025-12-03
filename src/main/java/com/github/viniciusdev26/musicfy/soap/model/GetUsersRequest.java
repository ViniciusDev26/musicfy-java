package com.github.viniciusdev26.musicfy.soap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetUsersRequest", namespace = "http://musicfy.viniciusdev26.github.com/soap/users")
@XmlType(name = "GetUsersRequest", namespace = "http://musicfy.viniciusdev26.github.com/soap/users", propOrder = {"page", "pageSize"})
public class GetUsersRequest {

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/users")
    private Integer page;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/users")
    private Integer pageSize;
}
