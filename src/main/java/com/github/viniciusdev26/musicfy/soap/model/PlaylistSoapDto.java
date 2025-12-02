package com.github.viniciusdev26.musicfy.soap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlaylistSoapDto", propOrder = {"id", "name", "userId", "isSystemPlaylist", "createdAt", "ownerName"})
public class PlaylistSoapDto {

    @XmlElement(required = true)
    private Long id;

    @XmlElement(required = true)
    private String name;

    @XmlElement
    private Long userId;

    @XmlElement(required = true)
    private Boolean isSystemPlaylist;

    @XmlElement(required = true)
    private LocalDateTime createdAt;

    @XmlElement
    private String ownerName;
}
