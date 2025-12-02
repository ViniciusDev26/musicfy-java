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
@XmlRootElement(name = "GetPlaylistsResponse")
@XmlType(name = "GetPlaylistsResponse", propOrder = {"playlists", "totalCount", "page", "pageSize", "totalPages"})
public class GetPlaylistsResponse {

    @XmlElement(required = true)
    private List<PlaylistSoapDto> playlists = new ArrayList<>();

    @XmlElement(required = true)
    private Integer totalCount;

    @XmlElement
    private Integer page;

    @XmlElement
    private Integer pageSize;

    @XmlElement
    private Integer totalPages;
}
