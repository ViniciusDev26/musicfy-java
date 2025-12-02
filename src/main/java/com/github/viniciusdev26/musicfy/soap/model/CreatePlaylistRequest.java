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
@XmlRootElement(name = "CreatePlaylistRequest")
@XmlType(name = "CreatePlaylistRequest", propOrder = {"name", "userId", "musicIds"})
public class CreatePlaylistRequest {

    @XmlElement(required = true)
    private String name;

    @XmlElement
    private Long userId;

    @XmlElement
    private List<Long> musicIds = new ArrayList<>();
}
