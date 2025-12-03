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
@XmlRootElement(name = "GetMusicsResponse", namespace = "http://musicfy.viniciusdev26.github.com/soap/musics")
@XmlType(name = "GetMusicsResponse", namespace = "http://musicfy.viniciusdev26.github.com/soap/musics", propOrder = {"musics", "totalCount", "page", "pageSize", "totalPages"})
public class GetMusicsResponse {

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics", required = true)
    private List<MusicSoapDto> musics = new ArrayList<>();

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics", required = true)
    private Integer totalCount;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics")
    private Integer page;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics")
    private Integer pageSize;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics")
    private Integer totalPages;
}
