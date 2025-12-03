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
@XmlType(name = "PlaylistSoapDto", namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists", propOrder = {"id", "name", "userId", "isSystemPlaylist", "createdAt", "ownerName"})
public class PlaylistSoapDto {

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists", required = true)
    private Long id;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists", required = true)
    private String name;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists")
    private Long userId;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists", required = true)
    private Boolean isSystemPlaylist;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists", required = true)
    private LocalDateTime createdAt;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists")
    private String ownerName;
}
