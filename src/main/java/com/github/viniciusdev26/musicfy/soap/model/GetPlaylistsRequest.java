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
@XmlRootElement(name = "GetPlaylistsRequest", namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists")
@XmlType(name = "GetPlaylistsRequest", namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists", propOrder = {"userId", "systemOnly", "page", "pageSize"})
public class GetPlaylistsRequest {

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists")
    private Long userId;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists")
    private Boolean systemOnly;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists")
    private Integer page;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists")
    private Integer pageSize;
}
