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
@XmlRootElement(name = "GetPlaylistsResponse", namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists")
@XmlType(name = "GetPlaylistsResponse", namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists", propOrder = {"playlists", "totalCount", "page", "pageSize", "totalPages"})
public class GetPlaylistsResponse {

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists", required = true)
    private List<PlaylistSoapDto> playlists = new ArrayList<>();

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists", required = true)
    private Integer totalCount;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists")
    private Integer page;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists")
    private Integer pageSize;

    @XmlElement(namespace = "http://musicfy.viniciusdev26.github.com/soap/playlists")
    private Integer totalPages;
}
