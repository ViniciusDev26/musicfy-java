package com.github.viniciusdev26.musicfy.soap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetUsersResponse", namespace = "http://musicfy.viniciusdev26.github.com/soap/users")
@XmlType(name = "GetUsersResponse", namespace = "http://musicfy.viniciusdev26.github.com/soap/users", propOrder = {"users", "totalCount", "page", "pageSize", "totalPages"})
public class GetUsersResponse {

    @XmlElement(required = true, namespace = "http://musicfy.viniciusdev26.github.com/soap/users")
    private List<UserSoapDto> users = new ArrayList<>();

    @XmlElement(required = true, namespace = "http://musicfy.viniciusdev26.github.com/soap/users")
    private Integer totalCount;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/users")
    private Integer page;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/users")
    private Integer pageSize;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/users")
    private Integer totalPages;
}
