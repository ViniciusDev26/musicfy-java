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
@XmlRootElement(name = "CreateMusicRequest", namespace = "http://musicfy.viniciusdev26.github.com/soap/musics")
@XmlType(name = "CreateMusicRequest", namespace = "http://musicfy.viniciusdev26.github.com/soap/musics", propOrder = {"name", "artist", "audioUrl"})
public class CreateMusicRequest {

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics", required = true)
    private String name;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics", required = true)
    private String artist;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/musics", required = true)
    private String audioUrl;
}
