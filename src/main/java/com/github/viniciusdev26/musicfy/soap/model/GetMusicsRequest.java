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
@XmlRootElement(name = "GetMusicsRequest", namespace = "http://musicfy.viniciusdev26.github.com/soap/musics")
@XmlType(name = "GetMusicsRequest", namespace = "http://musicfy.viniciusdev26.github.com/soap/musics", propOrder = {"playlistId", "artist", "searchTerm", "page", "pageSize"})
public class GetMusicsRequest {

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics")
    private Long playlistId;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics")
    private String artist;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics")
    private String searchTerm;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics")
    private Integer page;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics")
    private Integer pageSize;
}
