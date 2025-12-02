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
@XmlRootElement(name = "GetMusicsRequest")
@XmlType(name = "GetMusicsRequest", propOrder = {"playlistId", "artist", "searchTerm", "page", "pageSize"})
public class GetMusicsRequest {

    @XmlElement
    private Long playlistId;

    @XmlElement
    private String artist;

    @XmlElement
    private String searchTerm;

    @XmlElement
    private Integer page;

    @XmlElement
    private Integer pageSize;
}
