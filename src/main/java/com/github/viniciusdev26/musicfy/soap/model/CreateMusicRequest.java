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
@XmlRootElement(name = "CreateMusicRequest")
@XmlType(name = "CreateMusicRequest", propOrder = {"name", "artist", "audioUrl"})
public class CreateMusicRequest {

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String artist;

    @XmlElement(required = true)
    private String audioUrl;
}
