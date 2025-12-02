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
@XmlRootElement(name = "GetMusicsResponse")
@XmlType(name = "GetMusicsResponse", propOrder = {"musics", "totalCount", "page", "pageSize", "totalPages"})
public class GetMusicsResponse {

    @XmlElement(required = true)
    private List<MusicSoapDto> musics = new ArrayList<>();

    @XmlElement(required = true)
    private Integer totalCount;

    @XmlElement
    private Integer page;

    @XmlElement
    private Integer pageSize;

    @XmlElement
    private Integer totalPages;
}
